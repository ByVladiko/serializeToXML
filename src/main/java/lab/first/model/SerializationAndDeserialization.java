package lab.first.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class SerializationAndDeserialization {

    public void save() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Route.class);
            Marshaller ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Route route = new Route("Moscow", "Samara");
            ms.marshal(route, new File("src/main/resources/xml/Route.xml"));
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }

    public void read() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Route.class);
            Unmarshaller unm = jc.createUnmarshaller();
            Route route = (Route) unm.unmarshal(new File("src/main/resources/xml/Route.xml"));
            System.out.println("Route");
            System.out.println("id: " + route.getId());
            System.out.println("startPoint: " + route.getStartPoint());
            System.out.println("endPoint: " + route.getEndPoint());
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }
}
