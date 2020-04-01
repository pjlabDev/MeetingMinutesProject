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
 * @author pedro
 *
 */
@Entity
@Table(name = "Usuarios")
public class Usuarios {

	@Id
	@Column(name = "codusu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CodUsu;

	@Column(name = "nombre", unique = true)
	@NotNull
	private String Nombre;
	
	@Column(name = "correo", unique = true)
	@NotNull
	private String Correo;

	@Column(name = "clave")
	@NotNull
	private String Clave;

	@Column(name = "rol")
	@NotNull
	private Roles rol;

	/**
	 * 
	 */

	public Usuarios(int CodUsu, String Nombre, String Correo, String Clave, Roles rol) {
		this.CodUsu = CodUsu;
		this.Nombre = Nombre;
		this.Correo = Correo;
		this.Clave = Clave;
		this.rol = rol;
	}
	
	public Usuarios(String Nombre, String Correo, String Clave, Roles rol) {
		this.Nombre = Nombre;
		this.Correo = Correo;
		this.Clave = Clave;
		this.rol = rol;
	}

	public Usuarios() { }

	public int getCodUsu() {
		return CodUsu;
	}

	public void setCodUsu(int codUsu) {
		CodUsu = codUsu;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}
	

}
