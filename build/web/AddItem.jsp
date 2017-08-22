<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item</title>
        <link href='dist/css/bootstrap.min.css' rel='stylesheet'>

        <script src = "Walmartjs.js">
        </script>
        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
        <script src='dist/js/bootstrap.js'></script>
        <style>
            body{
                background-color: blue;
            }
            .well
            {
                margin-top: 100px;
            }
            </style>
    </head>
    <body>
        <nav class = 'navbar navbar-default'>
                <div class='container-fluid'>
                    <div class='navbar-header'>
                        <a class="navbar-brand" href = 'WalmartCopy.jsp'>
                            Walmart</a>
                    </div>
                </div>
            </nav>
        <div class="container">
            <div class="well">
            <form action = "addItemServ" method = "GET">
                <div class="form-group">
                    <label for="itemName">Name of new item</label> 
                    <input type="text" class="form-control" id="itemName"
                           placeholder="Item Name" name="itemName">
                </div>
                <div class="form-group">
                    <label for="item_ID">ID of new item</label> 
                    <input type="text" class="form-control" id="item_ID"
                           placeholder="Item ID" name="item_ID">
                </div>
                <div class="form-group">
                    <label for="price">Price of new item</label> 
                    <input type="text" class="form-control" id="itemPrice"
                           placeholder="Item Price" name="itemPrice">
                </div>
                <div class="form-group">
                    <label for="item_URL">URL of the image of new item</label> 
                    <input type="text" class="form-control" id="item_URL"
                           placeholder="Item Image URL" name="item_URL">
                </div>
                <button type="submit" class="btn btn-default">Add Item</button>
            </form>
                </div>
        </div>
    </body>
</html>
