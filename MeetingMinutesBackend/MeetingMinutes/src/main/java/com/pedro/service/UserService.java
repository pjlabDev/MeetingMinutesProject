/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Usuarios;

/**
 * The Interface UserService.
 *
 * @author Westermeyer
 */
public interface UserService {
	
	/**
	 * Login.
	 *
	 * @param nombre the nombre
	 * @param clave the clave
	 * @return the usuarios
	 */
	Usuarios login(String nombre, String clave);
	
	/**
	 * Gets the all usuarios.
	 *
	 * @return the all usuarios
	 */
	List<Usuarios> getAllUsuarios();
	
	/**
	 * Adds the usuario.
	 *
	 * @param usuario the usuario
	 */
	void addUsuario(Usuarios usuario);
	
	/**
	 * Gets the usuarios not in serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the usuarios not in serie reunion
	 */
	List<Usuarios> getUsuariosNotInSerieReunion(int codsreunion);
	
	/**
	 * Gets the usuarios in serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the usuarios in serie reunion
	 */
	List<Usuarios> getUsuariosInSerieReunion(int codsreunion);
	
	/**
	 * Gets the usuarios by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the usuarios by cod reunion
	 */
	List<Usuarios> getUsuariosByCodReunion(int codreunion);
	
	/**
	 * Gets the usuarios by cod usu.
	 *
	 * @param codigos the codigos
	 * @return the usuarios by cod usu
	 */
	List<Usuarios> getUsuariosByCodUsu(int[] codigos);
	
	/**
	 * Gets the usuarios not in tarea.
	 *
	 * @param codsreunion the codsreunion
	 * @param codtarea the codtarea
	 * @return the usuarios not in tarea
	 */
	List<Usuarios> getUsuariosNotInTarea(int codsreunion, int codtarea);
	
	/**
	 * Gets the usuarios not in reunion.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the usuarios not in reunion
	 */
	List<Usuarios> getUsuariosNotInReunion(int codreunion, int codsreunion);
	
	/**
	 * Gets the user by cod usu.
	 *
	 * @param codusu the codusu
	 * @return the user by cod usu
	 */
	Usuarios getUserByCodUsu(int codusu);
	
	/**
	 * Modificar usuario.
	 *
	 * @param user the user
	 */
	void modificarUsuario(Usuarios user);
	
	/**
	 * Eliminar usuario.
	 *
	 * @param codusu the codusu
	 * @return the int
	 */
	int eliminarUsuario(int codusu);
	
}
