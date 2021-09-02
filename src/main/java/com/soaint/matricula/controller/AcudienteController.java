package com.soaint.matricula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.matricula.dto.AcudienteResponse;
import com.soaint.matricula.entities.Acudiente;
import com.soaint.matricula.service.AcudienteService;
import com.soaint.matricula.util.AcudienteToConverter;

@RestController
public class AcudienteController {
	
	@Autowired
	private AcudienteService acudienteService;
	
	@Autowired
	private AcudienteToConverter acudienteToConverter;
	
	@GetMapping( value="acudiente/{id}" )
	public ResponseEntity<AcudienteResponse> consultarAcudientePorId
	(@PathVariable Long id){
		
		Acudiente acudiente =  acudienteService.consultarAcudientePorId(id);
		
		if(acudiente.getId()!=null) {
			return new ResponseEntity<>(acudienteToConverter.
					convertirEntidad(acudiente), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(acudienteToConverter.
					convertirEntidad(acudiente), HttpStatus.NOT_FOUND);
		}
		
	}
	

	@GetMapping( value="acudiente" )
	public ResponseEntity<List<AcudienteResponse>> consultarAcudientes(){
		List<Acudiente> acudientes = acudienteService.consultarAcudientes();
		
		if(acudientes.isEmpty()) {
			return new ResponseEntity<>(acudienteToConverter.
					convertirEntidad(acudientes), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(acudienteToConverter.
					convertirEntidad(acudientes), HttpStatus.OK);
		}
	}

}
