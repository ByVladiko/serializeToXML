package lab.first.serialize;

import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public abstract class XmlDoc<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // Функция для сохранения DOM в файл
    protected void writeDocument(Document document, File file) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(file);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
            fos.close();
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    abstract boolean checkAndUpdate(Document doc, T obj);

    abstract Document addNewNode(Document document, T obj);
}
