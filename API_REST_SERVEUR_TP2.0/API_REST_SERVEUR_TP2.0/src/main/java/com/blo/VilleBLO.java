package com.blo;

import com.dto.Ville;

import java.util.List;

public interface VilleBLO {
    public Ville getInfoVille(String codeCommune);
    public List<Ville> getAllVilles() ;
    public void updateVille(String codePostal, Ville ville);
    public void createVille(Ville ville);
    public List<Ville> searchVille(String name);
    public void deleteVille(String codePostal);
}
