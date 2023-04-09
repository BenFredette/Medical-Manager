package your.package.name;

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
import helper.Prescription;
import helper.Prescription_XML;

public class Business {

    public static boolean isAuthenticated(String username, String passwrod) {
        return true;
    }

    public static Prescription_XML getServices(String query, String token) throws IOException {

        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget
                = searchclient.target("http://localhost:8080/Prescribe/webresources/prescribe/" + username);
        InputStream is
                = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        Prescription_XML prescriptions = prescriptionxmltoObjects(xml);
        if (token != null) {
            Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget
                    = holdclient.target("http://localhost:8080/Message/webresources/message/" + username);
            for (Prescription prescription : prescriptions.getPrescription()) {

                InputStream holddata
                        = holdwebTarget.path(prescription.getPrescriptionId()).queryParam("token", token).
                                request(MediaType.APPLICATION_XML).get(InputStream.class);
                try{
                    Prescription p=prescriptionholdxmltoObjects(IOUtils.toString(holddata, "utf-8"));
                    if(p!=null)
                        prescription.setTobeHold(true);
                    else
                        prescription.setTobeHold(false);
                }
                catch(Exception e){
                    prescription.setTobeHold(false);
                }
            }
        }

        return (prescriptions);

    }

    private static Prescription_XML prescriptionxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Prescription_XML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Prescription_XML prescriptions = (Prescription_XML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return prescriptions;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Prescription prescriptionholdxmltoObjects(String xml) {
        if(xml.isEmpty())
            return null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Prescription.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Prescription prescription = (Prescription) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return prescription;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
