<!DOCTYPE html>
<html>
    <head>
        <title>Walmart Copy</title>
        <!--<link type="text/css" rel="stylesheet" href="WalmartHomeCss.cs">-->
        <link href='dist/css/bootstrap.min.css' rel='stylesheet'>
        <script src = "Walmartjs.js">
        </script>
        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
        <script src='dist/js/bootstrap.js'></script>
        <script>
            //Reads cookie containing rate
            function readCookie(name) {
                var value = name + "=";
                var ca = document.cookie.split(';');
                for (var i = 0; i < ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0) === ' ')
                        c = c.substring(1, c.length);
                    if (c.indexOf(value) === 0)
                        return c.substring(value.length, c.length);
                }
                return null;
            }
        </script>
    </head>

    <body>
        <%@ page import = "com.docmation.Item" %>
        <%@ page import = "java.util.ArrayList" %>
        <%@ page import = "com.docmation.User" %>
        <script>
            var rate = 1;
            var currentCur = "USD";
        </script>
        <% User user = (User) session.getAttribute("User");
            if (user != null) {
        %>
        <script>
            rate = parseFloat(readCookie('curRate'));
            currentCur = '<%=user.currency%>';
        </script>
        <%
            }
        %>
        <div class='container-fluid'>
            <nav class = 'navbar navbar-default'>
                <div class='container-fluid'>
                    <div class='navbar-header'>
                        <a class="navbar-brand" href = 'WalmartCopy.jsp' class = 'navbar-brand'>
                            Walmart</a>
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span> 
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href = '#' data-toggle = 'dropdown'>Departments<span class = 'caret'></span></a>
                                <ul class = 'dropdown-menu'>
                                    <li><a href = "#">Pets</a></li>
                                    <li><a href = "#">Cleaners</a></li>
                                    <li><a href = "#">Games</a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class = 'nav navbar-nav navbar-right'>
                            <%
                                if (user == null) {
                            %>
                            <li><a href = "WalmartLogin.html"><span class="glyphicon glyphicon-user"></span>Sign In</a></li>
                            <li><a href = "walmartRegistration.html">Registration</a></li>
                                <%
                                } else {
                                %>
                            <li><a href="UserAccountPage.jsp">My Account</a></li>
                            <li><a href = "userCart.jsp">My Cart<span class="glyphicon glyphicon-shopping-cart"></span></a></li>
                            <li>
                                <a href= "CheckLogout" method= 'POST'>Log Out</a>
                            </li>
                            <%
                                if (user.isAdmin) {
                            %>
                            <li><a href = "AddItem.jsp">Create Item</a></li>
                                <%
                                        }
                                    }

                                %>
                        </ul>
                    </div>
                </div>
            </nav>
            <br>
            <jsp:useBean id= "DBItems" class= "com.docmation.DBItems" />
            <!--<button id = "addNewItem" onClick="addNewItem('Monopoly', 'Games', '20.00', '$', '5');">Add New Item</button>-->
            <h2>Sponsored products you may like</h2>
            <div id = "itemListDiv">
                <hr>
                <%                    ArrayList<Item> itemList = DBItems.getItems();
                    for (int i = 0; i < itemList.size(); i++) {
                        Item i1 = (Item) itemList.get(i);
                %>
                <script>
                    var scrPrice = rate *<%=i1.price%>;
                </script>
                <div class = 'col-md-3 col-sm-6 col-xs-12' id = '<%=i1.itemName%>'>
                    <h3><%=i1.itemName%></h3>
                    <h4><script type="text/javascript">document.write(scrPrice.toFixed(2));</script> 
                        <script>document.write(currentCur);</script>
                        <%
                            if (user != null) {
                                String name = user.userName;
                        %>
                        <input id="addBtn" type="button" class='btn btn-info' value='Add' onClick = "addItemToCart(<%=i1.item_ID%>, '<%=name%>')">
                        <% }%></h4>
                    <img class="img-responsive col-md-6 col-sm-8" src="<%=i1.image_URL%>" width="150" height="150">

                </div>
                <%
                    }
                %>
            </div>
            <!--
            <script>
                constructList(itemList);
                //document.getElementById("itemListDiv").innerHTML = "test";
            </script>
            -->
        </div>
    </body>
</html>