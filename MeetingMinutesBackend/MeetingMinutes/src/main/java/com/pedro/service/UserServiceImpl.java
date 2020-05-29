/**
 * 
 */
package com.pedro.service;

import java.util.ArrayList;
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
		return userRepository.getUsuariosNotInSerieReunion(codsreunion);
	}

	@Override
	public List<Usuarios> getUsuariosInSerieReunion(int codsreunion) {
		return userRepository.getUsuariosInSerieReunion(codsreunion);
	}

	@Override
	public List<Usuarios> getUsuariosByCodReunion(int codreunion) {
		return userRepository.getUsuariosByCodReunion(codreunion);
	}

	@Override
	public List<Usuarios> getUsuariosByCodUsu(int[] codigos) {
		
		List<Usuarios> users = new ArrayList<Usuarios>();
		
		for (int cods : codigos) {
			Usuarios user = userRepository.findOne(cods);
			
			if(user != null) {
				users.add(user);
			}
			
		}
		
		return users;
	}

	@Override
	public List<Usuarios> getUsuariosNotInTarea(int codsreunion, int codtarea) {
		return userRepository.getUsuariosNotInTarea(codsreunion, codtarea);
	}

	@Override
	public List<Usuarios> getUsuariosNotInReunion(int codreunion, int codsreunion) {
		return userRepository.getUsuariosNotInReunion(codreunion, codsreunion);
	}

	@Override
	public Usuarios getUserByCodUsu(int codusu) {
		return userRepository.getUserByCodUsu(codusu);
	}

	@Override
	public void modificarUsuario(Usuarios user) {
		
		Usuarios updateUser = userRepository.findOne(user.getCodUsu());
		
		if(updateUser != null) {
			
			updateUser.setNombre(user.getNombre());
			updateUser.setCorreo(user.getCorreo());
			updateUser.setClave(user.getClave());
			updateUser.setRol(user.getRol());
			userRepository.save(updateUser);
		}
		
	}

	@Override
	public int eliminarUsuario(int codusu) {
		Usuarios deleteUser = userRepository.findOne(codusu);
		
		if(deleteUser != null) {
			try {
				userRepository.delete(deleteUser);
				return 1;
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}
	
}
