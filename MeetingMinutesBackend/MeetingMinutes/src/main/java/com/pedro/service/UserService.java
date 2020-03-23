/**
 * 
 */
package com.pedro.service;

import com.pedro.modelo.Usuarios;

/**
 * @author Westermeyer
 *
 */
public interface UserService {
	
	Usuarios login(String nombre, String clave);
	void addUsuario(Usuarios usuario);
	
}
