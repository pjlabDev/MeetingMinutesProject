/**
 * 
 */
package com.pedro.service;

import com.pedro.modelo.Temas;

/**
 * The Interface EmailService.
 *
 * @author Westermeyer
 */
public interface EmailService {
	
	/**
	 * Enviar agenda.
	 *
	 * @param receptores the receptores
	 * @param fechaReunion the fecha reunion
	 * @param tema the tema
	 */
	public void enviarAgenda(int[] receptores, String fechaReunion, Temas[] tema);
	
	/**
	 * Enviar acta.
	 *
	 * @param receptores the receptores
	 * @param fechaActa the fecha acta
	 * @param codtema the codtema
	 * @param codtarea the codtarea
	 * @param conclusion the conclusion
	 */
	public void enviarActa(int[] receptores, String fechaActa, int[] codtema, int[] codtarea, String conclusion);
	
	/**
	 * Enviar tarea.
	 *
	 * @param codtarea the codtarea
	 */
	public void enviarTarea(int codtarea);
	
	/**
	 * Enviar comentario.
	 *
	 * @param nombre the nombre
	 * @param correo the correo
	 * @param comentario the comentario
	 */
	public void enviarComentario(String nombre, String correo, String comentario);
	
}
