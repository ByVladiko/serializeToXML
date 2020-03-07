package lab.first.serialize;

import airship.model.Airship;
import airship.model.Client;
import airship.model.Route;
import airship.model.Ticket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientXmlImpl extends XmlDoc<Client> implements Xml<Client> {

    private File file;
    private DocumentBuilder documentBuilder;

    public ClientXmlImpl() {
        this.file = new File("Repository.xml");
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
        } catch (SAXException | IOException e) {
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

            List<Ticket> tickets = new ArrayList<>();

            NodeList ticketsList;
            try {
                ticketsList = ((Element) element.getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");
            } catch (NullPointerException e) {
                client.setTickets(tickets);
                list.add(client);
                continue;
            }

            for (int j = 0; j < ticketsList.getLength(); j++) {

                    Ticket ticket = new Ticket();

                    String ticketId = ((Element) ticketsList.item(j)).getAttribute("id");

                    Airship airship = new Airship();
                    String idAirship = ((Element) ((Element) ticketsList.item(j)).getElementsByTagName("airship").item(0)).getAttribute("id");
                    Element airshipOfTicket = findElement(document, "airship", idAirship);

                    airship.setId(UUID.fromString(airshipOfTicket.getAttribute("id")));
                    airship.setModel(airshipOfTicket.getElementsByTagName("model").item(0).getFirstChild().getNodeValue());
                    airship.setNumberOfSeat(Long.parseLong(airshipOfTicket.getElementsByTagName("numberOfSeat").item(0).getFirstChild().getNodeValue()));

                    Route route = new Route();
                    String idRoute = ((Element) ((Element) ticketsList.item(j)).getElementsByTagName("route").item(0)).getAttribute("id");
                    Element routeOfTicket = findElement(document, "route", idRoute);

                    route.setId(UUID.fromString(routeOfTicket.getAttribute("id")));
                    route.setStartPoint(routeOfTicket.getElementsByTagName("startPoint").item(0).getFirstChild().getNodeValue());
                    route.setEndPoint(routeOfTicket.getElementsByTagName("endPoint").item(0).getFirstChild().getNodeValue());

                    ticket.setId(UUID.fromString(ticketId));
                    ticket.setAirship(airship);
                    ticket.setRoute(route);
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
    public boolean save(Client client) {

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
                return true;
            }
            writeDocument(addNewNode(document, client), file);
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Client client) {
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
        Element element = (Element) document.getElementsByTagName("clients").item(0);
        NodeList clients = element.getElementsByTagName("client");
        for (int i = 0; i < clients.getLength(); i++) {
            if (clients.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(client.getId().toString())) {
                clients.item(i).getParentNode().removeChild(clients.item(i));
            }
        }
        writeDocument(document, file);
        return true;
    }

    @Override
    boolean checkAndUpdate(Document doc, Client client) {
        NodeList clientsList = doc.getElementsByTagName("client");
        Element element;
        for (int i = 0; i < clientsList.getLength(); i++) {
            element = (Element) clientsList.item(i);
            if (element.getAttribute("id").equals(client.getId().toString())) {
                element.getParentNode().removeChild(element);
                addNewNode(doc, client);
                return true;
            }
        }
        return false;
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

            Element airship = document.createElement("airship");
            airship.setAttribute("id", client.getTickets().get(i).getAirship().getId().toString());

            Element route = document.createElement("route");
            route.setAttribute("id", client.getTickets().get(i).getRoute().getId().toString());

            ticketElement.appendChild(airship);
            ticketElement.appendChild(route);

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
