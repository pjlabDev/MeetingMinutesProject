/**
 * 
 */
package com.pedro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.modelo.Usuarios;
import com.pedro.service.UserService;

/**
 * The Class UsuariosController.
 *
 * @author pedro
 */
@RestController
@CrossOrigin(origins = "https://mmfront-project.herokuapp.com")
@RequestMapping(value="/usuarios")
public class UsuariosController {
	
	/** The us. */
	@Autowired
	UserService us;
	
	/**
	 * Login.
	 *
	 * @param nombre the nombre
	 * @param clave the clave
	 * @return the usuarios
	 */
	@GetMapping("/login/{nombre}/{clave}")
	public Usuarios login(@PathVariable String nombre, @PathVariable String clave) {
		return us.login(nombre, clave);
	}
	
	/**
	 * Gets the all usuarios.
	 *
	 * @return the all usuarios
	 */
	@GetMapping("/getusuarios")
	public List<Usuarios> getAllUsuarios(){
		return us.getAllUsuarios();
	}
	
	/**
	 * Adds the usuario.
	 *
	 * @param usuario the usuario
	 */
	@PostMapping("/addusuario")
	public void addUsuario(@RequestBody Usuarios usuario) {
		us.addUsuario(usuario);
	}
	
	/**
	 * Gets the usuarios not in S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the usuarios not in S reunion
	 */
	@GetMapping("/usuariosnotinseriereunion/{codsreunion}")
	public List<Usuarios> getUsuariosNotInSReunion(@PathVariable int codsreunion){
		return us.getUsuariosNotInSerieReunion(codsreunion);
	}
	
	/**
	 * Gets the usuarios in S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the usuarios in S reunion
	 */
	@GetMapping("/usuariosinseriereunion/{codsreunion}")
	public List<Usuarios> getUsuariosInSReunion(@PathVariable int codsreunion){
		return us.getUsuariosInSerieReunion(codsreunion);
	}
	
	/**
	 * Gets the usuarios by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the usuarios by cod reunion
	 */
	@GetMapping("/usuariosbyreunion/{codreunion}")
	public List<Usuarios> getUsuariosByCodReunion(@PathVariable int codreunion){
		return us.getUsuariosByCodReunion(codreunion);
	}
	
	/**
	 * Gets the usuarios by cod usu.
	 *
	 * @param codigos the codigos
	 * @return the usuarios by cod usu
	 */
	@GetMapping("/userbycodusu/{codigos}")
	public List<Usuarios> getUsuariosByCodUsu(@PathVariable int[] codigos) {
		return us.getUsuariosByCodUsu(codigos);
	}
	
	/**
	 * Gets the usuarios not in tarea.
	 *
	 * @param codsreunion the codsreunion
	 * @param codtarea the codtarea
	 * @return the usuarios not in tarea
	 */
	@GetMapping("/getusuariosnotintarea/{codsreunion}/{codtarea}")
	public List<Usuarios> getUsuariosNotInTarea(@PathVariable int codsreunion, @PathVariable int codtarea) {
		return us.getUsuariosNotInTarea(codsreunion, codtarea);
	}
	
	/**
	 * Gets the usuarios not in reunion.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the usuarios not in reunion
	 */
	@GetMapping("/getusuariosnotinreunion/{codreunion}/{codsreunion}")
	public List<Usuarios> getUsuariosNotInReunion(@PathVariable int codreunion, @PathVariable int codsreunion) {
		return us.getUsuariosNotInReunion(codreunion, codsreunion);
	}
	
	/**
	 * Gets the user by cod usu.
	 *
	 * @param codusu the codusu
	 * @return the user by cod usu
	 */
	@GetMapping("/getuserbycod/{codusu}")
	public Usuarios getUserByCodUsu(@PathVariable int codusu) {
		return us.getUserByCodUsu(codusu);
	}
	
	/**
	 * Modificar usuario.
	 *
	 * @param user the user
	 */
	@PutMapping("/modificarusuario")
	public void modificarUsuario(@RequestBody Usuarios user) {
		us.modificarUsuario(user);
	}
	
	/**
	 * Eliminar usuario.
	 *
	 * @param codusu the codusu
	 * @return the int
	 */
	@DeleteMapping("/eliminarusuario/{codusu}")
	public int eliminarUsuario(@PathVariable int codusu) {
		return us.eliminarUsuario(codusu);
	}
	
}