package lab.first.serialize;

import airship.model.Airship;
import airship.model.Route;
import airship.model.Ticket;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RouteXmlImpl extends XmlDoc<Route> implements Xml<Route> {

    private File file;
    private DocumentBuilder documentBuilder;

    public RouteXmlImpl() {
        this.file = new File("Repository.xml");
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Route> read() {
        Document document = documentBuilder.newDocument();
        List<Route> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        NodeList routes;
        try {
            routes = ((Element) document.getElementsByTagName("routes").item(0)).getElementsByTagName("route");
        } catch (NullPointerException e) {
            return list;
        }

        for (int i = 0; i < routes.getLength(); i++) {

            Element elementRoute = (Element) routes.item(i);

            UUID id = UUID.fromString(elementRoute.getAttributes().getNamedItem("id").getNodeValue());
            String startPoint = elementRoute.getElementsByTagName("startPoint").item(0).getFirstChild().getTextContent();
            String endPoint = elementRoute.getElementsByTagName("endPoint").item(0).getFirstChild().getTextContent();

            list.add(new Route(id, startPoint, endPoint));
        }
        return list;
    }

    @Override
    public boolean save(Route route) {

        try {
            Document document;
            if (!file.exists()) {
                file.createNewFile();
                document = documentBuilder.newDocument();
                document.appendChild(document.createElement("repository"));
                writeDocument(document, file);
            }
            document = documentBuilder.parse(file);
            if (checkAndUpdate(document, route)) {
                writeDocument(document, file);
                return true;
            }
            writeDocument(addNewNode(document, route), file);
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Route route) {
        Document document;
        try {
            document = documentBuilder.parse("Repository.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
        if(checkLinkToElement(document, route)) return false;
        Element element = (Element) document.getElementsByTagName("routes").item(0);
        NodeList routes = element.getElementsByTagName("route");
        for (int i = 0; i < routes.getLength(); i++) {
            if (routes.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(route.getId().toString())) {
                routes.item(i).getParentNode().removeChild(routes.item(i));
                break;
            }
        }
        writeDocument(document, file);
        return true;
    }

    @Override
    boolean checkAndUpdate(Document doc, Route route) {
        Element element;
        NodeList routesList = ((Element) doc.getElementsByTagName("routes").item(0)).getElementsByTagName("route");
        for (int i = 0; i < routesList.getLength(); i++) {
            element = (Element) routesList.item(i);
            if (element.getAttribute("id").equals(route.getId().toString())) {
                element.getElementsByTagName("startPoint").item(0).getFirstChild().setNodeValue(route.getStartPoint());
                element.getElementsByTagName("endPoint").item(0).getFirstChild().setNodeValue(route.getEndPoint());
                return true;
            }
        }
        return false;
    }

    private boolean checkLinkToElement(Document doc, Route route) {
        NodeList clientList;
        try {
            clientList = ((Element) doc.getElementsByTagName("clients").item(0)).getElementsByTagName("client");
        } catch (NullPointerException e) {
            return false;
        }

        for (int i = 0; i < clientList.getLength(); i++) {

            NodeList ticketsOfClient;
            try {
                ticketsOfClient = ((Element) ((Element) clientList.item(i)).getElementsByTagName("tickets").item(0)).getElementsByTagName("ticket");
            } catch (NullPointerException e) {
                return false;
            }

            for (int j = 0; j < ticketsOfClient.getLength(); j++) {
                if (((Element) ((Element) ticketsOfClient.item(j)).getElementsByTagName("route").item(0)).getAttribute("id").equals(route.getId().toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    Document addNewNode(Document document, Route route) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getElementsByTagName("routes").item(0);
        if (root == null) {
            Element routeElement = document.createElement("routes");
            root = document.getElementsByTagName("repository").item(0).appendChild(routeElement);
        }

        // создание элемента route и присваивание ему id в виде атрибута
        Element routeElement = document.createElement("route");
        routeElement.setAttribute("id", route.getId().toString());

        // startPoint элемент
        Element startPoint = document.createElement("startPoint");
        // Устанавливаем значение текста внутри тега
        startPoint.setTextContent(route.getStartPoint());

        // endPoint элемент
        Element endPoint = document.createElement("endPoint");
        // Устанавливаем значение текста внутри тега
        endPoint.setTextContent(route.getEndPoint());

        // Добавляем внутренние элементы route в элемент <route>
        routeElement.appendChild(startPoint);
        routeElement.appendChild(endPoint);

        root.appendChild(routeElement);

        document.normalizeDocument();
        document.normalize();

        // Записываем XML в файл
        return document;
    }
}
