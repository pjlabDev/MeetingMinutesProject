/**
 * 
 */
package com.pedro.modelo;

import java.util.Set;

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
	
	@ManyToMany(targetEntity=Usuarios.class)
	private Set<Usuarios> usuarios;
	
	@OneToOne
	private Reunion reunion;
	
	/**
	 * 
	 */
	
	public Tareas(int CodTarea, String titulo, String descripcion) {
		this.CodTarea = CodTarea;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	
	public Tareas(String titulo, String descripcion, Set<Usuarios> usuarios, Reunion reunion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuarios = usuarios;
		this.reunion = reunion;
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

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

}
