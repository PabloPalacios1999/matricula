package com.soaint.matricula.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soaint.matricula.dto.AcudienteResponse;
import com.soaint.matricula.entities.Acudiente;

@Component
public class AcudienteToConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public AcudienteResponse convertirEntidad(Acudiente acudiente) {

		return modelMapper.map(acudiente, AcudienteResponse.class);
	}

	public List<AcudienteResponse> convertirEntidad(List<Acudiente> 
	acudientes) {

		return acudientes.stream().map(acudiente -> 
		convertirEntidad(acudiente)).collect(Collectors.toList());
	}

}
