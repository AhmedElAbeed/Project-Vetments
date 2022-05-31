package com.ahmed.vetements.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmed.vetements.entities.Categorie;
import com.ahmed.vetements.entities.Vetement;
@RepositoryRestResource(path = "rest")
public interface VetementRepository extends JpaRepository<Vetement, Long> {
	
	
	 List<Vetement> findByNomVetementContains(String nom);
	 @Query("select p from Vetement p where p.nomVetement like %:nom and p.prixVetement > :prix")
	 List<Vetement> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);
	 @Query("select p from Vetement p where p.categorie = ?1")
	 List<Vetement> findByCategorieContains (Categorie categorie);
	 List<Vetement> findByCategorieIdCat(Long id);
	 List<Vetement> findByOrderByNomVetementAsc();
	 @Query("select p from Vetement p order by p.nomVetement ASC, p.prixVetement DESC")
	 List<Vetement> trierVetementsNomsPrix ();
	 @Query("select p from Vetement p  where p.nomVetement like %:nomVet")
	 List<Vetement> findByNomVetement(@Param("nomVet") String nom);	
	 @Query("select p from Vetement p where p.categorie.nomCat like ?1")
	 List<Vetement> findByNomCategorieContains(String nom);
}