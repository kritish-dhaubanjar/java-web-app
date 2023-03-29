<form action="/session" method="POST">
  <input type="text" name="todo"/>
  <button type="submit">Add</button>
</form>

<%@ page import="java.util.List, java.util.ArrayList" %>

<%
  out.println(session.getId());
  List<String> todos = (ArrayList<String>)session.getAttribute("todos");
%>


<%
  String todo = request.getParameter("todo");

  if(todos == null) {
    todos = new ArrayList<String>();
  }

  if(todo != null) {
    todos.add(todo);
  }

  session.setAttribute("todos", todos);

  out.println(todos);
%>

<ul>
  <%
    for(String _todo : todos){
  %>
    <li><%= _todo %></li>
  <%
    }
  %>
</ul>
