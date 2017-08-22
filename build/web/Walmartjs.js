var itemsSelected = [];
var addToCart = function (id) {
    itemsSelected.push(id);
    cartItemsList[id] = itemList[id];
    console.log(cartItemsList[id]);
};
function addItemToCart(itemId, userName) {
    var url = "addItemToUserServ?userName=" + userName + "&itemId=" + itemId;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        console.log(request.readyState);
        console.log(request.status);
        if (request.readyState === 4) {
            var val = request.responseText;
            //document.getElementById('cartItems').innerHTML = val;
        }
    };
    request.open("GET", url, true);
    request.send();
}
function clearCart(username)
{
    var url = "CheckOut?userName=" + username;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        console.log(request.readyState);
        console.log(request.status);
        if (request.readyState === 4) {
            window.alert(request.responseText);
            window.location.assign("WalmartCopy.jsp");
            //document.getElementById('cartItems').innerHTML = val;
        }
    };
    request.open("POST", url, true);
    request.send();
}

function removeItemsFromCart(username, itemId)
{
    var numItems = document.getElementById("remove"+itemId).value;
    console.log(numItems);
    var url = "removeItem?userName=" + username + "&itemId=" + itemId + "&numItems=" + numItems;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        console.log(request.readyState);
        console.log(request.status);
        if (request.readyState === 4) {
            window.location.href = "userCart.jsp";
            //document.getElementById('cartItems').innerHTML = val;
        }
    };
    request.open("GET", url, true);
    request.send();
}
var itemList = {
    DogFood: {category: "Pets", price: "34.99", currency: "$", image: "1"},
    CatFood: {category: "Pets", price: "0.58", currency: "$", image: "2"},
    Bleach: {category: "Cleaner", price: "2.98", currency: "$", image: "3"},
    Detergent: {category: "Cleaner", price: "2.98", currency: "$", image: "4"},
    itemNames: ["DogFood", "CatFood", "Bleach", "Detergent"],
    addItem: function (name, category, price, currency, image)
    {
        this.itemNames.push(name);
        this[name] = {name: name,
            category: category,
            price: price,
            currency: currency,
            image: image};
    },
    deleteItem: function (name) {
        delete this[name];
    }
};
var addNewItem = function (name, category, price, currency, image)
{
    itemList.addItem(name, category, price, currency, image);
    constructList(itemList);
    
}

var cartItemsList = {};

function getPriceConversionRate() {
    var currency = document.getElementById("currency").value;
    console.log(currency);
    if (currency === "USD")
    {
        document.cookie = "curRate=1";
    } else {
        var url = "http://api.fixer.io/latest?base=USD&symbols=" + currency;
        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            console.log(request.readyState);
            console.log(request.status);
            if (request.readyState === 4) {
                var json = request.responseText;
                var obj = JSON.parse(json);
                console.log(obj.rates[currency]);
                document.cookie = "curRate="+obj.rates[currency];
            }
        };
        request.open("GET", url, true);
        request.send();
    }
}
;
var buildCurrencyList = function (currentCur)
{
    var rateList = [];
    var url = "http://api.fixer.io/latest?base=" + currentCur;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        console.log(request.readyState);
        console.log(request.status);
        if (request.readyState == 4) {
            var json = request.responseText;
            var obj = JSON.parse(json);
            rateList = Object.keys(obj.rates);
            make();
            function make()
            {
                console.log(currentCur);
                var output = "";
                output += "<label for='currency'>Preferred Currency: <\/label>" + "\n";
                output += "<select name='currency' id = 'currency' onchange = 'getPriceConversionRate()'>" + "\n";
                output += "<option value=" + currentCur + " selected>"+ currentCur+
                                "<\/option>" + "\n";
                for (var i = 0; i < rateList.length; i++)
                {
                    output += "<option value=" + rateList[i] + ">" + rateList[i]+"<\/option>" + "\n";
                }
                output += "<\/select>";
                document.getElementById('rates').innerHTML = output;
            }
        }
    };
    request.open("GET", url, true);
    request.send();

};
function getConversionRate() {
    var bc = document.getElementById("bc").value;
    var tc = document.getElementById("tc").value;
    var url = "http://api.fixer.io/latest?base=" + bc + "&symbols=" + tc;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        console.log(request.readyState);
        console.log(request.status);
        if (request.readyState == 4) {
            var json = request.responseText;
            console.log("JSON string:" + json);
            var obj = JSON.parse(json);
            console.log(obj);
            console.log(obj.rates);
            document.getElementById('rate').innerHTML = obj.rates;
        }
    };
    request.open("GET", url, true);
    request.send();
}

var constructList = function (list)
{
    
};
/*var output = "";
    var itemsLens = itemList.itemNames.length;
    var itemListDiv = document.getElementById("itemListDiv");
    for (var i = 0; i < itemsLens; i++)
    {
        currentItem = itemList.itemNames[i];
        console.log(list[currentItem]);
        var image = list[currentItem].image;
        output += "<div id = '" + currentItem + "'>";
        //output += "<div class = 'row'>";
        output += "<div class = 'col-md-4 col-sm-6 col-xs-12'>";
        output += "<h3>" + currentItem + "<\/h3>";
        output += '<button type = "button" class="btn btn-primary" onClick = "addToCart(\'' + currentItem + '\');" value="submit button"\/>Add to cart<\/button>';
        output += "<img src = 'images\\" + image + ".jpg'>";
        output += "<\/div>";
        //output += "<\/div>";
        output += "<\/div>";
    }
    itemListDiv.innerHTML = output;
var constructCart = function (list)
{
    var output = "";
    var itemsLens = itemsSelected.length;
    var itemListDiv = document.getElementById("itemListDiv");
    for (var i = 0; i < itemsLens; i++)
    {
        currentItem = itemsSelected[i];
        console.log(list[currentItem]);
        var image = list[currentItem].image;
        output += "<div class = 'col-md-4' id = '" + currentItem + "'>";
        output += "<h3>" + currentItem + "<\/h3>";
        output += "<div class = 'col-md-4'>";
        output += "<img src = 'images\\" + image + ".jpg'>";
        output += "<\/div>";
        //output += '<input id = "addBtn" type = "button" onClick = "addToCart(\''+currentItem+'\');" value="Add"\/>';
        output += "<\/div>";
    }
    itemListDiv.innerHTML = output;
};
//console.log(output);
/*var names = ["Dog Food", "Cat Food", "Bleach", "Detergent"];
 var images = ["1", "2", "3", "4"];
 for(int i = 0; i < 4; i++)
 {
 document.writeln("");
 document.writeln("<h3>"+names[i]+"</h3>");
 document.writeln("<img src='images/"+images[i]+".jpeg>'");
 
 }*/