package com.soaint.matricula.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( description = "Esta clase representa el request de un estudiante "
		+ "modificado" )
public class EstudianteModificarRequest {
	
	@ApiModelProperty( notes = "Es el nombre del estudiante", 
			example = "Pablo Ramirez")
	private String nombre;
	@ApiModelProperty( notes = "Es el email del estudiante", 
			example = "pramirez@gmail.com")
	private String email;

}
