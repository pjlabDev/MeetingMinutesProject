/**
 * 
 */
package com.pedro.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.modelo.Tareas;
import com.pedro.modelo.Temas;
import com.pedro.modelo.Usuarios;
import com.pedro.repository.TareasRepository;
import com.pedro.repository.TemasRepository;
import com.pedro.repository.UserRepository;

/**
 * @author Westermeyer
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	UserRepository userR;
	
	@Autowired
	TemasRepository tr;
	
	@Autowired
	TareasRepository taR;
	
	@Override
	public void enviarAgenda(int[] receptores, String fechaReunion, Temas[] tema) {
		
		String temas = "";
		String participantes = "";
		
	    String emisor = "vconvents@gmail.com";

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    
	    props.put("mail.smtp.user", emisor);
	    
	    props.put("mail.smtp.clave", "123456789#abc");
	    
	    props.put("mail.smtp.auth", "true");
	    
	    props.put("mail.smtp.starttls.enable", "true");
	    
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(emisor));
	        
	        for (int rec : receptores) {
				Usuarios us = userR.findOne(rec);
				message.addRecipients(Message.RecipientType.TO, us.getCorreo());		
				participantes += "  - " + us.getRol() + " - " + us.getNombre() + "\n";
			}
	        
	        for (Temas tem : tema) {
				temas += "  - " + tem.getTitulo() + "  " + "*" + tem.getEtiqueta() + "*" + "\n";
			}
	        
	        message.setSubject("Información reuniones.");
	        if(temas == "") {
	        	message.setText("Estimados Colaboradores, adjuntamos información sobre la próxima reunión:" + "\n\n" + "REUNIÓN para el día: " +
		        		"\n" + "  * " + fechaReunion + " *" + "\n\n" + "TEMAS a debatir: " + "\n"+ "Ninguno por el momento." + "\n\n" + "PARTICIPANTES.- " + "\n" + participantes + "\n" + "Saludos.");
	        }else {
	        	message.setText("Estimados Colaboradores, adjuntamos información sobre la próxima reunión:" + "\n\n" + "REUNIÓN para el día: " +
	        			"\n" + "  * " + fechaReunion + " *" + "\n\n" + "TEMAS a debatir: " + "\n"+ temas + "\n" + "PARTICIPANTES.- " + "\n" + participantes + "\n" + "Saludos.");	        	
	        }
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", emisor, "123456789#abc");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	    }
		
	}

	@Override
	public void enviarActa(int[] receptores, String fechaActa, int[] codtema, int[] codtarea, String conclusion) {
		
		String temas = "";
		String tareas = "";
		String asistentes = "";
		String users = "";
		
		String emisor = "vconvents@gmail.com";

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    
	    props.put("mail.smtp.user", emisor);
	    
	    props.put("mail.smtp.clave", "123456789#abc");
	    
	    props.put("mail.smtp.auth", "true");
	    
	    props.put("mail.smtp.starttls.enable", "true");
	    
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(emisor));
	        
	        for (int rec : receptores) {
				Usuarios us = userR.findOne(rec);
				message.addRecipients(Message.RecipientType.TO, us.getCorreo());		
				asistentes += "  - " + us.getRol() + " - " + us.getNombre() + "\n";
			}
	        
	        if(codtema[0] == -1) {
	        	temas = "";
	        }else {
	        	for (int codtem : codtema) {
		        	Temas tema = tr.findOne(codtem);
		        	if(tema.getCerrado() == 1) {
		        		temas += "  - " + tema.getTitulo() + "  " + "*" + tema.getEtiqueta() + "*" + " " + "(CERRADO)" + "\n";	        		
		        	}else {
		        		temas += "  - " + tema.getTitulo() + "  " + "*" + tema.getEtiqueta() + "*" + "\n";
		        	}
				}
	        }
	        
	        if(codtarea[0] == -1) {
	        	tareas = "";
	        }else {
	        	for (int codtar : codtarea) {
	        		Tareas tarea = taR.findOne(codtar);
	        		if(tarea.getCerrado() == 1) {
	        			for(Usuarios us : tarea.getUsuarios()) {
	        				users += "          " + us.getRol() + " - " + us.getNombre() + "\n";
	        			}
	        			tareas += "  - " + tarea.getTitulo() + " (CERRADA)" + "\n" + "   Detalles: " + "\n" + tarea.getDescripcion() + "\n" + "   Responsables: " + "\n" + users + "\n";
	        			users = "";
	        		}else {
	        			for(Usuarios us : tarea.getUsuarios()) {
	        				users += "          " + us.getRol() + " - " + us.getNombre() + "\n";
	        			}
	        			tareas += "  - " + tarea.getTitulo() + "\n" + "     Detalles: " + "\n" + "        " + tarea.getDescripcion() + "\n" + "     Responsables: " + "\n" + users + "\n";
	        			users = "";
	        		}
	        	}
	        }
	        
	        message.setSubject("Información Acta.");
	        
	        if(temas == "" && tareas == "") {
	        	
	        	message.setText("Estimados Colaboradores, adjuntamos el Acta de la reunión:" + "\n\n" + "Acta generada el día: " + "*" + fechaActa + "*" + "\n\n" + "TEMAS debatidos.- " + "\n" + "No se han debatido temas." + 
		        		"\n\n" + "TAREAS asignadas.- " + "\n" + "No se han asignado tareas." + "\n\n" + "ASISTEN.- " + "\n" + asistentes + "\n" + "CONCLUSION.- " + "\n" + conclusion + "\n\n" + "Saludos.");
	        	
	        }else if(temas == "" && tareas != "") {
	        	
	        	message.setText("Estimados Colaboradores, adjuntamos el Acta de la reunión:" + "\n\n" + "Acta generada el día: " + "*" + fechaActa + "*" + "\n\n" + "TEMAS debatidos.- " + "\n" + "No se han debatido temas." + 
		        		"\n\n" + "TAREAS asignadas.- " + "\n" + tareas + "\n" + "ASISTEN.- " + "\n" + asistentes + "\n\n"  + "CONCLUSION.- " + "\n" + conclusion + "\n\n" + "Saludos.");
	        	
	        }else if(temas != "" && tareas == "") {
	        	
	        	message.setText("Estimados Colaboradores, adjuntamos el Acta de la reunión:" + "\n\n" + "Acta generada el día: " + "*" + fechaActa + "*" + "\n\n" + "TEMAS debatidos.- " + "\n" + temas + 
		        		"\n" + "TAREAS asignadas.- " + "\n" + "No se han asignado tareas." + "\n\n" + "ASISTEN.- " + "\n" + asistentes + "\n"  + "CONCLUSION.- " + "\n" + conclusion + "\n\n" + "Saludos.");
	        	
	        }else if(temas != "" && tareas != "") {
	        	
	        	message.setText("Estimados Colaboradores, adjuntamos el Acta de la reunión:" + "\n\n" + "Acta generada el día: " + "*" + fechaActa + "*" + "\n\n" + "TEMAS debatidos.- " + "\n" + temas + 
	        			"\n" + "TAREAS asignadas.- " + "\n" + tareas + "\n" + "ASISTEN.- " + "\n" + asistentes + "\n"  + "CONCLUSION.- " + "\n" + conclusion + "\n\n" + "Saludos.");
	        }
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", emisor, "123456789#abc");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	    }
		
	}
	

}
