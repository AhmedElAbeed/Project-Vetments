package com.ahmed.vetements.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Vetement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVetement;
	
	private String nomVetement;
	@Min(value = 10)
	 @Max(value = 10000)
	private Double prixVetement;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent

	private Date dateCreation;
	@ManyToOne
	private Categorie categorie;
	public Vetement() {
		super();
	}

	public Vetement(String nomVetement, Double prixVetement, Date dateCreation,Categorie cat) {
		super();
		this.nomVetement = nomVetement;
		this.prixVetement = prixVetement;
		this.dateCreation = dateCreation;
		this.setCategorie(cat);
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Long getIdVetement() {
		return idVetement;
	}

	public void setIdVetement(Long idVetement) {
		this.idVetement = idVetement;
	}

	public String getNomVetement() {
		return nomVetement;
	}

	public void setNomVetement(String nomVetement) {
		this.nomVetement = nomVetement;
	}

	public Double getPrixVetement() {
		return prixVetement;
	}

	public void setPrixVetement(Double prixVetement) {
		this.prixVetement = prixVetement;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}



}