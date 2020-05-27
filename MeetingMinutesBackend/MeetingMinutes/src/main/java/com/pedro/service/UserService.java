/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Usuarios;

/**
 * @author Westermeyer
 *
 */
public interface UserService {
	
	Usuarios login(String nombre, String clave);
	List<Usuarios> getAllUsuarios();
	void addUsuario(Usuarios usuario);
	List<Usuarios> getUsuariosNotInSerieReunion(int codsreunion);
	List<Usuarios> getUsuariosInSerieReunion(int codsreunion);
	List<Usuarios> getUsuariosByCodReunion(int codreunion);
	List<Usuarios> getUsuariosByCodUsu(int[] codigos);
	List<Usuarios> getUsuariosNotInTarea(int codsreunion, int codtarea);
	List<Usuarios> getUsuariosNotInReunion(int codreunion, int codsreunion);
	Usuarios getUserByCodUsu(int codusu);
	void modificarUsuario(Usuarios user);
	void eliminarUsuario(int codusu);
	
}
