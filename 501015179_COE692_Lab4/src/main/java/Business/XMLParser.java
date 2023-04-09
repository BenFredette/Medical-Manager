/*package Business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {

    private static short TEXT = 3;

    public static String ConvertXmlToHtmlTable(String xmlUrl) {
        StringBuilder html = new StringBuilder("<table border=\"1\"   cellpadding=\"5\" cellspacing=\"0\"> " + "\r\n");
        try {
            URL url = new URL(xmlUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String xml = response.toString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            Element root = document.getDocumentElement();

            NodeList childs = root.getChildNodes();

            for (int i = 0; i < childs.getLength(); i++) {
                String elename = "";
                if (childs.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    html.append("<tr>");
                    String text = getChildText(childs.item(i));

                    html.append(text);

                    html.append("<td>  <button type=\"submit\">Hold</button> ");

                    html.append("</td>");
                    html.append("</tr>");
                }

            }

            html.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            return xmlUrl;
            // Returning the original string incase of error.
        }
        System.out.println(html.toString());
        return html.toString();
    }

    private static String getChildText(Node node) {
        StringBuilder html = new StringBuilder("");
        /*
         * if(!node.hasChildNodes()){ html.append("<table> <tr><th>"+node.getNodeName(
         * )+"</th></tr><tr><td>"+node.getNodeValue()+"</td></tr>"); return
         * (html.toString()); }
         

        if (node.getNodeType() == TEXT) {

            html.append("<td>" + node.getNodeValue() + "</td>");
            return (html.toString());

        } else {
            html.append("<td><table width=\"100%\" border=\"0\"  cellpadding=\"10\"  cellspacing=\"1\"><tr><th>"
                    + node.getNodeName() + "</th></tr>");
            NodeList nodes = node.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {

                html.append(getChildText(nodes.item(i)));

            }
            html.append("</table></td>");
            return (html.toString());
        }
    }

}
*/
package Business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import Helper.Message;
import Helper.Message_XML;
import Helper.Prescription;
import Helper.Prescription_XML;

public class XMLParser {

    private static short TEXT = 3;

    public static String ConvertXmlToHtmlTable(String xmlUrl) {
        StringBuilder html = new StringBuilder("<table border=\"1\"   cellpadding=\"5\" cellspacing=\"0\"> " + "\r\n");
        try {
            URL url = new URL(xmlUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String xml = response.toString();

            if (xml.contains("<prescriptions>")) {
                html.append(convertPrescriptionXmlToHtmlTable(xml));
            } else if (xml.contains("<messages>")) {
                html.append(convertMessageXmlToHtmlTable(xml));
            }

            html.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            return xmlUrl;
            // Returning the original string incase of error.
        }
        System.out.println(html.toString());
        return html.toString();
    }

    private static String convertPrescriptionXmlToHtmlTable(String xml) throws JAXBException {
        StringBuilder html = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance(Prescription_XML.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Prescription_XML prescriptionXml = (Prescription_XML) jaxbUnmarshaller.unmarshal(new InputSource(new java.io.StringReader(xml)));

        List<Prescription> prescriptions = prescriptionXml.getPrescription();
        for (Prescription prescription : prescriptions) {
            html.append("<tr>");
            html.append("<td>" + prescription.id + "</td>");
            html.append("<td>" + prescription.name + "</td>");
            html.append("<td>" + prescription.dosage + "</td>");
            html.append("<td>  <button type=\"submit\">Hold</button> ");
            html.append("</td>");
            html.append("</tr>");
        }

        return html.toString();
    }


   private static String convertMessageXmlToHtmlTable(String xml) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Message_XML.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    StringReader reader = new StringReader(xml);
    Message_XML messageXml = (Message_XML) jaxbUnmarshaller.unmarshal(reader);
    List<Message> messages = messageXml.getMessage();

    StringBuilder htmlTable = new StringBuilder();
    htmlTable.append("<table>");
    htmlTable.append("<tr><th>Message</th></tr>");
    for (Message message : messages) {
        htmlTable.append("<tr>");
        htmlTable.append("<td>").append(message.getMessage()).append("</td>");
        htmlTable.append("</tr>");
    }
    htmlTable.append("</table>");

    return htmlTable.toString();
}


private static String convertContentToHtml(String content) {
    StringBuilder htmlContent = new StringBuilder();
    try {
        Element root = getRootElement(content);
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == TEXT) {
                htmlContent.append(node.getTextContent());
            } else {
                htmlContent.append("<").append(node.getNodeName()).append(">");
                htmlContent.append(convertContentToHtml(node.getTextContent()));
                htmlContent.append("</").append(node.getNodeName()).append(">");
            }
        }
    } catch (Exception e) {
        htmlContent.append(content);
    }
    return htmlContent.toString();
}

private static Element getRootElement(String xml) throws Exception {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
    return document.getDocumentElement();
}

