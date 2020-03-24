/**
 * 
 */
package com.pedro.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Westermeyer
 *
 */
@Entity
@Table(name = "SerieReunion")
public class SerieReunion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codsreunion")
	private int CodSReunion;
	
	@Column(name = "equipo")
	@NotNull
	private String Equipo;
	
	@Column(name = "nombre")
	@NotNull
	private String Nombre;
	
	@Column(name = "cerrado")
	@NotNull
	private int Cerrado;
	
	@ManyToMany(cascade = CascadeType.DETACH, mappedBy = "serieReuniones")
	private List<Usuarios> usuarios;
	
	/**
	 * 
	 */
	
	public SerieReunion(String Equipo, String Nombre) {
		this.Equipo = Equipo;
		this.Nombre = Nombre;
	}
	
	public SerieReunion() {
		
		this.usuarios = new ArrayList<Usuarios>();
		
	}

	public int getCodSReunion() {
		return CodSReunion;
	}

	public void setCodSReunion(int codSReunion) {
		CodSReunion = codSReunion;
	}

	public String getEquipo() {
		return Equipo;
	}

	public void setEquipo(String equipo) {
		Equipo = equipo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
	
}
