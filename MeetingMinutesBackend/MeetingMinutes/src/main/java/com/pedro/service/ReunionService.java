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
	List<Reunion> getReuniones(int codusu, int codsreunion);
	void crearReunion(Reunion reunion, int codsreunion, int[] codsusu);
	
}
