/**
 * 
 */
package com.pedro.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.Actas;
import com.pedro.modelo.Reunion;
import com.pedro.modelo.Usuarios;
import com.pedro.repository.ActasRepository;
import com.pedro.repository.ReunionRepository;
import com.pedro.repository.UserRepository;

/**
 * @author Westermeyer
 *
 */
@Service
public class ActaServiceImpl implements ActaService {

	@Autowired
	ActasRepository ar;
	
	@Autowired
	UserRepository useR;

	@Autowired
	ReunionRepository rr;
	
	@Override
	public void generarActa(Actas acta, int codReunion, int[] codusu) {
		Set<Usuarios> usuario = new HashSet<>();
		Reunion reunion;
		
		for (int i : codusu) {
			Usuarios user = useR.findOne(i);
			
			usuario.add(user);
		}
		
		reunion = rr.findOne(codReunion);
		
		Actas newAc = new Actas(acta.getFecha(),reunion,usuario);
		
		ar.save(newAc);
		
	}

}
