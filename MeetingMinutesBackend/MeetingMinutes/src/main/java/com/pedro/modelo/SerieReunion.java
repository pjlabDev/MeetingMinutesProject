/**
 * 
 */
package com.pedro.modelo;

import java.util.Set;

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
 * The Class SerieReunion.
 *
 * @author Westermeyer
 */
@Entity
@Table(name = "SerieReunion")
public class SerieReunion {

	/** The Cod S reunion. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codsreunion")
	private int CodSReunion;
	
	/** The Equipo. */
	@Column(name = "equipo")
	@NotNull
	private String Equipo;
	
	/** The Nombre. */
	@Column(name = "nombre")
	@NotNull
	private String Nombre;
	
	/** The Cerrado. */
	@Column(name = "cerrado")
	@NotNull
	private int Cerrado;
	
	/** The usuarios. */
	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity=Usuarios.class)
	private Set<Usuarios> usuarios;
	
	
	/**
	 * Instantiates a new serie reunion.
	 *
	 * @param codSReunion the cod S reunion
	 * @param Equipo the equipo
	 * @param Nombre the nombre
	 */
	
	public SerieReunion(int codSReunion, String Equipo, String Nombre) {
		this.CodSReunion = codSReunion;
		this.Equipo = Equipo;
		this.Nombre = Nombre;
	}
	
	/**
	 * Instantiates a new serie reunion.
	 *
	 * @param Equipo the equipo
	 * @param Nombre the nombre
	 * @param usuarios the usuarios
	 */
	public SerieReunion(String Equipo, String Nombre, Set<Usuarios> usuarios) {
		this.Equipo = Equipo;
		this.Nombre = Nombre;
		this.usuarios = usuarios;
	}
	
	/**
	 * Instantiates a new serie reunion.
	 */
	public SerieReunion() {	}

	/**
	 * Gets the cod S reunion.
	 *
	 * @return the cod S reunion
	 */
	public int getCodSReunion() {
		return CodSReunion;
	}

	/**
	 * Sets the cod S reunion.
	 *
	 * @param codSReunion the new cod S reunion
	 */
	public void setCodSReunion(int codSReunion) {
		CodSReunion = codSReunion;
	}

	/**
	 * Gets the equipo.
	 *
	 * @return the equipo
	 */
	public String getEquipo() {
		return Equipo;
	}

	/**
	 * Sets the equipo.
	 *
	 * @param equipo the new equipo
	 */
	public void setEquipo(String equipo) {
		Equipo = equipo;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * Gets the cerrado.
	 *
	 * @return the cerrado
	 */
	public int getCerrado() {
		return Cerrado;
	}

	/**
	 * Sets the cerrado.
	 *
	 * @param cerrado the new cerrado
	 */
	public void setCerrado(int cerrado) {
		Cerrado = cerrado;
	}

	/**
	 * Gets the usuarios.
	 *
	 * @return the usuarios
	 */
	public Set<Usuarios> getUsuarios() {
		return usuarios;
	}

	/**
	 * Sets the usuarios.
	 *
	 * @param usuarios the new usuarios
	 */
	public void setUsuarios(Set<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

//	public List<Usuarios> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuarios> usuarios) {
//		this.usuarios = usuarios;
//	}
	
	
}
