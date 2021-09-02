package com.soaint.matricula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.matricula.dto.EstudianteCrearRequest;
import com.soaint.matricula.dto.EstudianteModificarRequest;
import com.soaint.matricula.dto.EstudianteResponse;
import com.soaint.matricula.entities.Estudiante;
import com.soaint.matricula.service.EstudianteService;
import com.soaint.matricula.util.EstudianteToConverter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;


@RestController
@ApiModel( description = "Es el controlador del estudiante" )
public class EstudianteController {
	
	@ApiModelProperty( notes = "Es un servicio para el estudiante", 
			example = "Registrar Estudiante")
	@Autowired
	private EstudianteService estudianteService;
	
	@ApiModelProperty( notes = "Es un conversor para la clase estudiante", 
			example = "Registrar Estudiante")
	@Autowired
	private EstudianteToConverter estudianteToConverter;
	
	@ApiOperation( value = "Registrar estudiante", notes = "Registra un "
			+ "estudiante" )
	@PostMapping(value="estudiante")
	public ResponseEntity<EstudianteResponse> registrarEstudiante(@RequestBody 
			EstudianteCrearRequest payload) {
		
		Estudiante estudiante = new Estudiante();
		
		if(estudianteService.validarEdadEstudiante(payload.getEdad())) {
			estudiante = estudianteService.registrarEstudiante(payload);
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiante), HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiante), HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation( value = "Consultar estudiante por id", notes = "Consulta a "
			+ "un estudiante por su id " )
	@GetMapping( value="estudiante/{id}" )
	public ResponseEntity<EstudianteResponse> consultarEstudiantePorId
	(@PathVariable Long id){
		
		Estudiante estudiante =  estudianteService.consultarEstudiantePorId(id);
		
		if(estudiante.getId()!=null) {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiante), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiante), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation( value = "Consultar estudiantes", notes = "Consulta a los "
			+ "estudiantes registrados" )
	@GetMapping( value="estudiante" )
	public ResponseEntity<List<EstudianteResponse>> consultarEstudiantes(){
		List<Estudiante> estudiantes = estudianteService.consultarEstudiantes();
		
		if(estudiantes.isEmpty()) {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiantes), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiantes), HttpStatus.OK);
		}
	}
	
	@ApiOperation( value = "Eliminar estudiante", notes = "Elimina a "
			+ "un estudiante por su id " )
	@DeleteMapping( value="estudiante/{id}" )
	public ResponseEntity<Boolean> eliminarEstudiante( @PathVariable Long id ){
		
		if(estudianteService.eliminarEstudiante(id)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation( value = "Modificar estudiante", notes = "Modifica a "
			+ "un estudiante por su id " )
	@PutMapping( value="estudiante/{id}" )
	public ResponseEntity<Boolean> modificarEstudiante( @PathVariable Long id, 
			@RequestBody EstudianteModificarRequest payload){
		
		if(estudianteService.ModificarEstudiante(id, payload)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
			
	}
	
	@ApiOperation( value = "Consultar estudiante por edad y estado", 
			notes = "consulta a un estudiante por su edad y estado " )
	@GetMapping( value="estudiantePorEdadYEstado/{edad}/{estado}" )
	public ResponseEntity<List<EstudianteResponse>> 
	consultarEstudiantesPorEdadYEstado( @PathVariable Integer edad, 
			@PathVariable String estado){
		List<Estudiante> estudiantes = estudianteService.
				consultarEstudiantePorEdadYEstado(edad, estado);
		
		if(estudiantes.isEmpty()) {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiantes), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(estudianteToConverter.
					convertirEntidad(estudiantes), HttpStatus.OK);
		}
	}

}
