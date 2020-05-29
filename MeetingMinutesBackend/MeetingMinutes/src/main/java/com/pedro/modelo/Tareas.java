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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * @author Westermeyer
 *
 */
@Entity
@Table(name = "Tareas")
public class Tareas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codtarea")
	private int CodTarea;
	
	@Column(name = "titulo")
	@NotNull
	private String titulo;
	
	@Column(name = "descripcion")
	@NotNull
	private String descripcion;
	
	@Column(name = "cerrado")
	@NotNull
	private int cerrado;
	
	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity=Usuarios.class)
	private Set<Usuarios> usuarios;
	
	@ManyToMany(targetEntity=Reunion.class)
	private Set<Reunion> reunion;
	
	@OneToOne
	private SerieReunion seriereunion;
	
	/**
	 * 
	 */
	
	public Tareas(int CodTarea, String titulo, String descripcion, int cerrado) {
		this.CodTarea = CodTarea;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.cerrado = cerrado;
	}
	
	public Tareas(String titulo, String descripcion, Set<Usuarios> usuarios, Set<Reunion> reunion, SerieReunion seriereunion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuarios = usuarios;
		this.reunion = reunion;
		this.seriereunion = seriereunion;
	}
	
	public Tareas() {
		super();
	}

	public int getCodTarea() {
		return CodTarea;
	}

	public void setCodTarea(int codTarea) {
		CodTarea = codTarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Reunion> getReunion() {
		return reunion;
	}

	public void setReunion(Set<Reunion> reunion) {
		this.reunion = reunion;
	}

	public SerieReunion getSeriereunion() {
		return seriereunion;
	}

	public void setSeriereunion(SerieReunion seriereunion) {
		this.seriereunion = seriereunion;
	}

	public int getCerrado() {
		return cerrado;
	}

	public void setCerrado(int cerrado) {
		this.cerrado = cerrado;
	}
	
}
