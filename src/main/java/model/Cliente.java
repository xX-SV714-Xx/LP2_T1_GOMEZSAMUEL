package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_cliente")
@Getter
@Setter
@DynamicInsert
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_cliente")
 private Integer idCliente;
	
	@Column(name= "razon_social")
 private String razonSocial;
	
	@Column(name= "ruc")
	 private String ruc;
 
}
