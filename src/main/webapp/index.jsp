<html>
<body>
  <!-- JSP Expression -->
  <%= new java.util.Date() %>
  <br/>

  <!-- JSP Declaration -->
  <%! String string = "Hello World"; %>
  <%!
    public String greet(String name){
      return String.format("%s, %s!", string, name);
    }
  %>
  <%= string %>
  <br/>

  <!-- JSP Scriptlet -->
  <%
    String name = "Jin";
    String message = greet(name);

    out.println(message);
  %>

  <%
    int number = 102;

    if(number % 2 == 0){
  %>
    <%= number %> is even.
  <%
    }
  %>
  </br>

  <!-- JSP Java Class -->
  <%
    java.util.Map<String, String> map = new java.util.HashMap<String, String>();
    map.put("userId", "102");
    map.put("postId", "201");
  %>
  <%= com.example.utils.Utility.interpolate("https://api.example.com/:userId/posts/:postId", map) %>
</body>
</html>
