package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_orden_soporte")
@Getter
@Setter
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class OrdenSoporte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "nro_orden")
 private Integer nroOrden;
	
	@Column(name= "fecha_registro")
 private LocalDate fechaRegistro;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id_tecnico")
	 private Tecnico tecnico;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id_cliente")
	 private Cliente cliente;
	
	@Column(name = "monto")
	private Double monto;
	
	@Column(name = "detalle_incidencia")
	private String detalleIncidencia;
	
	
}
