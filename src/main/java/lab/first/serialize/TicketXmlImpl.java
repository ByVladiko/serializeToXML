package lab.first.serialize;

import lab.first.model.Ticket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketXmlImpl extends XmlDoc<Ticket> implements Xml<Ticket> {

    private File file;
    private DocumentBuilder documentBuilder;

    public TicketXmlImpl() {
        this.file = new File("Serialize.xml");
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> read() {
        String xmlText = null;
        List<Ticket> list = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String s;
            while((s=br.readLine())!=null) {
                xmlText = "";
                if(s.indexOf("<ticket id=")!=-1) {
                    xmlText += s;
                    while(s.indexOf("</ticket>")==-1) {
                        s = br.readLine();
                        xmlText += s;
                    }
                    if(!xmlText.isEmpty()) {
                        try {
                            JAXBContext context = JAXBContext.newInstance(Ticket.class);
                            Unmarshaller unmarshaller = context.createUnmarshaller();
                            xmlText.trim();
                            list = new ArrayList<>();
                            list.add((Ticket) unmarshaller.unmarshal(new StringReader(xmlText)));
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void save(Ticket ticket) {

        // Создается построитель документа
        try {
            Document document;
            if (!file.exists()) {
                file.createNewFile();
                document = documentBuilder.newDocument();
                document.appendChild(document.createElement("repository"));
                writeDocument(document, file);
            }
            document = documentBuilder.parse(file);
            writeDocument(addNewNode(document, ticket), file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Ticket ticket) {
        Document document = null;
        try {
            document = documentBuilder.parse("Serialize.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element element = (Element) document.getElementsByTagName("tickets").item(0);
        NodeList tickets = element.getElementsByTagName("route");
        for (int i = 0; i < tickets.getLength(); i++) {
            if(tickets.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(ticket.getId().toString())) {
                tickets.item(i).getParentNode().removeChild(tickets.item(i));
            }
        }
        writeDocument(document, file);
    }

    @Override
    Document addNewNode(Document document, Ticket ticket) {
        // Получаем корневой элемент
        Node root = document.getElementsByTagName("tickets").item(0);
        if (root == null) {
            Element ticketElement = document.createElement("tickets");
            root = document.getElementsByTagName("repository").item(0).appendChild(ticketElement);
        }

        // создание элемента ticket и присваивание ему id в виде атрибута
        Element ticketElement = document.createElement("ticket");
        ticketElement.setAttribute("id", ticket.getId().toString());


        // airship элемент и id в виде атрибута
        Element airship = document.createElement("airship");

        // Устанавливаем значения текста внутри тегов
        airship.setAttribute("id", ticket.getAirship().getId().toString());

        Element airshipModel = document.createElement("model");
        airshipModel.setTextContent(ticket.getAirship().getModel());

        Element airshipNumOfSeat = document.createElement("numberOfSeat");
        airshipNumOfSeat.setTextContent(String.valueOf(ticket.getAirship().getNumberOfSeat()));

        Element route = document.createElement("route");

        route.setAttribute("id", ticket.getRoute().getId().toString());

        Element routeStart = document.createElement("startPoint");
        routeStart.setTextContent(ticket.getRoute().getStartPoint());

        Element routeFinish = document.createElement("endPoint");
        routeFinish.setTextContent(ticket.getRoute().getEndPoint());

        ticketElement.appendChild(airship);
        airship.appendChild(airshipModel);
        airship.appendChild(airshipNumOfSeat);

        ticketElement.appendChild(route);
        route.appendChild(routeStart);
        route.appendChild(routeFinish);

        root.appendChild(ticketElement);

        document.normalizeDocument();
        document.normalize();

        // Записываем XML в файл
        return document;
    }
}
