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

public class TicketXmlImpl extends XmlDoc<Ticket> implements Xml<Ticket> {

    private File file;
    private DocumentBuilder documentBuilder;

    public TicketXmlImpl() {
        this.file = new File("Repository.xml");
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> read() {
        List<Ticket> list = new ArrayList<>();
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

        NodeList clientList;
        try {
            clientList = ((Element) document.getElementsByTagName("clients").item(0)).getElementsByTagName("client");
        } catch (NullPointerException e) {
            return list;
        }

        for (int i = 0; i < clientList.getLength(); i++) {

            NodeList ticketsOfClient;
            try {
                ticketsOfClient = ((Element) ((Element) clientList.item(i)).getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");
            } catch (NullPointerException e) {
                continue;
            }

            for (int j = 0; j < ticketsOfClient.getLength(); j++) {

                Ticket ticket = new Ticket();

                ticket.setId(UUID.fromString(((Element)ticketsOfClient.item(j)).getAttribute("id")));

                String idAirship = ((Element)((Element) ticketsOfClient.item(j)).getElementsByTagName("airship").item(0)).getAttribute("id");
                Element airshipOfTicket = findElement(document, "airship", idAirship);

                Airship airship = new Airship();
                airship.setId(UUID.fromString(airshipOfTicket.getAttribute("id")));
                airship.setModel(airshipOfTicket.getElementsByTagName("model").item(0).getFirstChild().getNodeValue());
                airship.setNumberOfSeat(Long.parseLong(airshipOfTicket.getElementsByTagName("numberOfSeat").item(0).getFirstChild().getNodeValue()));

                String idRoute = ((Element)((Element) ticketsOfClient.item(j)).getElementsByTagName("route").item(0)).getAttribute("id");
                Element routeOfTicket = findElement(document, "route", idRoute);

                Route route = new Route();
                route.setId(UUID.fromString(routeOfTicket.getAttribute("id")));
                route.setStartPoint(routeOfTicket.getElementsByTagName("startPoint").item(0).getFirstChild().getNodeValue());
                route.setEndPoint(routeOfTicket.getElementsByTagName("endPoint").item(0).getFirstChild().getNodeValue());

                ticket.setAirship(airship);
                ticket.setRoute(route);

                list.add(ticket);
            }
        }
        return list;
    }

    public List<Ticket> getTicketsByClient(Client client) {
        List<Ticket> list = new ArrayList<>();
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

            Element element = (Element) clientsList.item(i);

            if (!client.getId().toString().equals(element.getAttribute("id"))) {
                continue;
            }

            NodeList ticketsList;
            try {
                ticketsList = ((Element) element.getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");
            } catch (NullPointerException e) {
                return list;
            }

            for (int j = 0; j < ticketsList.getLength(); j++) {

                Ticket ticket = new Ticket();

                ticket.setId(UUID.fromString(((Element)ticketsList.item(j)).getAttribute("id")));

                String idAirship = ((Element)((Element) ticketsList.item(j)).getElementsByTagName("airship").item(0)).getAttribute("id");
                Element airshipOfTicket = findElement(document, "airship", idAirship);

                Airship airship = new Airship();
                airship.setId(UUID.fromString(airshipOfTicket.getAttribute("id")));
                airship.setModel(airshipOfTicket.getElementsByTagName("model").item(0).getFirstChild().getNodeValue());
                airship.setNumberOfSeat(Long.parseLong(airshipOfTicket.getElementsByTagName("numberOfSeat").item(0).getFirstChild().getNodeValue()));

                String idRoute = ((Element)((Element) ticketsList.item(j)).getElementsByTagName("route").item(0)).getAttribute("id");
                Element routeOfTicket = findElement(document, "route", idRoute);

                Route route = new Route();
                route.setId(UUID.fromString(routeOfTicket.getAttribute("id")));
                route.setStartPoint(routeOfTicket.getElementsByTagName("startPoint").item(0).getFirstChild().getNodeValue());
                route.setEndPoint(routeOfTicket.getElementsByTagName("endPoint").item(0).getFirstChild().getNodeValue());

                ticket.setAirship(airship);
                ticket.setRoute(route);
                list.add(ticket);
            }
            return list;
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
    public void save(Ticket ticket) {

        try {
            Document document;
            if (!file.exists()) {
                file.createNewFile();
                document = documentBuilder.newDocument();
                document.appendChild(document.createElement("repository"));
                writeDocument(document, file);
            }
            document = documentBuilder.parse(file);
            checkAndUpdate(document, ticket);
            writeDocument(document, file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        Document document = null;
        try {
            document = documentBuilder.parse("Repository.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList clientsList = ((Element) document.getElementsByTagName("clients").item(0)).getElementsByTagName("client");

        for (int i = 0; i < clientsList.getLength(); i++) {

            Element element = (Element) clientsList.item(i);

            NodeList ticketsList = ((Element) element.getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");

            for (int j = 0; j < ticketsList.getLength(); j++) {
                if (ticket.getId().toString().equals(((Element) ticketsList.item(j)).getAttribute("id"))) {
                    ticketsList.item(j).getParentNode().removeChild(ticketsList.item(j));
                    break;
                }
            }
            writeDocument(document, file);
        }
    }

    @Override
    boolean checkAndUpdate(Document doc, Ticket ticket) {
        boolean flag = false;
        NodeList clientsList = ((Element) doc.getElementsByTagName("clients").item(0)).getElementsByTagName("client");

        for (int i = 0; i < clientsList.getLength(); i++) {

            Element element = (Element) clientsList.item(i);

            NodeList ticketsList = ((Element) element.getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");

            for (int j = 0; j < ticketsList.getLength(); j++) {
                if (ticket.getId().toString().equals(((Element) ticketsList.item(j)).getAttribute("id"))) {
                    flag = true;
                    ((Element) ((Element) ticketsList.item(j)).getElementsByTagName("airship").item(0)).setAttribute("id", ticket.getAirship().getId().toString());
                    ((Element) ((Element) ticketsList.item(j)).getElementsByTagName("route").item(0)).setAttribute("id", ticket.getRoute().getId().toString());
                    return flag;
                }
            }
        }
        return flag;
    }

    @Override
    Document addNewNode(Document document, Ticket ticket) {
        // Получаем корневой элемент
        Node root = document.getElementsByTagName("tickets").item(0);

        // создание элемента ticket и присваивание ему id в виде атрибута
        Element ticketElement = document.createElement("ticket");
        ticketElement.setAttribute("id", ticket.getId().toString());


        // airship элемент и id в виде атрибута
        Element airship = document.createElement("airship");
        airship.setAttribute("id", ticket.getAirship().getId().toString());

        Element route = document.createElement("route");
        route.setAttribute("id", ticket.getRoute().getId().toString());

        ticketElement.appendChild(airship);
        ticketElement.appendChild(route);

        root.appendChild(ticketElement);

        document.normalizeDocument();
        document.normalize();

        // Записываем XML в файл
        return document;
    }
}
