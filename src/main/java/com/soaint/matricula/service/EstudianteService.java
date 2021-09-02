package com.soaint.matricula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.matricula.dto.EstudianteCrearRequest;
import com.soaint.matricula.dto.EstudianteModificarRequest;
import com.soaint.matricula.entities.Acudiente;
import com.soaint.matricula.entities.Estudiante;
import com.soaint.matricula.repository.EstudianteRepository;
import com.soaint.matricula.util.EstudianteEstado;

@Service
public class EstudianteService {
	
	private final int EDAD_VALIDA_REGISTRO_ESTUDIANTE = 18;
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	public List<Estudiante> consultarEstudiantePorEdadYEstado( Integer edad, String estado ) {
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		estudiantes = estudianteRepository.findByEdadAndEstado(edad, estado);
		return estudiantes;
	}
	
	public Boolean ModificarEstudiante( Long id, EstudianteModificarRequest 
			payload ) {
		Estudiante estudiante = new Estudiante();
		
		if(estudianteRepository.existsById(id)) {
			
			estudiante = estudianteRepository.findById(id).get();
			estudiante.setNombre(payload.getNombre());
			estudiante.setEmail(payload.getEmail());
			estudianteRepository.save(estudiante);
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean eliminarEstudiante( Long id ) {
		Estudiante estudiante = new Estudiante();
		
		if(estudianteRepository.existsById(id)) {
			
			estudiante = estudianteRepository.findById(id).get();
			estudiante.setEstado(EstudianteEstado.INACTIVO.getEstado());
			estudianteRepository.save(estudiante);
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean validarEdadEstudiante( Integer edad ) {
		
		boolean bandera = false;
		
		if(edad<EDAD_VALIDA_REGISTRO_ESTUDIANTE) {
			bandera = true;
		}
		
		return bandera;
		
	}
	
	public Estudiante consultarEstudiantePorId( Long id ) {
		Estudiante estudiante = new Estudiante();
		
		if(estudianteRepository.existsById(id)) {
			estudiante = estudianteRepository.findById(id).get();
		}
		return estudiante;
	}
	
	public List<Estudiante> consultarEstudiantes(){
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		return estudiantes;
	}
	
	public Estudiante registrarEstudiante( EstudianteCrearRequest payload ) {
		
		Estudiante estudiante = new Estudiante();
		
		estudiante.setNombre(payload.getNombre());
		estudiante.setIdentificacion(payload.getIdentificacion());
		estudiante.setEmail(payload.getEmail());
		estudiante.setEdad(payload.getEdad());
		estudiante.setEstado(EstudianteEstado.ACTIVO.getEstado());
		List<Acudiente> acudientes = payload.getAcudientes().stream().
				map(acudiente -> Acudiente.builder().
						nombre(acudiente.getNombre()).
						parentesco(acudiente.getParentesco()).
						telefono(acudiente.getTelefono()).
						estudiante(estudiante).build()).
				collect(Collectors.toList());
		estudiante.setAcudientes(acudientes);
		
		estudianteRepository.save(estudiante);
		
		return estudiante;	
		
	}

}
