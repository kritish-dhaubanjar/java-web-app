<%
  String[] payload = (String[])request.getAttribute("payload");
  for(String p: payload){
    out.println(p);
  }
%>
