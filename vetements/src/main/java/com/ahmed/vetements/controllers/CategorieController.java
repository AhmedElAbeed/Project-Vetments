package com.ahmed.vetements.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmed.vetements.entities.Categorie;
import com.ahmed.vetements.entities.Vetement;
import com.ahmed.vetements.service.CategorieService;
import com.ahmed.vetements.service.VetementService;

@Controller
public class CategorieController {
	  @Autowired
	    CategorieService categorieService;
	  @Autowired
	    VetementService vetementService;
	 @RequestMapping("/CreateCategorie")
	  public String CreateCat(ModelMap modelMap)
	    {
	  
	    modelMap.addAttribute("categorie",new Categorie());
	    modelMap.addAttribute("mode","new");
	    
	    return "formCategorie";
	    }
	 
	 @RequestMapping("/saveCategorie")
	    public String saveCategorie(Categorie categorie)
	    {
	    categorieService.saveCategorie(categorie);
		return "redirect:/ListeCategories";
	    }
	 
	 @RequestMapping("/ListeCategories")
	    public String listeCategories(ModelMap modelMap,
	    		@RequestParam (name="page",defaultValue = "0") int page,
	    		@RequestParam (name="size", defaultValue = "4") int size)
	    {
	    	Page<Categorie> cats = categorieService.getAllCategoriesParPage(page, size);
	    	modelMap.addAttribute("categories", cats);
	    	modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
	    	modelMap.addAttribute("currentPage", page);
	    	return "listecategorie";
	    }
	 
	 
	 @RequestMapping("/supprimerCategorie")
	    public String supprimerSkin(@RequestParam("id") Long id,
	    		ModelMap modelMap,
	    		@RequestParam (name="page",defaultValue = "0") int page,
	    		@RequestParam (name="size", defaultValue = "4") int size)
	    {
	        categorieService.deleteCategorieById(id);
	        Page<Categorie> cats = categorieService.getAllCategoriesParPage(page,size);
	        		modelMap.addAttribute("categories", cats);
	        		modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
	        		modelMap.addAttribute("currentPage", page);
	        		modelMap.addAttribute("size", size);
	        		return "listecategorie";
	    }
	 @RequestMapping("/modifierCategorie")
	    public String editerCategorie(@RequestParam("id") Long id,ModelMap modelMap)
	    {
	    Categorie c= categorieService.getCategorie(id);
	    modelMap.addAttribute("categorie", c);
	    modelMap.addAttribute("mode", "edit");
	    return "formCategorie";
	    }
	 
	 
	 @RequestMapping("/updateCategorie")
	    public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,ModelMap modelMap) throws ParseException
	    {

	        categorieService.updateCategorie(categorie);
	        List<Categorie> cats = categorieService.getAllCategories();
	        modelMap.addAttribute("categories", cats);
	        return "listecategorie";
	    }
	 @RequestMapping("/chercherCategorie")
	
	    public String chercherCategorie(@RequestParam("nomCat") String nom,
	    		ModelMap modelMap)
	    
	    
	    {      
		 
		 		System.out.println(nom);
	    	  List <Vetement> vets = vetementService.findByNomCategorieContains(nom);
	    	  System.out.println(vets);
	    	  modelMap.addAttribute("categories",vets);
	    	  
	    	  return "chercherCat";
	    } 


}
