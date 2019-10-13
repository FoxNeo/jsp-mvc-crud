<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Employee</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
   integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
   crossorigin="anonymous">
</head>
<body>
   <div class="container">

      <h1>Employee Directoy</h1>
      <div class="row">
         <div class="col-md-6">
            <form
               action="${pageContext.request.contextPath}/EmployeeController"
               method="post">
               <input class="form-control" placeholder="Enter name" type="text" name="firstname"
                  value="${employee.name}" /></br>
                  <input class="form-control" placeholder="Enter Date of birth" type="date" name="dob" value="${employee.dob}" /></br>
                  <input class="form-control" placeholder="Enter Department" type="text" name="department" value="${employee.department}" /></br>
                  <input type="hidden" value="${employee.id}" name="id" />
               <button class="btn btn-primary" type="submit">Save
                  Employee</button>
            </form>
         </div>
      </div>
   </div>
</body>
</html>