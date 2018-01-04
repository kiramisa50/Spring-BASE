package com.exo.metier;

import org.springframework.data.domain.Page;

import com.exo.entities.Compte;
import com.exo.entities.Operation;

public interface IbanqueMetier {
	public Compte consulterCompte(String codeCpte);
	public void verser(String codeCpte,double montant);
	public void retirer(String codeCpte, double montant);
	public void virement(String codeCpte1,String codeCpte2, double monatant);
	public Page<Operation> listOperation(String codeCpte, int page, int size);
	
}
