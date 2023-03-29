<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="now" value="<%= new java.util.Date() %>"/>
Time: ${now}

<%
  String[] fruits = {"Banana", "Mango", "Papaya", "Pumpkin"};
  pageContext.setAttribute("fruits", fruits);
%>

<c:forEach var="fruit" items="${fruits}">
  ${fruit}
</c:forEach>
