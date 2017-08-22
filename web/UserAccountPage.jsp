<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account Page</title>
        <link href='dist/css/bootstrap.min.css' rel='stylesheet'>
        <script src = "Walmartjs.js">
        </script>
    </head>
    <body>
        <%@ page import = "com.docmation.User" %>
        <% User user = (User) session.getAttribute("User");%>
        <div class="container">
            <div class = 'col-md-4 col-md-offset-4 col-sm-6'>
                <div class="well">

                    <div class="row">
                        <div class = "col-md-offset-3">
                            <img class="img-responsive img-circle col-md-8 col-sm-8" src="images/noProfile.jpg">
                        </div> 
                    </div>   
                    <div class="row col-md-offset-1">
                        <h1><%=user.firstName%> <%=user.lastName%></h1>
                    </div> 

                    <div class="col-md-offset-1">
                        <div class="row">
                            <h2><%=user.email%></h2>
                        </div>
                    </div>
                    <div class = "row">
                        <label>Preferred Currency:<%=user.currency%></label>
                        
                        </div>
                    <div class="row"><a href="WalmartCopy.jsp">Return Home</a>
                        <a href="UserAccountEditingPage.jsp" class="btn btn-info" role="button">Edit</a>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
