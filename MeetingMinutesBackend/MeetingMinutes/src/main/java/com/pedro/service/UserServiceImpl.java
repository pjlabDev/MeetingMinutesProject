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
 * The Class UserServiceImpl.
 *
 * @author Westermeyer
 */

@Service
public class UserServiceImpl implements UserService {

	/** The userRepository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Login.
	 *
	 * @param nombre the nombre
	 * @param clave the clave
	 * @return the usuarios
	 */
	@Override
	public Usuarios login(String nombre, String clave) {
		return userRepository.login(nombre, clave);
	}

	/**
	 * Gets the all usuarios.
	 *
	 * @return the all usuarios
	 */
	@Override
	public List<Usuarios> getAllUsuarios() {
		return userRepository.getAllUsuarios();
	}
	
	/**
	 * Adds the usuario.
	 *
	 * @param usuario the usuario
	 */
	@Override
	public void addUsuario(Usuarios usuario) {
		userRepository.save(usuario);
	}

	/**
	 * Gets the usuarios not in serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the usuarios not in serie reunion
	 */
	@Override
	public List<Usuarios> getUsuariosNotInSerieReunion(int codsreunion) {
		return userRepository.getUsuariosNotInSerieReunion(codsreunion);
	}

	/**
	 * Gets the usuarios in serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the usuarios in serie reunion
	 */
	@Override
	public List<Usuarios> getUsuariosInSerieReunion(int codsreunion) {
		return userRepository.getUsuariosInSerieReunion(codsreunion);
	}

	/**
	 * Gets the usuarios by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the usuarios by cod reunion
	 */
	@Override
	public List<Usuarios> getUsuariosByCodReunion(int codreunion) {
		return userRepository.getUsuariosByCodReunion(codreunion);
	}

	/**
	 * Gets the usuarios by cod usu.
	 *
	 * @param codigos the codigos
	 * @return the usuarios by cod usu
	 */
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

	/**
	 * Gets the usuarios not in tarea.
	 *
	 * @param codsreunion the codsreunion
	 * @param codtarea the codtarea
	 * @return the usuarios not in tarea
	 */
	@Override
	public List<Usuarios> getUsuariosNotInTarea(int codsreunion, int codtarea) {
		return userRepository.getUsuariosNotInTarea(codsreunion, codtarea);
	}

	/**
	 * Gets the usuarios not in reunion.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the usuarios not in reunion
	 */
	@Override
	public List<Usuarios> getUsuariosNotInReunion(int codreunion, int codsreunion) {
		return userRepository.getUsuariosNotInReunion(codreunion, codsreunion);
	}

	/**
	 * Gets the user by cod usu.
	 *
	 * @param codusu the codusu
	 * @return the user by cod usu
	 */
	@Override
	public Usuarios getUserByCodUsu(int codusu) {
		return userRepository.getUserByCodUsu(codusu);
	}

	/**
	 * Modificar usuario.
	 *
	 * @param user the user
	 */
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

	/**
	 * Eliminar usuario.
	 *
	 * @param codusu the codusu
	 * @return the int
	 */
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
