package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.docmation.Item;
import java.util.ArrayList;
import com.docmation.User;

public final class WalmartCopy_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Walmart Copy</title>\r\n");
      out.write("        <!--<link type=\"text/css\" rel=\"stylesheet\" href=\"WalmartHomeCss.cs\">-->\r\n");
      out.write("        <link href='dist/css/bootstrap.min.css' rel='stylesheet'>\r\n");
      out.write("        <script src = \"Walmartjs.js\">\r\n");
      out.write("        </script>\r\n");
      out.write("        <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>\r\n");
      out.write("        <script src='dist/js/bootstrap.js'></script>\r\n");
      out.write("        <script>\r\n");
      out.write("            //Reads cookie containing rate\r\n");
      out.write("            function readCookie(name) {\r\n");
      out.write("                var value = name + \"=\";\r\n");
      out.write("                var ca = document.cookie.split(';');\r\n");
      out.write("                for (var i = 0; i < ca.length; i++) {\r\n");
      out.write("                    var c = ca[i];\r\n");
      out.write("                    while (c.charAt(0) === ' ')\r\n");
      out.write("                        c = c.substring(1, c.length);\r\n");
      out.write("                    if (c.indexOf(value) === 0)\r\n");
      out.write("                        return c.substring(value.length, c.length);\r\n");
      out.write("                }\r\n");
      out.write("                return null;\r\n");
      out.write("            }\r\n");
      out.write("        </script>\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        <script>\r\n");
      out.write("            var rate = 1;\r\n");
      out.write("            var currentCur = \"USD\";\r\n");
      out.write("        </script>\r\n");
      out.write("        ");
 User user = (User) session.getAttribute("User");
            if (user != null) {
        
      out.write("\r\n");
      out.write("        <script>\r\n");
      out.write("            rate = parseFloat(readCookie('curRate'));\r\n");
      out.write("            currentCur = '");
      out.print(user.currency);
      out.write("';\r\n");
      out.write("        </script>\r\n");
      out.write("        ");

            } 
            else
            {
        
      out.write("\r\n");
      out.write("        <script>\r\n");
      out.write("            rate = parseFloat(1);\r\n");
      out.write("            currentCur = 'USD';\r\n");
      out.write("        </script>\r\n");
      out.write("        ");

            }
        
      out.write("\r\n");
      out.write("        <div class='container-fluid'>\r\n");
      out.write("            <nav class = 'navbar navbar-default'>\r\n");
      out.write("                <div class='container-fluid'>\r\n");
      out.write("                    <div class='navbar-header'>\r\n");
      out.write("                        <a class=\"navbar-brand\" href = 'WalmartCopy.jsp' class = 'navbar-brand'>\r\n");
      out.write("                            Walmart</a>\r\n");
      out.write("                        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">\r\n");
      out.write("                            <span class=\"icon-bar\"></span>\r\n");
      out.write("                            <span class=\"icon-bar\"></span>\r\n");
      out.write("                            <span class=\"icon-bar\"></span> \r\n");
      out.write("                        </button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\r\n");
      out.write("                        <ul class=\"nav navbar-nav\">\r\n");
      out.write("                            <li><a href = '#' data-toggle = 'dropdown'>Departments<span class = 'caret'></span></a>\r\n");
      out.write("                                <ul class = 'dropdown-menu'>\r\n");
      out.write("                                    <li><a href = \"#\">Pets</a></li>\r\n");
      out.write("                                    <li><a href = \"#\">Cleaners</a></li>\r\n");
      out.write("                                    <li><a href = \"#\">Games</a></li>\r\n");
      out.write("                                </ul>\r\n");
      out.write("                            </li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <ul class = 'nav navbar-nav navbar-right'>\r\n");
      out.write("                            ");

                                if (user == null) {
                            
      out.write("\r\n");
      out.write("                            <li><a href = \"WalmartLogin.html\"><span class=\"glyphicon glyphicon-user\"></span>Sign In</a></li>\r\n");
      out.write("                            <li><a href = \"walmartRegistration.html\">Registration</a></li>\r\n");
      out.write("                                ");

                                } else {
                                
      out.write("\r\n");
      out.write("                            <li><a href=\"UserAccountPage.jsp\">My Account</a></li>\r\n");
      out.write("                            <li><a href = \"userCart.jsp\">My Cart<span class=\"glyphicon glyphicon-shopping-cart\"></span></a></li>\r\n");
      out.write("                            <li>\r\n");
      out.write("                                <a href= \"CheckLogout\" method= 'POST'>Log Out</a>\r\n");
      out.write("                            </li>\r\n");
      out.write("                            ");

                                if (user.isAdmin) {
                            
      out.write("\r\n");
      out.write("                            <li><a href = \"AddItem.jsp\">Create Item</a></li>\r\n");
      out.write("                                ");

                                        }
                                    }

                                
      out.write("\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </nav>\r\n");
      out.write("            <br>\r\n");
      out.write("            ");
      com.docmation.DBItems DBItems = null;
      synchronized (_jspx_page_context) {
        DBItems = (com.docmation.DBItems) _jspx_page_context.getAttribute("DBItems", PageContext.PAGE_SCOPE);
        if (DBItems == null){
          DBItems = new com.docmation.DBItems();
          _jspx_page_context.setAttribute("DBItems", DBItems, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("            <!--<button id = \"addNewItem\" onClick=\"addNewItem('Monopoly', 'Games', '20.00', '$', '5');\">Add New Item</button>-->\r\n");
      out.write("            <h2>Sponsored products you may like</h2>\r\n");
      out.write("            <div id = \"itemListDiv\">\r\n");
      out.write("                <hr>\r\n");
      out.write("                ");
                    ArrayList<Item> itemList = DBItems.getItems();
                    for (int i = 0; i < itemList.size(); i++) {
                        Item i1 = (Item) itemList.get(i);
                
      out.write("\r\n");
      out.write("                <script>\r\n");
      out.write("                    var scrPrice = rate *");
      out.print(i1.price);
      out.write(";\r\n");
      out.write("                </script>\r\n");
      out.write("                <div class = 'col-md-3 col-sm-6 col-xs-12' id = '");
      out.print(i1.itemName);
      out.write("'>\r\n");
      out.write("                    <h3>");
      out.print(i1.itemName);
      out.write("</h3>\r\n");
      out.write("                    <h4><script type=\"text/javascript\">document.write(scrPrice.toFixed(2));</script> \r\n");
      out.write("                        <script>document.write(currentCur);</script>\r\n");
      out.write("                        ");

                            if (user != null) {
                                String name = user.userName;
                        
      out.write("\r\n");
      out.write("                        <input id=\"addBtn\" type=\"button\" class='btn btn-info' value='Add' onClick = \"addItemToCart(");
      out.print(i1.item_ID);
      out.write(", '");
      out.print(name);
      out.write("')\">\r\n");
      out.write("                        ");
 }
      out.write("</h4>\r\n");
      out.write("                    <img class=\"img-responsive col-md-6 col-sm-8\" src=\"");
      out.print(i1.image_URL);
      out.write("\" width=\"150\" height=\"150\">\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                ");

                    }
                
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <!--\r\n");
      out.write("            <script>\r\n");
      out.write("                constructList(itemList);\r\n");
      out.write("                //document.getElementById(\"itemListDiv\").innerHTML = \"test\";\r\n");
      out.write("            </script>\r\n");
      out.write("            -->\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
