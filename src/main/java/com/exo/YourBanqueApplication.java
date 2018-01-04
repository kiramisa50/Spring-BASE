package com.exo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exo.dao.ClientRepository;
import com.exo.dao.CompteRepository;
import com.exo.dao.OperationRepository;
import com.exo.entities.Client;
import com.exo.entities.Compte;
import com.exo.entities.CompteCourant;
import com.exo.entities.CompteEpargne;
import com.exo.entities.Retrait;
import com.exo.entities.Versement;
import com.exo.metier.IbanqueMetier;

@SpringBootApplication
public class YourBanqueApplication implements CommandLineRunner{
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	IbanqueMetier banqueMetier;
	public static void main(String[] args) {
		SpringApplication.run(YourBanqueApplication.class, args);
	}

	public void run(String... arg0) throws Exception {
		// Client 
		Client c1 = clientRepository.save(new Client("AITELABBAS","saad.aitelabbas@iga-marrakech.ma"));
		
		Client c2 = clientRepository.save(new Client("MOUMMID","hamza.moummid@fst-marrakech.ma"));
		
		Client c3 = clientRepository.save(new Client("Saloumi","saloumi.achraf@fst-marrakech.ma"));
		
		// Compte
		
		Compte cp1 = compteRepository.save(new CompteCourant("cp1", new Date(), 90000, c1, 6000));  
		
		Compte cp2 = compteRepository.save(new CompteEpargne("cp2", new Date(), 6000, c2, 5.5));
		
		operationRepository.save(new Versement(new Date(), 9000, cp1));
		operationRepository.save(new Versement(new Date(), 6000, cp1));
		operationRepository.save(new Versement(new Date(), 2300, cp1));
		operationRepository.save(new Retrait(new Date(), 9000, cp1));
		
		operationRepository.save(new Versement(new Date(), 2300, cp2));
		operationRepository.save(new Versement(new Date(), 6000, cp2));
		operationRepository.save(new Versement(new Date(), 2300, cp2));
		operationRepository.save(new Retrait(new Date(), 9000, cp2));
		
		banqueMetier.verser("cp1", 3000);

		
	}
}
