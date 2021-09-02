package com.soaint.matricula.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_acudiente")
public class Acudiente {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "parentesco", length = 50)
	private String parentesco;
	@Column(name = "telefono", length = 10)
	private Long telefono;
	
	@ManyToOne( cascade = CascadeType.ALL )
	private Estudiante estudiante;

}
