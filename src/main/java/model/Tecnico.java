package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_tecnico")
@Getter
@Setter
public class Tecnico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_tecnico")
 private Integer idTecnico;
	
	@Column(name= "nombre")
 private String nombre;
	
	@Column(name= "especialidad")
	 private String especialidad;
 
}
