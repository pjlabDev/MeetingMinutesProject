/**
 * 
 */
package com.pedro.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Westermeyer
 *
 */
@Entity
@Table(name = "Reunion")
public class Reunion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codreunion")
	private int codReunion;
	
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@OneToOne
	private SerieReunion seriereunion;
	
	/**
	 * 
	 */
	
	public Reunion(int codReunion, Date fecha) {
		this.codReunion = codReunion;
		this.fecha = fecha;
	}
	
	public Reunion() {
		super();
	}

	public int getCodReunion() {
		return codReunion;
	}

	public void setCodReunion(int codReunion) {
		this.codReunion = codReunion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public SerieReunion getSeriereunion() {
		return seriereunion;
	}

	public void setSeriereunion(SerieReunion seriereunion) {
		this.seriereunion = seriereunion;
	}
	
}
