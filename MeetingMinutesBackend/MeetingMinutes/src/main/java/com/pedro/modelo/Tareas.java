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
 * The Class Tareas.
 *
 * @author Westermeyer
 */
@Entity
@Table(name = "Tareas")
public class Tareas {

	/** The Cod tarea. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codtarea")
	private int CodTarea;
	
	/** The titulo. */
	@Column(name = "titulo")
	@NotNull
	private String titulo;
	
	/** The descripcion. */
	@Column(name = "descripcion")
	@NotNull
	private String descripcion;
	
	/** The cerrado. */
	@Column(name = "cerrado")
	@NotNull
	private int cerrado;
	
	/** The usuarios. */
	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity=Usuarios.class)
	private Set<Usuarios> usuarios;
	
	/** The reunion. */
	@ManyToMany(targetEntity=Reunion.class)
	private Set<Reunion> reunion;
	
	/** The seriereunion. */
	@OneToOne
	private SerieReunion seriereunion;
	
	/**
	 * Instantiates a new tareas.
	 *
	 * @param CodTarea the cod tarea
	 * @param titulo the titulo
	 * @param descripcion the descripcion
	 * @param cerrado the cerrado
	 */
	
	public Tareas(int CodTarea, String titulo, String descripcion, int cerrado) {
		this.CodTarea = CodTarea;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.cerrado = cerrado;
	}
	
	/**
	 * Instantiates a new tareas.
	 *
	 * @param titulo the titulo
	 * @param descripcion the descripcion
	 * @param usuarios the usuarios
	 * @param reunion the reunion
	 * @param seriereunion the seriereunion
	 */
	public Tareas(String titulo, String descripcion, Set<Usuarios> usuarios, Set<Reunion> reunion, SerieReunion seriereunion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuarios = usuarios;
		this.reunion = reunion;
		this.seriereunion = seriereunion;
	}
	
	/**
	 * Instantiates a new tareas.
	 */
	public Tareas() {
		super();
	}

	/**
	 * Gets the cod tarea.
	 *
	 * @return the cod tarea
	 */
	public int getCodTarea() {
		return CodTarea;
	}

	/**
	 * Sets the cod tarea.
	 *
	 * @param codTarea the new cod tarea
	 */
	public void setCodTarea(int codTarea) {
		CodTarea = codTarea;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/**
	 * Gets the reunion.
	 *
	 * @return the reunion
	 */
	public Set<Reunion> getReunion() {
		return reunion;
	}

	/**
	 * Sets the reunion.
	 *
	 * @param reunion the new reunion
	 */
	public void setReunion(Set<Reunion> reunion) {
		this.reunion = reunion;
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
	 * Gets the cerrado.
	 *
	 * @return the cerrado
	 */
	public int getCerrado() {
		return cerrado;
	}

	/**
	 * Sets the cerrado.
	 *
	 * @param cerrado the new cerrado
	 */
	public void setCerrado(int cerrado) {
		this.cerrado = cerrado;
	}
	
}
