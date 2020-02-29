package lab.first.serialize;

import airship.model.Route;
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
        Document document = null;
        List<Route> list = new ArrayList<>();
        if(!file.exists()) {
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
    public void save(Route route) {

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
                return;
            }
            writeDocument(addNewNode(document, route), file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Route route) {
        Document document = null;
        try {
            document = documentBuilder.parse("Repository.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        Element element = (Element) document.getElementsByTagName("routes").item(0);
        NodeList routes = element.getElementsByTagName("route");
        for (int i = 0; i < routes.getLength(); i++) {
            if(routes.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(route.getId().toString())) {
                routes.item(i).getParentNode().removeChild(routes.item(i));
                break;
            }
        }
        writeDocument(document, file);
    }

    @Override
    boolean checkAndUpdate(Document doc, Route route) {
        boolean flag = false;
        NodeList routesList = ((Element) doc.getElementsByTagName("routes").item(0)).getElementsByTagName("route");
        Element element = null;
        for (int i = 0; i < routesList.getLength(); i++) {
            element = (Element) routesList.item(i);
            if (element.getAttribute("id").equals(route.getId().toString())) {
                flag = true;
                element.getElementsByTagName("startPoint").item(0).getFirstChild().setNodeValue(route.getStartPoint());
                element.getElementsByTagName("endPoint").item(0).getFirstChild().setNodeValue(route.getEndPoint());
            }
        }
        return flag;
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
