/**
 * 
 */
package com.pedro.modelo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Reunion.
 *
 * @author Westermeyer
 */
@Entity
@Table(name = "Reunion")
public class Reunion {

	/** The cod reunion. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codreunion")
	private int codReunion;
	
	/** The fecha. */
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	/** The seriereunion. */
	@OneToOne
	private SerieReunion seriereunion;
	
	/** The usuarios. */
	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity=Usuarios.class)
	private Set<Usuarios> usuarios;
	
	/**
	 * Instantiates a new reunion.
	 *
	 * @param codReunion the cod reunion
	 * @param fecha the fecha
	 */
	
	public Reunion(int codReunion, Date fecha) {
		this.codReunion = codReunion;
		this.fecha = fecha;
	}
	
	/**
	 * Instantiates a new reunion.
	 *
	 * @param fecha the fecha
	 * @param seriereunion the seriereunion
	 * @param usuarios the usuarios
	 */
	public Reunion(Date fecha, SerieReunion seriereunion, Set<Usuarios> usuarios) {
		this.fecha = fecha;
		this.seriereunion = seriereunion;
		this.usuarios = usuarios;
	}
	
	/**
	 * Instantiates a new reunion.
	 */
	public Reunion() {
		super();
	}

	/**
	 * Gets the cod reunion.
	 *
	 * @return the cod reunion
	 */
	public int getCodReunion() {
		return codReunion;
	}

	/**
	 * Sets the cod reunion.
	 *
	 * @param codReunion the new cod reunion
	 */
	public void setCodReunion(int codReunion) {
		this.codReunion = codReunion;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the seriereunion.
	 *
	 * @return the seriereunion
	 */
	public SerieReunion getSeriereunion() {
		return seriereunion;
	}

	/**
	 * Sets the seriereunion.
	 *
	 * @param seriereunion the new seriereunion
	 */
	public void setSeriereunion(SerieReunion seriereunion) {
		this.seriereunion = seriereunion;
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
	
}
