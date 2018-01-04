package com.exo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exo.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
