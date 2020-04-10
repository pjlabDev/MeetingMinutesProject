/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Reunion;

/**
 * @author Westermeyer
 *
 */
public interface ReunionService {
	
	List<Reunion> getReunionBySerieReunion(int codsreunion);
	Reunion getReunionByCodReunion(int codreunion);
	List<Reunion> getReunionByUsuario(int codusu);
	void crearReunion(Reunion reunion, int codsreunion, int[] codsusu);
	
}
