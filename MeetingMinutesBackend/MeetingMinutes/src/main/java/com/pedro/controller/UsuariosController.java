/**
 * 
 */
package com.pedro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/usuariosnotinreunion/{codsreunion}")
	public List<Usuarios> getUsuariosNotInReunion(@PathVariable int codsreunion){
		return us.getUsuariosNotInSerieReunion(codsreunion);
	}
	
	@GetMapping("/usuariosinreunion/{codsreunion}")
	public List<Usuarios> getUsuariosInReunion(@PathVariable int codsreunion){
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
	
}