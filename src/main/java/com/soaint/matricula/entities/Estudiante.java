package com.soaint.matricula.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_estudiante")
public class Estudiante {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private Long identificacion;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "correo_electronico", length = 50)
	private String email;
	@Column(name = "edad", length = 2)
	private Integer edad;
	@Column(name = "estado", length = 50)
	private String estado;
	
	@OneToMany( fetch = FetchType.EAGER, cascade = 
			CascadeType.ALL, mappedBy = "estudiante" )
	private List<Acudiente> acudientes;

}
