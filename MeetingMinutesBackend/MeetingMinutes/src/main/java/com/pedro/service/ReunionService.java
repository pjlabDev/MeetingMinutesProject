/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Reunion;

/**
 * The Interface ReunionService.
 *
 * @author Westermeyer
 */
public interface ReunionService {
	
	/**
	 * Gets the reunion by serie reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the reunion by serie reunion
	 */
	List<Reunion> getReunionBySerieReunion(int codsreunion);
	
	/**
	 * Gets the reunion by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the reunion by cod reunion
	 */
	Reunion getReunionByCodReunion(int codreunion);
	
	/**
	 * Gets the reuniones.
	 *
	 * @param codusu the codusu
	 * @param codsreunion the codsreunion
	 * @return the reuniones
	 */
	List<Reunion> getReuniones(int codusu, int codsreunion);
	
	/**
	 * Crear reunion.
	 *
	 * @param reunion the reunion
	 * @param codsreunion the codsreunion
	 * @param codsusu the codsusu
	 */
	void crearReunion(Reunion reunion, int codsreunion, int[] codsusu);
	
	/**
	 * Modificar reunion.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	void modificarReunion(Reunion reunion, int[] codusu);
	
	/**
	 * Eliminar participante.
	 *
	 * @param reunion the reunion
	 * @param codusu the codusu
	 */
	void eliminarParticipante(Reunion reunion, int codusu);
	
}
