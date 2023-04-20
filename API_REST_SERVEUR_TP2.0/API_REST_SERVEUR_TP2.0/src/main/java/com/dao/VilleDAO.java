package com.dao;

import com.dto.Ville;

import java.util.List;

public interface VilleDAO {
    public List<Ville> getAllVilles() ;
    public Ville chercherVille(String codeCommune);
    public List<Ville> searchVille(String name);
    public void updateVille(Ville ville);
    public void createVille(Ville ville);
    public void deleteVille(Ville ville);

}
