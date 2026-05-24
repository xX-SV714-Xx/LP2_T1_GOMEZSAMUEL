package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_tecnico")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tecnico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tecnico")
	private Integer idTecnico;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "especialidad")
	private String especialidad;

	public String getEspecialidadDescripcion() {
		switch (especialidad) {
		case "S":
			return "Software";
		case "H":
			return "Hardware";
		case "R":
			return "Redes";
		case "B":
			return "Base de Datos";
		default:
			return "Sin Descripcion";
		}
	}
	@Override
	public String toString() {
		return nombre;
		
	}
}
