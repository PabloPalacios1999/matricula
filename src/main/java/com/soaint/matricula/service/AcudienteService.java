package com.soaint.matricula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.matricula.entities.Acudiente;
import com.soaint.matricula.repository.AcudienteRepository;

@Service
public class AcudienteService {
	
	@Autowired
	private AcudienteRepository acudienteRepository;
	
	public Acudiente consultarAcudientePorId( Long id ) {
		Acudiente acudiente = new Acudiente();
		
		if(acudienteRepository.existsById(id)) {
			acudiente = acudienteRepository.findById(id).get();
		}
		return acudiente;
	}
	
	public List<Acudiente> consultarAcudientes(){
		List<Acudiente> acudientes = acudienteRepository.findAll();
		return acudientes;
	}

}
