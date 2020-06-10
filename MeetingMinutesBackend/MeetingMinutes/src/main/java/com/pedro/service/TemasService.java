/**
 * 
 */
package com.pedro.service;

import java.util.List;

import com.pedro.modelo.Temas;

/**
 * The Interface TemasService.
 *
 * @author Westermeyer
 */
public interface TemasService {
	
	/**
	 * Gets the temas by cod reunion.
	 *
	 * @param codreunion the codreunion
	 * @return the temas by cod reunion
	 */
	List<Temas> getTemasByCodReunion(int codreunion);
	
	/**
	 * Gets the all temas by cod S reunion.
	 *
	 * @param codsreunion the codsreunion
	 * @return the all temas by cod S reunion
	 */
	List<Temas> getAllTemasByCodSReunion(int codsreunion);
	
	/**
	 * Crear temas.
	 *
	 * @param tema the tema
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 */
	void crearTemas(Temas tema, int codreunion, int codsreunion);
	
	/**
	 * Añadir info tema.
	 *
	 * @param tema the tema
	 * @param codTema the cod tema
	 */
	void añadirInfoTema(Temas tema, int codTema);
	
	/**
	 * Añadir decision tema.
	 *
	 * @param tema the tema
	 * @param codTema the cod tema
	 */
	void añadirDecisionTema(Temas tema, int codTema);
	
	/**
	 * Cerrar temas.
	 *
	 * @param tema the tema
	 */
	void cerrarTemas(Temas tema);
	
	/**
	 * Gets the temas by cod reunion antigua and no cerrado.
	 *
	 * @param codreunion the codreunion
	 * @param codsreunion the codsreunion
	 * @return the temas by cod reunion antigua and no cerrado
	 */
	List<Temas> getTemasByCodReunionAntiguaAndNoCerrado(int codreunion, int codsreunion);
	
	/**
	 * Save tema antiguo.
	 *
	 * @param tema the tema
	 * @param codreunion the codreunion
	 */
	void saveTemaAntiguo(Temas[] tema, int codreunion);
	
	/**
	 * Gets the tema by cod tema.
	 *
	 * @param codtema the codtema
	 * @return the tema by cod tema
	 */
	Temas getTemaByCodTema(int codtema);
	
	/**
	 * Modificar tema.
	 *
	 * @param tema the tema
	 */
	void modificarTema(Temas tema);
	
	/**
	 * Añadir seguimiento tema cerrado.
	 *
	 * @param tema the tema
	 */
	void añadirSeguimientoTemaCerrado(Temas tema);
	
}
