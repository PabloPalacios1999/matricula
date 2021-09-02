package com.soaint.matricula.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( description = "Esta clase representa el response de un estudiante" )
public class EstudianteResponse {
	@ApiModelProperty( notes = "Es el id del estudiante", example = "1")
	private Long id;
	@ApiModelProperty( notes = "Es la identificacion del estudiante", 
			example = "1111740615")
	private Long identificacion;
	@ApiModelProperty( notes = "Es el nombre del estudiante", 
			example = "Pablo Ramirez")
	private String nombre;
	@ApiModelProperty( notes = "Es el email del estudiante", 
			example = "pramirez@gmail.com")
	private String email;
	@ApiModelProperty( notes = "Es la edad del estudiante", 
			example = "15")
	private Integer edad;
	@ApiModelProperty( notes = "Es el estado del estudiante", 
			example = "activo")
	private String estado;

}
