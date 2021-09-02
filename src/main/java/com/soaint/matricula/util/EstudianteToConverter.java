package com.soaint.matricula.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soaint.matricula.dto.EstudianteResponse;
import com.soaint.matricula.entities.Estudiante;


@Component
public class EstudianteToConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public EstudianteResponse convertirEntidad(Estudiante estudiante) {

		return modelMapper.map(estudiante, EstudianteResponse.class);
	}

	public List<EstudianteResponse> convertirEntidad(List<Estudiante> 
	estudiantes) {

		return estudiantes.stream().map(estudiante -> 
		convertirEntidad(estudiante)).collect(Collectors.toList());
	}

}
