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
	

}