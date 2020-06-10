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
 * The Class Archivos.
 *
 * @author Westermeyer
 */
@Entity
@Table(name = "Archivos")
public class Archivos {

	/** The Cod archivo. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codarchivo")
	private int CodArchivo;
	
	/** The nombre. */
	@Column(name = "nombre")
	@NotNull
	private String nombre;
	
	/** The archivo. */
	@Lob
	@Column(name = "archivo")
	@NotNull
	private byte[] archivo;
	
	/** The reunion. */
	@OneToOne
	private Reunion reunion;
	
	/**
	 * Instantiates a new archivos.
	 *
	 * @param CodArchivo the cod archivo
	 * @param nombre the nombre
	 * @param archivo the archivo
	 */
	
	public Archivos(int CodArchivo, String nombre, byte[] archivo) {
		this.CodArchivo = CodArchivo;
		this.nombre = nombre;
		this.archivo = archivo;
	}
	
	/**
	 * Instantiates a new archivos.
	 *
	 * @param nombre the nombre
	 * @param archivo the archivo
	 * @param reunion the reunion
	 */
	public Archivos(String nombre, byte[] archivo, Reunion reunion) {
		this.nombre = nombre;
		this.archivo = archivo;
		this.reunion = reunion;
	}
	
	/**
	 * Instantiates a new archivos.
	 */
	public Archivos() {
		super();
	}


	/**
	 * Gets the cod archivo.
	 *
	 * @return the cod archivo
	 */
	public int getCodArchivo() {
		return CodArchivo;
	}


	/**
	 * Sets the cod archivo.
	 *
	 * @param codArchivo the new cod archivo
	 */
	public void setCodArchivo(int codArchivo) {
		CodArchivo = codArchivo;
	}


	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public byte[] getArchivo() {
		return archivo;
	}


	/**
	 * Sets the archivo.
	 *
	 * @param archivo the new archivo
	 */
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}
