package lab.first.serialize;

import lab.first.model.Airship;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AirshipXmlImpl extends XmlDoc<Airship> implements Xml<Airship>, Serializable {

    private File file;
    private DocumentBuilder documentBuilder;

    public AirshipXmlImpl() {
        this.file = new File("Serialize.xml");
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airship> read() {
        Document document = null;
        List<Airship> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        try {
            document = documentBuilder.parse("Serialize.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        NodeList airships;
        try {
            airships = ((Element) document.getElementsByTagName("airships").item(0)).getElementsByTagName("airship");
        } catch (NullPointerException e) {
            return list;
        }

        for (int i = 0; i < airships.getLength(); i++) {
            Element elementAirship = (Element) airships.item(i);
            UUID id = UUID.fromString(elementAirship.getAttributes().getNamedItem("id").getNodeValue());
            String model = elementAirship.getElementsByTagName("model").item(0).getFirstChild().getTextContent();
            long numberOfSeat = Long.parseLong(elementAirship.getElementsByTagName("numberOfSeat").item(0).getFirstChild().getTextContent());
            list.add(new Airship(id, model, numberOfSeat));
        }
        return list;
    }

    @Override
    public void save(Airship airship) {

        try {
            Document document;
            if (!file.exists()) {
                file.createNewFile();
                document = documentBuilder.newDocument();
                document.appendChild(document.createElement("repository"));
                writeDocument(document, file);
            }
            document = documentBuilder.parse(file);
            if (checkAndUpdate(document, airship)) {
                writeDocument(document, file);
                return;
            }
            writeDocument(addNewNode(document, airship), file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Airship airship) {

        Document document = null;
        try {
            document = documentBuilder.parse("Serialize.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        Element element = (Element) document.getElementsByTagName("airships").item(0);
        NodeList airships = element.getElementsByTagName("airship");
        for (int i = 0; i < airships.getLength(); i++) {
            if (airships.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(airship.getId().toString())) {
                airships.item(i).getParentNode().removeChild(airships.item(i));
            }
        }
        writeDocument(document, file);

    }

    @Override
    Document addNewNode(Document document, Airship airship) {
        // Получаем корневой элемент
        Node root = document.getElementsByTagName("airships").item(0);
        if (root == null) {
            Element airshipElement = document.createElement("airships");
            root = document.getElementsByTagName("repository").item(0).appendChild(airshipElement);
        }

        // создание элемента airship и присваивание ему id в виде атрибута
        Element airshipElement = document.createElement("airship");
        airshipElement.setAttribute("id", airship.getId().toString());

        // model элемент
        Element model = document.createElement("model");
        // Устанавливаем значение текста внутри тега
        model.setTextContent(airship.getModel());

        // numberOfSeat элемент
        Element numberOfSeat = document.createElement("numberOfSeat");
        // Устанавливаем значение текста внутри тега
        numberOfSeat.setTextContent(Long.toString(airship.getNumberOfSeat()));

        // Добавляем внутренние элементы route в элемент <airship>
        airshipElement.appendChild(model);
        airshipElement.appendChild(numberOfSeat);

        root.appendChild(airshipElement);

        document.normalizeDocument();
        document.normalize();

        // Записываем XML в файл
        return document;
    }

    @Override
    boolean checkAndUpdate(Document doc, Airship airship) {
        boolean flag = false;
        NodeList airshipsList = doc.getElementsByTagName("airship");
        Element element = null;
        for (int i = 0; i < airshipsList.getLength(); i++) {
            element = (Element) airshipsList.item(i);
            if (element.getAttribute("id").equals(airship.getId().toString())) {
                flag = true;
                element.getElementsByTagName("model").item(0).getFirstChild().setNodeValue(airship.getModel());
                element.getElementsByTagName("numberOfSeat").item(0).getFirstChild().setNodeValue(Long.toString(airship.getNumberOfSeat()));
                break;
            }
        }
        return flag;
    }

}
