<%-- 
    Document   : patient
    Author     : Ben Fredette, Faiz
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="ryerson.ca.lab2.Drug"%>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Patient Page </title>
    <style>

      .message-section {
        text-align: center;
        margin-top: 20px;
      }
      .schedule-section {
        float: left;
        width: 100%;
        margin-top: 20px;
        margin-left: 20px;
      }
      .drug-section {
        float: left;
        width: 40%;
        margin-top: 20px;
        margin-left: 20px;
      }
      table {
        width: 100%;
        border-collapse: collapse;
      }
      th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
      }
      th {
        background-color: lightgray;
      }
    button {
        padding: 10px 20px;
        border: 2px solid black;
        border-radius: 5px;
        background-color: lightgray;
        font-size: 18px;
        cursor: pointer;
  }
  button:hover {
    background-color: lightgreen;
  }
</style>
</head>
<% 
    ArrayList<String> messages =(ArrayList)request.getAttribute("patientMessages");
    ArrayList<Drug> drugs= (ArrayList)request.getAttribute("patientDrugs");
  
%>
  <body>
    <div class="message-section">
        <%--Eventually will be patient's name instead--%>
        <h1>Hello <%=session.getAttribute("uname")%></h1>
      <h2>Messages</h2>
      <table id="message-table">
        <% for(String message : messages){ %>
        <tr>
            <td><%= message %></td>
        </tr>
        <% } %>
      </table>
    </div>
    <div class="schedule-section">
      <h2>Schedule</h2>
      <table id="schedule-table">
      <tr>
        <th></th>
        <th>Monday</th>
        <th>Tuesday</th>
        <th>Wednesday</th>
        <th>Thursday</th>
        <th>Friday</th>
      </tr>
      <tr>
        <th>8am</th>
        <td><button></button></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>9am</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>10am</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>11am</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>12pm</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>1pm</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>2pm</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>3pm</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>4pm</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>5pm</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>        
      </table>
      <button>Book Appointment</button>
    </div>
    <div class="drug-section">
      <h2>Drugs</h2>

<table id="drug-table">
  <tr>
    <th>Drug Name</th>
    <th>Dosage</th>
    <th>Report Side Effect</th>
    <th></th>
  </tr>
  <% for(Drug drug : drugs){ %>
    <tr>
      <form method="post" action ="DrugReport">
        <input type="hidden" name="drugName" value="<%= drug.getName() %>">
        <td><%= drug.getName() %></td>
        <td><%= drug.getDosage() %></td>
        <td>
          <input type="sideEffect" name="sideEffect" size="30">  
        </td>
        <td>
          <input type="submit" value="Send">
        </td>
      </form>
    </tr>
  <% } %>
</table>
    </div>
  </body>
</html>
