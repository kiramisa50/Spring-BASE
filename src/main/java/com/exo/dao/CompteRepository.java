package com.exo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exo.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
