/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Helper.PrescriptionsXML;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import Helper.*;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String passwrod) {
        if(username.equals("ben") || username.equals("faiz")){
        return true;
        }
        else{
            return false;
        }
    }

    public static PrescriptionsXML getServices(String query, String token) throws IOException {

        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget
                = searchclient.target("http://localhost:8080/Prescribe/webresources/prescribe/");
        InputStream is
                = searchwebTarget.path("update").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        PrescriptionsXML books = bookxmltoObjects(xml);
        if (token != null) {
            Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget
                    = holdclient.target("http://localhost:8080/Message/webresources/message/");
            for (Prescription book : books.getPrescriptions()) {

                InputStream holddata
                        = holdwebTarget.path(book.getName()).queryParam("token", token).
                                request(MediaType.APPLICATION_XML).get(InputStream.class);
                try{
                    Prescription a=bookholdxmltoObjects(IOUtils.toString(holddata, "utf-8"));
                    if(a!=null)
                        book.setTobePrescribed(true);
                    else
                        book.setTobePrescribed(false);
                }
                catch(Exception e){
                    book.setTobePrescribed(false);
                }
                
                
            }
        }

        return (books);

    }

    private static PrescriptionsXML bookxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PrescriptionsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            PrescriptionsXML books = (PrescriptionsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return books;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Prescription bookholdxmltoObjects(String xml) {
        if(xml.isEmpty())
            return null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Prescription.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Prescription book = (Prescription) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return book;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
