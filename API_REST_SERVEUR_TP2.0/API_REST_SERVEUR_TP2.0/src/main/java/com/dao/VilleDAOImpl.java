package com.dao;

import com.dto.Ville;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VilleDAOImpl implements VilleDAO{
    private Connection connexion;

    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root",
                    "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ville> getAllVilles() {
        ArrayList<Ville> villes = new ArrayList<>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery(
                    "SELECT Nom_commune, Code_postal, Code_commune_INSEE, Latitude, Longitude from ville_france;");
            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("Nom_commune");
                String cp = resultat.getString("Code_postal");
                String codeCommune = resultat.getString("Code_commune_INSEE");
                String latitude = resultat.getString("Latitude");
                String longitude = resultat.getString("Longitude");
                Ville ville = new Ville();
                ville.setNom(nom);
                ville.setCodePostal(cp);
                ville.setCodeCommune(codeCommune);
                ville.setLatitude(latitude);
                ville.setLongitude(longitude);
                villes.add(ville);
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }

        return villes;
    }

    public Ville chercherVille(String codeCommune){
        Statement statement = null;
        ResultSet resultat = null;
        Ville ville = null;
        loadDatabase();

        try {
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
                    "SELECT Nom_commune, Code_postal, Code_commune_INSEE, Longitude, Latitude from ville_france" +
                            " where Code_commune_INSEE = " + codeCommune + ";");
            while (resultat.next()) {
                String nom = resultat.getString("Nom_commune");
                String cp = resultat.getString("Code_postal");
                String code = resultat.getString("Code_commune_INSEE");
                String latitude = resultat.getString("Latitude");
                String longitude = resultat.getString("Longitude");
                ville = new Ville();
                ville.setNom(nom);
                ville.setCodePostal(cp);
                ville.setCodeCommune(code);
                ville.setLatitude(latitude);
                ville.setLongitude(longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        return ville;
    }

    @Override
    public ArrayList<Ville> searchVille(String name) {
        ArrayList<Ville> villes = new ArrayList<>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery(
                    "SELECT Nom_commune, Code_postal, Code_commune_INSEE, Latitude, Longitude from ville_france" +
                            " where Nom_commune like '%" + name + "%';");
            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("Nom_commune");
                String cp = resultat.getString("Code_postal");
                String codeCommune = resultat.getString("Code_commune_INSEE");
                String latitude = resultat.getString("Latitude");
                String longitude = resultat.getString("Longitude");
                Ville ville = new Ville();
                ville.setNom(nom);
                ville.setCodePostal(cp);
                ville.setCodeCommune(codeCommune);
                ville.setLatitude(latitude);
                ville.setLongitude(longitude);
                villes.add(ville);
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }

        return villes;
    }

    @Override
    public void updateVille(Ville ville) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE ville_france SET" +
                    " Nom_commune = ?,Code_postal=?, Longitude=?, Latitude=? WHERE Code_commune_INSEE = ?");
            preparedStatement.setString(1, ville.getNom());
            preparedStatement.setString(2, ville.getCodePostal());
            preparedStatement.setString(3, ville.getLongitude());
            preparedStatement.setString(4, ville.getLatitude());
            preparedStatement.setString(5, ville.getCodeCommune());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // Fermeture de la connexion
            try {
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void createVille(Ville ville) {
        Statement statement = null;
        loadDatabase();

        try {
            statement = connexion.createStatement();
            statement.executeUpdate(
                    "INSERT INTO ville_france (Code_commune_INSEE,Nom_commune, Code_postal, Latitude, Longitude)" +
                            " VALUES ('" + ville.getCodeCommune() +"','" + ville.getNom() + "'," +
                            " '" + ville.getCodePostal() + "', '" + ville.getLatitude() + "'," +
                            " '" + ville.getLongitude() +"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // Fermeture de la connexion
            try {
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void deleteVille(Ville ville) {
        Statement statement = null;
        loadDatabase();

        try {
            statement = connexion.createStatement();
            statement.executeUpdate(
                    "DELETE FROM ville_france WHERE Code_commune_INSEE = " + ville.getCodeCommune() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // Fermeture de la connexion
            try {
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
    }
}
