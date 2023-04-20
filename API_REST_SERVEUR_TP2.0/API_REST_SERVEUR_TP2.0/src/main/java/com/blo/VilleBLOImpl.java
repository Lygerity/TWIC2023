package com.blo;

import com.dao.VilleDAO;
import com.dto.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleBLOImpl implements VilleBLO{

    @Autowired
    private VilleDAO villeDAO;

    @Override
    public Ville getInfoVille(String codeCommune) {
        Ville ville = villeDAO.chercherVille(codeCommune);
        if(ville != null){
            return ville;
        }else{
            return null;
        }

    }

    @Override
    public List<Ville> getAllVilles() {
        return villeDAO.getAllVilles();
    }

    @Override
    public void updateVille(String codePostal, Ville ville) {
        Ville oldVille = villeDAO.chercherVille(codePostal);
        if(oldVille != null){
            ville.setNom(ville.getNom());
            ville.setCodePostal(ville.getCodePostal());
            villeDAO.updateVille(ville);
        }
    }

    @Override
    public void createVille(Ville ville) {
        villeDAO.createVille(ville);
    }

    @Override
    public List<Ville> searchVille(String name) {
        return villeDAO.searchVille(name);
    }

    @Override
    public void deleteVille(String codePostal) {
        Ville ville = villeDAO.chercherVille(codePostal);
        if(ville != null){
            villeDAO.deleteVille(ville);
        }
    }
}
