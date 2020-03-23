/**
 * 
 */
package com.pedro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.Usuarios;
import com.pedro.repository.UserRepository;

/**
 * @author Westermeyer
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Usuarios login(String nombre, String clave) {
		return userRepository.login(nombre, clave);
	}

	@Override
	public void addUsuario(Usuarios usuario) {
		userRepository.save(usuario);
	}
	
}
