<%--
  Created by IntelliJ IDEA.
  User: louis
  Date: 20/04/2023
  Time: 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Villes de France</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/listeVilles.css">
</head>
<body>
    <h1>Liste des Villes de France</h1>

    <h3>
        <c:out value="${page}/${nbPages}"/>
    </h3>

    <form method="post" action="ListeVilles">
        <p>
            <label for="page">Page</label>
            <input type="text" id="page" name="page"/>
            <input type="submit" name="button" value="afficher"/>
            <input type="button" id="listeVilles"
                   onclick="window.location.href = 'CalculDistance';"
                   value="Aller au calcul de distance">
        </p>
    </form>

    <c:forEach items="${ villesPage }" var="ville">
        <p>
            <c:out value="Nom : ${ville.nom}"/>
            <c:out value="Code Commune : ${ville.codeCommune}"/>
            <c:out value="Code Postal : ${ville.codePostal}"/>
            <c:out value="Latitude : ${ville.latitude}"/>
            <c:out value="Longitude : ${ville.longitude}"/>
            <c:out value="Ligne : ${ville.ligne}"/>
            <input type="submit" id="modifier" onclick="window.location.href
            = 'ModifierVille?codeCommune=${ville.codeCommune}';"
                   value="Modifier la Ville">
        </p>
</c:forEach>
</body>
</html>
