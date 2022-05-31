package com.ahmed.vetements.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class VetementsController {

	@Autowired
	VetementService vetementService;
	
	@Autowired
	CategorieService categorieService;
	
	
	
	@RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap)
    {
        List<Categorie> categories = categorieService.getAllCategories();
        Vetement vetement = new Vetement();
        Categorie categorie = new Categorie();
        categorie = categories.get(0); // prendre la première catégorie de la liste
        vetement.setCategorie(categorie); //affedter une catégorie par défaut au produit pour éviter le pb avec une catégorie null
        modelMap.addAttribute("vetement",vetement);
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("categories", categories);
        return "formVetement";
    }
	

	
	
	@RequestMapping("/saveVetement")
	public String saveVetement(@Valid Vetement vetement,BindingResult bindingResult)
	{
	if (bindingResult.hasErrors()) return "formVetement";
	vetementService.saveVetement(vetement);
	return "redirect:/ListeVetements";
	}
	
	@RequestMapping("/ListeVetements")
	public String listeVetementss(ModelMap modelMap,

	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "4") int size)

	{
		List<Categorie> categories = categorieService.findAll();
		modelMap.addAttribute("categories", categories);
	Page<Vetement> vetements = vetementService.getAllVetementsParPage(page, size);
	modelMap.addAttribute("vetements", vetements);

	modelMap.addAttribute("pages", new int[vetements.getTotalPages()]);

	modelMap.addAttribute("currentPage", page);
	return "listeVetements";
	}
	
	@RequestMapping("/supprimerVetement")
	public String supprimerVetement(@RequestParam("id") Long id,

	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "4") int size)

	{
	vetementService.deleteVetementById(id);
	Page<Vetement> vetements = vetementService.getAllVetementsParPage(page,size);

	modelMap.addAttribute("vetements", vetements);
	modelMap.addAttribute("pages", new int[vetements.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeVetements";
	}
	
	@RequestMapping("/modifierVetement")
	public String editerVetement(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Vetement p= vetementService.getVetement(id);
	List<Categorie> cats = categorieService.findAll();
	cats.remove(p.getCategorie());
	modelMap.addAttribute("categories", cats);
	modelMap.addAttribute("vetement", p);
	modelMap.addAttribute("mode", "edit");
	return "formVetement";
	}
	
	@RequestMapping("/updateVetement")
	public String updateVetement(@ModelAttribute("vetement") Vetement vetement,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException

{
	
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	Date dateCreation = dateformat.parse(String.valueOf(date));
	vetement.setDateCreation(dateCreation);
	vetementService.updateVetement(vetement);
	List<Vetement> vetements = vetementService.getAllVetements();
	modelMap.addAttribute("vetements", vetements);
	return "listeVetements";
	
	}
	
	 @RequestMapping("/search")
		public String findByNomVetement(@RequestParam("nom") String nom,
		ModelMap modelMap)
		{
			List<Categorie> categories = categorieService.findAll();
			modelMap.addAttribute("categories", categories);
		List<Vetement> vetements = vetementService.findByNomVetement(nom);
		modelMap.addAttribute("vetements",vetements);
		modelMap.addAttribute("mode", "SearchNom");
		return "searchVetements";
		}
	
	 @RequestMapping("/chercherCat")
	  public String chercherCat(@RequestParam("idCat") int idCat,
	    		ModelMap modelMap,
	    		@RequestParam (name="page",defaultValue = "0") int page,
	    		@RequestParam (name="size", defaultValue = "4") int size)
	    {     
		 	
	    	  List <Vetement> vetements = vetementService.getAllVetements();
	    	  vetements = vetements.stream()
	                  .filter(x -> x.getCategorie().getIdCat() == idCat)
	                  .collect(Collectors.toList());
	    	  modelMap.addAttribute("vetements",vetements);
	    	  Page<Categorie> cats = categorieService.getAllCategoriesParPage(page, size);
	    		modelMap.addAttribute("categories", cats);
	    		modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
	    		modelMap.addAttribute("currentPage", page);
	    		modelMap.addAttribute("mode", "rech");
	    	  
	    	  return "rechVetements";
	    }  
		
		@RequestMapping("/ListeVetements2")
		public String listevetements2(ModelMap modelMap,

		@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "4") int size)

		{
		Page<Vetement> vetements = vetementService.getAllVetementsParPage(page, size);
		modelMap.addAttribute("vetements", vetements);
		modelMap.addAttribute("pages", new int[vetements.getTotalPages()]);
		Page<Categorie> cats = categorieService.getAllCategoriesParPage(page, size);
		modelMap.addAttribute("categories", cats);
		modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("currentPage", page);
		
		return "listeVetements2";
		
		}
}