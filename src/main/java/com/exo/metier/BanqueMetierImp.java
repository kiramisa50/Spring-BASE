package com.exo.metier;

import java.util.Date;

import javax.management.RuntimeErrorException;

import net.minidev.json.writer.CompessorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exo.dao.CompteRepository;
import com.exo.dao.OperationRepository;
import com.exo.entities.Compte;
import com.exo.entities.CompteCourant;
import com.exo.entities.Operation;
import com.exo.entities.Retrait;
import com.exo.entities.Versement;

@Service
@Transactional
public class BanqueMetierImp implements IbanqueMetier {
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	OperationRepository operationRepository;
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findOne(codeCpte);
		if(cp == null) throw new RuntimeException("compte introuvable");
		return cp;
	}

	public void verser(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde() + montant);
		compteRepository.save(cp);
		
	}

	public void retirer(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		double facilitesCaisse =0;
		if(cp instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		if(cp.getSolde() + facilitesCaisse <montant)
			throw new RuntimeException("solde insuffisant");
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde() - montant);
		compteRepository.save(cp);
		
	}

	public void virement(String codeCpte1, String codeCpte2, double montant) {
		// TODO Auto-generated method stub
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);		
	}

	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.listOperation(codeCpte, new PageRequest(page, size));
	}

	

	
}
