<%--
  Created by IntelliJ IDEA.
  User: louis
  Date: 20/04/2023
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Calcul de la distance entre les villes</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/calculDistance.css">
</head>
<body>
<h1>Entrer les villes dont vous voulez calculer la distance</h1>

<form method="post" action="CalculDistance">
    <p>
        <label for="villeDepart">Ville de Départ</label>
        <select name="villeDepart" id="villeDepart">
            <option value="">Sélectionner une ville de départ</option>
            <c:forEach items="${villes}" var="ville">
                <option value="${ville.codeCommune}">${ville.nom}</option>
            </c:forEach>
        </select>
    </p>
    <p>
        <label for="villeArrivee">Ville d'Arrivée</label>
        <select name="villeArrivee" id="villeArrivee">
            <option value="">Sélectionner une ville d'arrivée</option>
            <c:forEach items="${villes}" var="ville">
                <option value="${ville.codeCommune}">${ville.nom}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <input type="submit" value="Calculer la distance"/>
        <input type="button" id="listeVilles"
               onclick="window.location.href = 'ListeVilles';"
               value="Liste des Villes"/>
    </p>
    <p>
        <c:if test="${distance != null}">
    <p>
        <c:out value="La distance entre ${villeDepart.nom} et
                 ${villeArrivee.nom}
                est de
                ${distance} km"/>
    </p>
    </c:if>
    </p>
</form>
</body>
</html>
