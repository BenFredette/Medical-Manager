package Helper;

import Frontend.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Persistance.UserDatabase;
import Persistance.MessageDatabase;
import Persistance.PrescriptionDatabase;

/**
 * This class handles user login.
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String account_type = request.getParameter("account_type");

        // Call Authenticate class to verify user credentials and generate token
        String token = Authenticate.authenticateUser(username, password, account_type);

        // If token is null, then the user failed authentication, redirect to loginfailed.jsp
        if (token == null) {
            RequestDispatcher rd = request.getRequestDispatcher("loginfailed.jsp");
            rd.forward(request, response);
            return;
        }

        // If token is not null, store it in the session and continue to appropriate page
        request.getSession().setAttribute("token", token);

         if (account_type.equals("Patient")) {
        Patient patient = getPatientInfo(username, password);
        if (patient == null) {
            RequestDispatcher rd = request.getRequestDispatcher("loginfailed.jsp");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("uname", username);
            request.getSession().setAttribute("password", password);
            response.sendRedirect("http://localhost:8080/Prescribe/webresources/prescribe/" + username);
        }
        } else if (account_type.equals("Admin")) {
            if (username.equals("admin") && password.equals("admin")) {
                RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                rd.forward(request, response);

            } else {
                RequestDispatcher rd = request.getRequestDispatcher("loginfailed.jsp");
                rd.forward(request, response);
            }
        } else if (account_type.equals("Doctor")) {
            Doctor doctor = getDoctorInfo(username, password);
            System.out.println("Doctor is " + doctor.getName());
            if (doctor == null) {
                System.out.println("Doctor is null");
                RequestDispatcher rd = request.getRequestDispatcher("loginfailed.jsp");
                rd.forward(request, response);
            } else {
                request.getSession().setAttribute("uname", username);
                request.getSession().setAttribute("password", password);
                response.sendRedirect("http://localhost:8080/Prescribe/webresources/prescribe/");
                //request.setAttribute("doctorMessages", doctor.getMessages());

                //RequestDispatcher rd = request.getRequestDispatcher("doctor.jsp");
                //rd.forward(request, response);

            }
        }
    }

    private Patient getPatientInfo(String uname, String password) {
        /**
         * to be completed. For now, we just return a patient object that has a default message;
         * This method must return null when user name and password is incorrect
         **/
    //search the database for matching username and password
    UserDatabase ud = new UserDatabase();
    Patient patient = ud.patientRead(uname, password);

    // check if the patient is found
    if (patient == null) {
        return null; // return null if patient is not found
    }

    // get patient's messages from database
    MessageDatabase md = new MessageDatabase(); 
    patient.messages = md.messageRead(uname);

    // get patient's prescriptions from database
    PrescriptionDatabase pd = new PrescriptionDatabase();       
    patient.drugs = pd.prescriptionRead(uname);

    return patient; // return the patient object
}
}