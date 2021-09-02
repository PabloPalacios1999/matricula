package com.soaint.matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soaint.matricula.entities.Acudiente;

public interface AcudienteRepository extends JpaRepository<Acudiente, Long> {

}
