/**
 * 
 */
package com.pedro.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Westermeyer
 *
 */
@Entity
@Table(name = "Archivos")
public class Archivos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codarchivo")
	private int CodArchivo;
	
	@Column(name = "nombre")
	@NotNull
	private String nombre;
	
	@Lob
	@Column(name = "archivo")
	@NotNull
	private byte[] archivo;
	
	@OneToOne
	private Reunion reunion;
	
	/**
	 * 
	 */
	
	public Archivos(int CodArchivo, String nombre, byte[] archivo) {
		this.CodArchivo = CodArchivo;
		this.nombre = nombre;
		this.archivo = archivo;
	}
	
	public Archivos(String nombre, byte[] archivo, Reunion reunion) {
		this.nombre = nombre;
		this.archivo = archivo;
		this.reunion = reunion;
	}
	
	public Archivos() {
		super();
	}


	public int getCodArchivo() {
		return CodArchivo;
	}


	public void setCodArchivo(int codArchivo) {
		CodArchivo = codArchivo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public byte[] getArchivo() {
		return archivo;
	}


	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}
