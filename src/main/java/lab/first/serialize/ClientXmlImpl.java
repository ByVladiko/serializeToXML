package lab.first.serialize;

import lab.first.model.Client;
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

public class ClientXmlImpl extends XmlDoc<Client> implements Xml<Client>{

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
        String xmlText = null;
        List<Client> list = null;
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
                            JAXBContext context = JAXBContext.newInstance(Client.class);
                            Unmarshaller unmarshaller = context.createUnmarshaller();
                            xmlText.trim();
                            list = new ArrayList<>();
                            list.add((Client) unmarshaller.unmarshal(new StringReader(xmlText)));
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
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        return list;
    }

    @Override
    public void save(Client client) {

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
            if(clients.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(client.getId().toString())) {
                clients.item(i).getParentNode().removeChild(clients.item(i));
            }
        }
        writeDocument(document, file);
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


            // airship элемент и id в виде атрибута
            Element airship = document.createElement("airship");

            // Устанавливаем значения текста внутри тегов
            airship.setAttribute("id", client.getTickets().get(i).getAirship().getId().toString());

            Element airshipModel = document.createElement("model");
            airshipModel.setTextContent(client.getTickets().get(i).getAirship().getModel());

            Element airshipNumOfSeat = document.createElement("numberOfSeat");
            airshipNumOfSeat.setTextContent(String.valueOf(client.getTickets().get(i).getAirship().getNumberOfSeat()));

            Element route = document.createElement("route");

            route.setAttribute("id", client.getTickets().get(i).getRoute().getId().toString());

            Element routeStart = document.createElement("startPoint");
            routeStart.setTextContent(client.getTickets().get(i).getRoute().getStartPoint());

            Element routeFinish = document.createElement("endPoint");
            routeFinish.setTextContent(client.getTickets().get(i).getRoute().getEndPoint());

            ticketElement.appendChild(airship);
            airship.appendChild(airshipModel);
            airship.appendChild(airshipNumOfSeat);

            ticketElement.appendChild(route);
            route.appendChild(routeStart);
            route.appendChild(routeFinish);

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
