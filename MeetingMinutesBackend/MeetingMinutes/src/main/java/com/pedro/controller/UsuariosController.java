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
 * 
 * @author pedro
 * 
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/")
public class UsuariosController {
	
	@Autowired
	UserService us;
	
	@GetMapping("/login/{nombre}/{clave}")
	public Usuarios login(@PathVariable String nombre, @PathVariable String clave) {
		return us.login(nombre, clave);
	}
	
	@GetMapping("/getusuarios")
	public List<Usuarios> getAllUsuarios(){
		return us.getAllUsuarios();
	}
	
	@PostMapping("/addusuario")
	public void addUsuario(@RequestBody Usuarios usuario) {
		us.addUsuario(usuario);
	}
	
	@GetMapping("/usuariosnotinseriereunion/{codsreunion}")
	public List<Usuarios> getUsuariosNotInSReunion(@PathVariable int codsreunion){
		return us.getUsuariosNotInSerieReunion(codsreunion);
	}
	
	@GetMapping("/usuariosinseriereunion/{codsreunion}")
	public List<Usuarios> getUsuariosInSReunion(@PathVariable int codsreunion){
		return us.getUsuariosInSerieReunion(codsreunion);
	}
	
	@GetMapping("/usuariosbyreunion/{codreunion}")
	public List<Usuarios> getUsuariosByCodReunion(@PathVariable int codreunion){
		return us.getUsuariosByCodReunion(codreunion);
	}
	
	@GetMapping("/userbycodusu/{codigos}")
	public List<Usuarios> getUsuariosByCodUsu(@PathVariable int[] codigos) {
		return us.getUsuariosByCodUsu(codigos);
	}
	
	@GetMapping("/getusuariosnotintarea/{codsreunion}/{codtarea}")
	public List<Usuarios> getUsuariosNotInTarea(@PathVariable int codsreunion, @PathVariable int codtarea) {
		return us.getUsuariosNotInTarea(codsreunion, codtarea);
	}
	
	@GetMapping("/getusuariosnotinreunion/{codreunion}/{codsreunion}")
	public List<Usuarios> getUsuariosNotInReunion(@PathVariable int codreunion, @PathVariable int codsreunion) {
		return us.getUsuariosNotInReunion(codreunion, codsreunion);
	}
	
	@GetMapping("/getuserbycod/{codusu}")
	public Usuarios getUserByCodUsu(@PathVariable int codusu) {
		return us.getUserByCodUsu(codusu);
	}
	
	@PutMapping("/modificarusuario")
	public void modificarUsuario(@RequestBody Usuarios user) {
		us.modificarUsuario(user);
	}
	
	@DeleteMapping("/eliminarusuario/{codusu}")
	public int eliminarUsuario(@PathVariable int codusu) {
		return us.eliminarUsuario(codusu);
	}
	
}