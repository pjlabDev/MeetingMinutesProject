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
	List<Usuarios> getUsuariosNotInReunion(int codsreunion);
	List<Usuarios> getUsuariosInReunion(int codsreunion);
	
}
