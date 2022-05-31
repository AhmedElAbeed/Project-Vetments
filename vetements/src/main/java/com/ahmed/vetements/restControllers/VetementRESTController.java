package com.ahmed.vetements.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.vetements.entities.Vetement;
import com.ahmed.vetements.service.VetementService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VetementRESTController {
@Autowired
VetementService VetementService;
@RequestMapping(method = RequestMethod.GET)
public List<Vetement> getAllVetements() {
return VetementService.getAllVetements();
}
@RequestMapping(value="/{id}",method = RequestMethod.GET)
public Vetement getVetementById(@PathVariable("id") Long id) {
return VetementService.getVetement(id);
 }
@RequestMapping(method = RequestMethod.POST)
public Vetement createVetement(@RequestBody Vetement vetement) {
return VetementService.saveVetement(vetement);
}
@RequestMapping(method = RequestMethod.PUT)
public Vetement updateVetement(@RequestBody Vetement vetement) {
return VetementService.updateVetement(vetement);
}
@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
public void deleteVetement(@PathVariable("id") Long id)
{
VetementService.deleteVetementById(id);
}
@RequestMapping(value="/vetscat/{idCat}",method = RequestMethod.GET)
public List<Vetement> getVetementsByCatId(@PathVariable("idCat") Long idCat) {
return VetementService.findByCategorieIdCat(idCat);
}
}

