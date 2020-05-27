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
 * @author Westermeyer
 *
 */
@Entity
@Table(name = "Actas")
public class Actas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codacta")
	private int CodActa;
	
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@OneToOne
	private Reunion reunion;
	
	@ManyToMany(targetEntity=Usuarios.class, cascade = {CascadeType.ALL})
	private Set<Usuarios> usuarios;
	
	/**
	 * 
	 */
	
	public Actas(Date fecha, Reunion reunion, Set<Usuarios> usuarios) {
		this.fecha = fecha;
		this.reunion = reunion;
		this.usuarios = usuarios;
	}
	
	public Actas() {
		super();
	}

	public int getCodActa() {
		return CodActa;
	}

	public void setCodActa(int codActa) {
		CodActa = codActa;
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	public Set<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
