/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import Persistance.UserDatabase;
import Persistance.MessageDatabase;
import Helper.Patient;
@WebServlet(name = "Report", urlPatterns = {"/Report"})
public class ReportCreator extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    HttpSession session = request.getSession();

    String drugName = request.getParameter("drugName");
    String sideEffect = request.getParameter("sideEffect");
    String uname = (String)session.getAttribute("uname");
    String pass = (String)session.getAttribute("password");
    
    // use uname to find user so that we can send message to   
    UserDatabase ub = new UserDatabase();
    Patient patient = ub.patientRead(uname, pass);
    System.out.println("Got patient "+patient.getName());
    //Send message to patient's doctor
    String message = patient.getName()+" has reported the following side effect from "+drugName+": "+sideEffect;
    //patient.sendMessage(message);
    //Write to database
    MessageDatabase md = new MessageDatabase();
    md.writeMessage(patient.getDoctorUsername(), message);
    // Show that drug has been reported
    response.sendRedirect("drugReported.jsp");
  }
}

