/**
 * 
 */
package com.pedro.service;

import java.util.List;

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
	public List<Usuarios> getAllUsuarios() {
		return userRepository.getAllUsuarios();
	}
	
	@Override
	public void addUsuario(Usuarios usuario) {
		userRepository.save(usuario);
	}

	@Override
	public List<Usuarios> getUsuariosNotInSerieReunion(int codsreunion) {
		return userRepository.getUsuariosNotInReunion(codsreunion);
	}

	@Override
	public List<Usuarios> getUsuariosInSerieReunion(int codsreunion) {
		return userRepository.getUsuariosInReunion(codsreunion);
	}

	@Override
	public List<Usuarios> getUsuariosByCodReunion(int codreunion) {
		return userRepository.getUsuariosByCodReunion(codreunion);
	}
	
}
