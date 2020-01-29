package lab.first.serialize;

import lab.first.model.Airship;
import lab.first.model.Client;
import lab.first.model.Route;
import lab.first.model.Ticket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientXmlImpl extends XmlDoc<Client> implements Xml<Client> {

    private File file;
    private DocumentBuilder documentBuilder;

    public ClientXmlImpl() {
        this.file = new File("Serialize.xml");
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> read() {
        List<Client> list = new ArrayList<>();
        Document document = null;
        if (!file.exists()) {
            return list;
        }
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList clientsList;
        try {
            clientsList = ((Element) document.getElementsByTagName("clients").item(0)).getElementsByTagName("client");
        } catch (NullPointerException e) {
            return list;
        }

        for (int i = 0; i < clientsList.getLength(); i++) {

            Client client = new Client();

            Element element = (Element) clientsList.item(i);

            client.setId(UUID.fromString(element.getAttribute("id")));
            client.setFirstName(element.getElementsByTagName("firstName").item(0).getFirstChild().getNodeValue());
            client.setMiddleName(element.getElementsByTagName("middleName").item(0).getFirstChild().getNodeValue());
            client.setLastName(element.getElementsByTagName("lastName").item(0).getFirstChild().getNodeValue());

            NodeList ticketsList = ((Element) element.getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");

            List<Ticket> tickets = new ArrayList<>();

            for (int j = 0; j < ticketsList.getLength(); j++) {

                Ticket ticket = new Ticket();

                String ticketId = ((Element) ticketsList.item(j)).getAttribute("id");
                NodeList repoTicketsList = ((Element) document.getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");

                for (int k = 0; k < repoTicketsList.getLength(); k++) {

                    Airship airship = new Airship();

                    Element foundedTicket = findElement(document, "ticket", ticketId);
                    String idAirship = ((Element) foundedTicket.getElementsByTagName("airship").item(0)).getAttribute("id");
                    Element airshipOfTicket = findElement(document, "airship", idAirship);

                    airship.setId(UUID.fromString(airshipOfTicket.getAttribute("id")));
                    airship.setModel(airshipOfTicket.getElementsByTagName("model").item(0).getFirstChild().getNodeValue());
                    airship.setNumberOfSeat(Long.valueOf(airshipOfTicket.getElementsByTagName("numberOfSeat").item(0).getFirstChild().getNodeValue()));

                    Route route = new Route();
                    String idRoute = ((Element) foundedTicket.getElementsByTagName("route").item(0)).getAttribute("id");
                    Element routeOfTicket = findElement(document, "route", idRoute);

                    route.setId(UUID.fromString(routeOfTicket.getAttribute("id")));
                    route.setStartPoint(routeOfTicket.getElementsByTagName("startPoint").item(0).getFirstChild().getNodeValue());
                    route.setEndPoint(routeOfTicket.getElementsByTagName("endPoint").item(0).getFirstChild().getNodeValue());

                    ticket.setId(UUID.fromString(ticketId));
                    ticket.setAirship(airship);
                    ticket.setRoute(route);

                }
                tickets.add(ticket);
            }
            client.setTickets(tickets);
            list.add(client);
        }
        return list;
    }

    private Element findElement(Document document, String elem, String id) {
        NodeList list = ((Element) document.getElementsByTagName(elem + "s").item(0)).getElementsByTagName(elem);
        for (int i = 0; i < list.getLength(); i++) {
            if (((Element) list.item(i)).getAttribute("id").equals(id)) {
                return (Element) list.item(i);
            }
        }
        return null;
    }

    @Override
    public void save(Client client) {

        try {
            Document document;
            if (!file.exists()) {
                file.createNewFile();
                document = documentBuilder.newDocument();
                document.appendChild(document.createElement("repository"));
                writeDocument(document, file);
            }
            document = documentBuilder.parse(file);
            if (checkAndUpdate(document, client)) {
                writeDocument(document, file);
                return;
            }
            writeDocument(addNewNode(document, client), file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Client client) {
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element element = (Element) document.getElementsByTagName("clients").item(0);
        NodeList clients = element.getElementsByTagName("client");
        for (int i = 0; i < clients.getLength(); i++) {
            if (clients.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(client.getId().toString())) {
                clients.item(i).getParentNode().removeChild(clients.item(i));
            }
        }
        writeDocument(document, file);
    }

    @Override
    boolean checkAndUpdate(Document doc, Client client) {
        Boolean flag = false;
        NodeList clientsList = doc.getElementsByTagName("client");
        Element element;
        for (int i = 0; i < clientsList.getLength(); i++) {
            element = (Element) clientsList.item(i);
            if (element.getAttribute("id").equals(client.getId().toString())) {
                flag = true;
                element.getParentNode().removeChild(element);
                addNewNode(doc, client);
                writeDocument(doc, file);
            }
        }
        return flag;
    }

    @Override
    Document addNewNode(Document document, Client client) {
        // Получаем корневой элемент
        Node root = document.getElementsByTagName("clients").item(0);
        if (root == null) {
            Element ticketElement = document.createElement("clients");
            root = document.getElementsByTagName("repository").item(0).appendChild(ticketElement);
        }

        Element clientElement = document.createElement("client");
        clientElement.setAttribute("id", client.getId().toString());

        Element firstName = document.createElement("firstName");
        firstName.setTextContent(client.getFirstName());

        Element middleName = document.createElement("middleName");
        middleName.setTextContent(client.getMiddleName());

        Element lastName = document.createElement("lastName");
        lastName.setTextContent(client.getLastName());

        Element tickets = document.createElement("tickets");

        for (int i = 0; i < client.getTickets().size(); i++) {
            Element ticketElement = document.createElement("ticket");
            ticketElement.setAttribute("id", client.getTickets().get(i).getId().toString());

            tickets.appendChild(ticketElement);
        }

        clientElement.appendChild(firstName);
        clientElement.appendChild(middleName);
        clientElement.appendChild(lastName);
        clientElement.appendChild(tickets);

        root.appendChild(clientElement);

        document.normalizeDocument();
        document.normalize();

        // Записываем XML в файл
        return document;
    }
}
