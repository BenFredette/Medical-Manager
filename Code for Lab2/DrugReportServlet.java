import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DrugReportServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    HttpSession session = request.getSession();

    String drugName = request.getParameter("drugName");
    String sideEffect = request.getParameter("sideEffect");
    String uname = (String)session.getAttribute("uname");

    // Do something with the form data and the session attribute (e.g. store it in a database)

    // Redirect the user to a "thank you" page or back to the form
    response.sendRedirect("thankyou.jsp");
  }
}
