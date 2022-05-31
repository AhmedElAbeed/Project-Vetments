package com.ahmed.vetements.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.ahmed.vetements.entities.Categorie;
import com.ahmed.vetements.entities.Vetement;

public interface VetementService {

	Vetement saveVetement(Vetement p);
	Vetement updateVetement(Vetement p);
	void deleteVetement(Vetement p);
	void deleteVetementById(Long id);
	Vetement getVetement(Long id);
	List<Vetement> getAllVetements();
	List<Vetement> findByNomVetementContains(String nom);
	List<Vetement> findByNomPrix(String nom, Double prix);
	List<Vetement> findByCategorie (Categorie categorie);
	List<Vetement> findByCategorieIdCat(Long id);
	List<Vetement> findByOrderByNomVetementAsc();
	List<Vetement> trierVetementsNomsPrix();
	List<Vetement> findByNomVetement(String nom);
	
	List<Vetement> findByNomCategorieContains (String nom);
	Page<Vetement> getAllVetementsParPage(int page, int size);
	

}
