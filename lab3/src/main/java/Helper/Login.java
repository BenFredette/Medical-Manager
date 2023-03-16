
package Helper;

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
 *
 * @author student
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
        
        String username=(String) request.getParameter("username");
        String password=(String) request.getParameter("password");
        String account_type = (String) request.getParameter("account_type");
        
        if(account_type.equals("Patient")){
        Patient patient = getPatientInfo(username, password);
        System.out.println(patient.getUsername());
        if (patient==null){
            System.out.println("Oops, patient was null:/");
            RequestDispatcher rd= request.getRequestDispatcher("loginfailed.jsp");
            rd.forward(request, response);
        }
        else{
            request.getSession().setAttribute("uname", username);
            request.getSession().setAttribute("password", password);
            request.setAttribute("patientMessages", patient.getMessages());
            request.setAttribute("patientDrugs", patient.getDrugs());
            
            RequestDispatcher rd= request.getRequestDispatcher("patient.jsp");
            rd.forward(request, response);
            
        }
        }else if(account_type.equals("Admin")){
            if(username.equals("admin") && password.equals("admin")){
                RequestDispatcher rd= request.getRequestDispatcher("admin.jsp");
                rd.forward(request, response);
                
            }else{
                 RequestDispatcher rd= request.getRequestDispatcher("loginfailed.jsp");
                 rd.forward(request, response);      
            }
        }else if(account_type.equals("Doctor")){
            Doctor doctor = getDoctorInfo(username, password);
            System.out.println("Doctor is "+doctor.getName());
            if(doctor == null){
                System.out.println("Doctor is null");
                RequestDispatcher rd= request.getRequestDispatcher("loginfailed.jsp");
                rd.forward(request, response);
            }else{
              request.getSession().setAttribute("uname", username);
              request.getSession().setAttribute("password", password);
              request.setAttribute("doctorMessages", doctor.getMessages());  
              
              RequestDispatcher rd= request.getRequestDispatcher("doctor.jsp");
              rd.forward(request, response);
            
        }
        }
        
        
     
    }

    private Patient getPatientInfo(String uname, String password) {
        /**
         * to be completed. For now, we just return a patient object that has a default message;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all information about the patient
         */

        //search the database for matching username
        UserDatabase ud = new UserDatabase();
        Patient patient = ud.patientRead(uname, password);
        MessageDatabase md = new MessageDatabase(); 
        patient.messages = md.messageRead(uname);
        PrescriptionDatabase pd = new PrescriptionDatabase();       
        patient.drugs = pd.prescriptionRead(uname);
        
        //Doctor exDoctor = new Doctor("Dr. Example","Doctory password","DRE", "Dr@tmu.ca");
        //Patient patient = new Patient(uname, uname, password, "Ex@tmu.ca",exDoctor);
        //exDoctor.sendMessage(patient, "Welcome to our hospital, my name is "+exDoctor.name+" and I will be your doctor");
        //Prescription exDrug = new Prescription(0,"Example Drug", "This would be where your doctor would leave you instructions");
        //exDoctor.prescribeDrug(patient, exDrug);
   
        return patient;
    }
    
    private Doctor getDoctorInfo(String uname, String password){
        UserDatabase ud = new UserDatabase();
        Doctor doctor = ud.doctorRead(uname, password);
        MessageDatabase md = new MessageDatabase();
        doctor.messages = md.messageRead(uname);
        //In future could add way to get doctors patients here if necessary
        return doctor;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }

}
