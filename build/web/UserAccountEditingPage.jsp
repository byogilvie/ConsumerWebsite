<%-- 
    Document   : UserAccountEdititngPage
    Created on : May 22, 2017, 2:44:26 PM
    Author     : bradl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account Editing Page></title>
        <link href='dist/css/bootstrap.min.css' rel='stylesheet'>
        <script src = "Walmartjs.js"></script>
    </head>
    <body>
        <%@ page import = "com.docmation.User" %>
        <% User user = (User) session.getAttribute("User");%>
        <div class="container">

            <div class = 'col-md-4 col-md-offset-4 col-sm-6'>
                <div class="well">
                    <form action="EditAccount" method="GET">
                        <div class="row">
                            <div class = "col-md-offset-3">
                                <img class="img-responsive img-circle col-md-8 col-sm-8"
                                     src="images/noProfile.jpg">
                            </div> 
                        </div>   
                        <div class = 'form-inline'>
                            <label for="firstName">First Name</label> <input
                                type="text" class="form-control" id="firstName"
                                placeholder="<%=user.firstName%>" name="firstName" 
                                value = "<%=user.firstName%>">
                            <label for="lastName">Last Name</label> <input
                                type="text" class="form-control" id="lastName"
                                placeholder="<%=user.lastName%>" name="lastName" 
                                value = "<%=user.lastName%>">
                        </div>
                        <div class="col-md-offset-1">
                            <div class="row">
                                <div class = 'form-group'>
                                    <label for="email">Email</label> <input
                                        type="text" class="form-control" id="email"
                                        placeholder="email" name="email" value ="<%=user.email%>"
                                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                                </div>
                            </div>
                        </div>
                        <div id='rates'></div>
                        <button type="submit" class="btn btn-default">Save Changes</button>
                    </form>
                </div>
            </div>
        </div>
        <script>
            buildCurrencyList('<%=user.currency%>');
        </script>
    </body>
</html>
