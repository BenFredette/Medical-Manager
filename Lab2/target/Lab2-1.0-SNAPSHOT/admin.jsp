<%-- 
    Document   : admin
    Created on : Feb 16, 2023, 1:19:48 AM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
      <title>Admin Page</title>
    <style>
      .container {
        width: 80%;
        margin: 50px auto;
        text-align: center;
        background-color: #f2f2f2;
        padding: 30px;
        box-shadow: 0 0 10px #999;
        border-radius: 10px;
      }
      
      h1 {
        font-size: 36px;
        margin-bottom: 20px;
        color: #333;
      }
      
      form {
        margin-bottom: 20px;
        background-color: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px #999;
      }
      
      input[type="text"],
      select {
        padding: 10px;
        font-size: 16px;
        margin-bottom: 20px;
        width: 100%;
        border-radius: 5px;
        border: none;
        box-shadow: 0 0 5px #999;
      }
      
      input[type="submit"] {
        padding: 10px 20px;
        background-color: green;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        float: right;
      }
      
      table {
        width: 100%;
        text-align: left;
        margin-top: 20px;
        border-collapse: collapse;
      }
      
      th, td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
      }
      
      th {
        background-color: #333;
        color: white;
      }
      
      td button {
        background-color: red;
        color: white;
        padding: 5px 10px;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        float: right;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Add/Remove Patients or Doctors</h1>
      <form>
        <!-- Form for adding a new patient or doctor -->
        <h2>Add Patient/Doctor</h2>
        <input type="text" placeholder="Name">
        <input type="text" placeholder="Email">
        <select>
          <option value="patient">Patient</option>
          <option value="doctor">Doctor</option>
        </select>
        <input type="submit" value="Add">
      </form>
      <table>
        <!-- Table for displaying current patients and doctors -->
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Type</th>
          <th>Action</th>
        </tr>
        <tr>
          <td>Randal Smith</td>
          <td>randal@randycare.com</td>
          <td>Patient</td>
          <td><button>Remove</button></td>
        </tr>
        <tr>
          <td>Dr. Bobby Fisch</td>
          <td>drbobf@randycare.com</td>
          <td>Doctor</td>
          <td><button>Remove</button></td>
        </tr>
      </table>
    </div>
  </body>
</html>
