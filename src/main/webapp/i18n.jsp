<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="/i18n?locale=es_ES">es_ES</a>

<%
  String locale = request.getParameter("locale");
  pageContext.setAttribute("locale", locale);
%>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n"/>
<fmt:message key="label.greeting" />
