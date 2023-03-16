/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author student
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import Persistance.UserDatabase;
import Persistance.MessageDatabase;
import Persistance.PrescriptionDatabase;
import Helper.Doctor;
@WebServlet(name = "Prescribe", urlPatterns = {"/Prescribe"})
public class DrugPrescriber extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    HttpSession session = request.getSession();

    int prescriptionId = Integer.parseInt(request.getParameter("prescription_id"));
    String drugName = request.getParameter("drug");
    String dosage = request.getParameter("instructions");
    String username = request.getParameter("username");
    String uname = (String)session.getAttribute("uname");
    String pass = (String)session.getAttribute("password");
    
    // use uname to find doctor   
    UserDatabase ub = new UserDatabase();
    Doctor doctor = ub.doctorRead(uname, pass);
    //Send message to patient's doctor
    String message = doctor.getName()+" has prescribed you "+drugName+". Check drug section for details";
    //Write to database
    PrescriptionDatabase pd = new PrescriptionDatabase();
    pd.writePrescription(prescriptionId, drugName, dosage, username);
    MessageDatabase md = new MessageDatabase();
    md.writeMessage(username, message);
    // Show that drug has been prescribed
    response.sendRedirect("prescribed.jsp");
  }
}
