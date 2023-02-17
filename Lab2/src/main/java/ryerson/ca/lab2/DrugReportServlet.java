/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "DrugReportServlet", urlPatterns = {"/DrugReport"})
public class DrugReportServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    HttpSession session = request.getSession();

    String drugName = request.getParameter("drugName");
    String sideEffect = request.getParameter("sideEffect");
    String uname = (String)session.getAttribute("uname");

    // use uname to find user so that we can send message to
    // their doctor (Cannot be properly implemented until database)

    // Redirect the user back the the patient page
    response.sendRedirect("drugReported.jsp");
  }
}
