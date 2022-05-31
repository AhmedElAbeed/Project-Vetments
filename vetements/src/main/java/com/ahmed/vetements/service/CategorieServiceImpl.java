package com.ahmed.vetements.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ahmed.vetements.entities.Categorie;
import com.ahmed.vetements.entities.Vetement;
import com.ahmed.vetements.repos.CategorieRepository;

@Service
public  class CategorieServiceImpl implements CategorieService {
	@Autowired
	CategorieRepository categorieRepository;
	
	@Override
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}
	 @Override
	    public Categorie saveCategorie(Categorie categorie) {
	        return categorieRepository.save(categorie);
	    }
	 @Override
	    public Page<Categorie> getAllCategoriesParPage(int page, int size) {
	    return categorieRepository.findAll(PageRequest.of(page, size));
	    }
	 
	 @Override
	    public void deleteCategorieById(Long id) {
	        categorieRepository.deleteById(id);
	    }
	 
	 @Override
	    public Categorie getCategorie(Long id) {
	        return categorieRepository.findById(id).get();
	    }
	 @Override
	    public Categorie updateCategorie(Categorie c) {
	        return categorieRepository.save(c);
	    }
		@Override
	    public List <Categorie> findAll() {
	        return categorieRepository.findAll();
	    }
		@Override
		public List<Vetement> findByNomCategorieContains(String nom) {
			// TODO Auto-generated method stub
			return null;
		}

}
