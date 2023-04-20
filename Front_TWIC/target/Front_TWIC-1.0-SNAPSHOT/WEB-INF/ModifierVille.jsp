<%--
  Created by IntelliJ IDEA.
  User: louis
  Date: 20/04/2023
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modification d'une ville</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/modifierVille.css">
</head>
<body>
<h1>Modifier une Ville</h1>
<form action="ModifierVille" method="post">
    <p>
        <label>Code Commune: </label>
        <c:out value="${codeCommune}"/>
        <input type="hidden" name="codeCommune" value="${codeCommune}"/>
    </p>
    <p>
        <label for="nomCommune">Nom Commune: </label>
        <input type="text" name="nomCommune" id="nomCommune"
               value="${nomCommune}"/>
        <label for="codePostal">Code Postal: </label>
        <input type="text" name="codePostal" id="codePostal"
               value="${codePostal}"/>
    </p>
    <p>
        <label for="latitude">Latitude</label>
        <input type="text" name="latitude" id="latitude" value="${latitude}"/>
        <label for="longitude">Longitude</label>
        <input type="text" name="longitude" id="longitude"
               value="${longitude}"/>
    </p>

    <p>
        <input type="submit" name="button" value="valider"/>
        <input type="button" id="listeVille" onclick="window.location.href =
        'ListeVilles';" value="Aller à la liste des villes">
    </p>
</form>
</body>
</html>
