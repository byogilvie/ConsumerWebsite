<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='dist/css/bootstrap.min.css' rel='stylesheet'>

        <script src = "Walmartjs.js">
        </script>
        <script src = "money.js">
        </script>
        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
        <script src='dist/js/bootstrap.js'></script>
        <script>
            function readCookie(name) {
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for (var i = 0; i < ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0) === ' ')
                        c = c.substring(1, c.length);
                    if (c.indexOf(nameEQ) === 0)
                        return parseFloat(c.substring(nameEQ.length, c.length));
                }
                return null;
            }
        </script>
    </head>
    <body>
        <jsp:useBean id= "DBItems" class= "com.docmation.DBItems" />
        <%@ page import = "com.docmation.User" %>
        <%@ page import = "com.docmation.Item" %>
        <%@ page import = "java.util.ArrayList" %>
        <%@ page import = "java.lang.Math" %>

        <%
            User user = (User) session.getAttribute("User");
            String name = user.userName;
        %>

        <script>console.log('<%=user.currency%>');
            var rate = readCookie("curRate");
        </script>

        <%ArrayList<String> itemList = DBItems.getUserItems(name);%>

        <br>
        <div class = "container">
            <nav class = 'navbar navbar-default'>
                <div class='container-fluid'>
                    <div class='navbar-header'>
                        <a class="navbar-brand" href = 'WalmartCopy.jsp'>
                            Walmart</a>
                    </div>
                </div>
            </nav>
            <hr>
            <table class="table table-responsive">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th></th>         
                        <th>Item Price(<%=user.currency%>)</th>
                        <th>Quantity</th>
                        <th>Total Item Price</th>
                    </tr>
                </thead>
                <tbody>



                <script>
                    var totalPrice = 0.00;
                    console.log(rate);
                </script>
                <%
                    int totalItems = 0;
                    for (int i = 0; i < itemList.size(); i++) {
                        Item item = DBItems.findItem(itemList.get(i));
                        double price = Double.parseDouble(item.price);
                        int quantity = DBItems.getNumItems(name, itemList.get(i));
                        totalItems += quantity;
                %>
                <script>
                    var scrPrice = rate *<%=price%>;
                    var totalItemPrice = scrPrice *<%=quantity%>;
                    totalPrice = totalPrice + totalItemPrice;

                </script>

                <tr>
                    <td><%=item.itemName%></td>
                    <td><img class="img-responsive" src="<%=item.image_URL%>" width = "50px" height="50px"></td>
                    <td><script type="text/javascript">document.write(scrPrice.toFixed(2));</script></td>
                    <td><%=quantity%></td>
                    <td><script type="text/javascript">document.write(totalItemPrice.toFixed(2));</script></td>
                    <td>
                        <input type="number" id = "remove<%=item.item_ID%>" min="1" max="<%=quantity%>" value='1'>
                        <input id="removeButton" type="button" class='btn btn-info' value='Remove'
                               onClick = "removeItemsFromCart('<%=user.userName%>', 
                                           '<%=item.item_ID%>')">
                    </td>
                </tr>
                <%
                    }
                %>
                <tr class="active">
                    <td>Total:</td>
                    <td></td>
                    <td></td>
                    <td><%=totalItems%></td>
                    <td><script type="text/javascript">document.write(totalPrice.toFixed(2));</script></td>
                    <td><input id="addBtn" type="button" class='btn btn-info' value='Check Out'
                               onClick = "clearCart('<%=user.userName%>')"></td>
                </tr>
                </tbody>
            </table>

        </div>

    </body>
</html>
