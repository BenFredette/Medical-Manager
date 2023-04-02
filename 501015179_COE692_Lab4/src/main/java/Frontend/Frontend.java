package Frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.Business;
import Business.XMLParser;

@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }

    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty()) {
            try {
                if (this.autho.verify(token).getKey()) {
                    Map.Entry entry = new AbstractMap.SimpleEntry<String, String>(token, this.autho.verify(token).getValue());
                    return entry;
                } else {
                    Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
                    return entry;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
        return entry;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        switch (hiddenParam) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean isAuthenticated = Business.isAuthenticated(username, password);
                if (isAuthenticated) {
                    request.setAttribute("username", username);
                    token = autho.createJWT("FrontEnd", username, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("loginpage.jsp");

                    requestDispatcher.forward(request, response);

                } else {
                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("index.html");

                    requestDispatcher.forward(request, response);
                }
                break;
            case "search":
                String query = request.getParameter("query");
                String microserviceURL = "";
                if (request.getParameter("prescription") != null) {
                    microserviceURL = "http://localhost:8080/Prescribe/webresources/prescribe/" + query;
                } else if (request.getParameter("message") != null) {
                    microserviceURL = "http://localhost:8080/MessageService/webresources/message/" + query;
                } else {
                // handle invalid search request
                    response.getWriter().write("Invalid search request.");
                    return;
                }
                            // send HTTP GET request to the appropriate microservice
            URL url = new URL(microserviceURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // check if the response code is valid
            int status = con.getResponseCode();
            if (status != 200) {
                // handle error response from microservice
                response.getWriter().write("Error response from microservice.");
                return;
            }

            // read the response from microservice and write it back to the client
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(content.toString());
            break;
        default:
            // handle invalid request
            response.getWriter().write("Invalid request.");
            break;
    }
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
}
}

