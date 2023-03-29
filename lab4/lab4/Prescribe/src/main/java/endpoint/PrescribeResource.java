/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import business.PrescribeBusiness;
import helper.Prescription_XML;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("prescribe/{query}")
public class PrescribeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PrescribeResource
     */
    public PrescribeResource() {
    }

    /**
     * Retrieves representation of an instance of endpoint.PrescribeResource
     * @param query
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        //TODO return proper representation object
        PrescribeBusiness prescribe = new PrescribeBusiness();
        Prescription_XML prescriptions = prescribe.getPrescriptionsUser(query);
        System.out.println(">>>>>>>>>>>>>>>>" + prescriptions);
        
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(Prescription_XML.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(prescriptions, sw);
            
            return(sw.toString());
        } catch (JAXBException ex) {
              Logger.getLogger(PrescribeResource.class.getName()).log(Level.SEVERE, null, ex);
              return("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of PrescribeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
