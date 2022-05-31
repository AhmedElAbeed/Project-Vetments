package com.ahmed.vetements.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ahmed.vetements.entities.Categorie;
import com.ahmed.vetements.entities.Vetement;

public interface CategorieService {
	List<Categorie> getAllCategories();

	Categorie saveCategorie(Categorie categorie);
	Page<Categorie> getAllCategoriesParPage(int page, int size);
	void deleteCategorieById(Long id);
	Categorie getCategorie(Long id);
	
	Categorie updateCategorie(Categorie c);

	List<Categorie> findAll();

	List<Vetement> findByNomCategorieContains(String nom);
}
