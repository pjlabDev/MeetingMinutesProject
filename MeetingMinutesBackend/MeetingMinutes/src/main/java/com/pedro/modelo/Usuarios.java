/**
 * 
 */
package com.pedro.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The Class Usuarios.
 *
 * @author pedro
 */
@Entity
@Table(name = "Usuarios")
public class Usuarios {

	/** The Cod usu. */
	@Id
	@Column(name = "codusu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CodUsu;

	/** The Nombre. */
	@Column(name = "nombre", unique = true)
	@NotNull
	private String Nombre;
	
	/** The Correo. */
	@Column(name = "correo", unique = true)
	@NotNull
	private String Correo;

	/** The Clave. */
	@Column(name = "clave")
	@NotNull
	private String Clave;

	/** The rol. */
	@Column(name = "rol")
	@NotNull
	private Roles rol;
	
	/**
	 * Instantiates a new usuarios.
	 *
	 * @param CodUsu the cod usu
	 * @param Nombre the nombre
	 * @param Correo the correo
	 * @param Clave the clave
	 * @param rol the rol
	 */

	public Usuarios(int CodUsu, String Nombre, String Correo, String Clave, Roles rol) {
		this.CodUsu = CodUsu;
		this.Nombre = Nombre;
		this.Correo = Correo;
		this.Clave = Clave;
		this.rol = rol;
	}
	
	/**
	 * Instantiates a new usuarios.
	 *
	 * @param Nombre the nombre
	 * @param Correo the correo
	 * @param Clave the clave
	 * @param rol the rol
	 */
	public Usuarios(String Nombre, String Correo, String Clave, Roles rol) {
		this.Nombre = Nombre;
		this.Correo = Correo;
		this.Clave = Clave;
		this.rol = rol;
	}

	/**
	 * Instantiates a new usuarios.
	 */
	public Usuarios() {
		super();
	}

	/**
	 * Gets the cod usu.
	 *
	 * @return the cod usu
	 */
	public int getCodUsu() {
		return CodUsu;
	}

	/**
	 * Sets the cod usu.
	 *
	 * @param codUsu the new cod usu
	 */
	public void setCodUsu(int codUsu) {
		CodUsu = codUsu;
	}

	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	public String getCorreo() {
		return Correo;
	}

	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		Correo = correo;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return Clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		Clave = clave;
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
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	public Roles getRol() {
		return rol;
	}

	/**
	 * Sets the rol.
	 *
	 * @param rol the new rol
	 */
	public void setRol(Roles rol) {
		this.rol = rol;
	}

}
