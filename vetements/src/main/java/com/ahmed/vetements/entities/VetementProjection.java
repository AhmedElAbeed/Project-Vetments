package com.ahmed.vetements.entities;

import org.springframework.data.rest.core.config.Projection;
@Projection(name = "nomVetement", types = { Vetement.class })
public interface VetementProjection {
public String getNomVetement();
}
