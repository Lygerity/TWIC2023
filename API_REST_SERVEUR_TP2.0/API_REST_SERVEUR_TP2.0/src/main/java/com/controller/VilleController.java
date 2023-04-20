package com.controller;


import com.blo.VilleBLO;
import com.dto.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VilleController {
    @Autowired
    VilleBLO villeBLOService;

    @GetMapping(value="/ville")
    public Ville get(@RequestParam(required = false, value="code_commune") String param){
        return villeBLOService.getInfoVille(param);
    }

    @GetMapping(value="/villes")
    public List<Ville> get(){
        return villeBLOService.getAllVilles();
    }

    @PutMapping(value="/ville/{codePostal}")
    public void put(@PathVariable String codePostal, @RequestBody Ville ville){
        villeBLOService.updateVille(codePostal, ville);
    }

    @PostMapping(value="/searchVille")
    public List<Ville> search(@RequestBody String name){
        return villeBLOService.searchVille(name);
    }

    @PostMapping(value="/ville")
    public void post(@RequestBody Ville ville){
        villeBLOService.createVille(ville);
    }

    @DeleteMapping(value="/ville/{codePostal}")
    public void delete(@PathVariable String codePostal){
        villeBLOService.deleteVille(codePostal);
    }
}
