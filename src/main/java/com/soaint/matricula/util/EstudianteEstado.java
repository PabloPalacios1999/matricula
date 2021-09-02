package com.soaint.matricula.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstudianteEstado {
	
	ACTIVO("activo"),
	INACTIVO("inactivo");
	
	private String estado;

}
