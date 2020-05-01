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

import com.pedro.modelo.Temas;
import com.pedro.modelo.Usuarios;
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
	
	@Override
	public void enviarAgenda(int[] receptores, String fechaReunion, Temas[] tema, int[] temasAntiguos) {
		
		String temas = "";
		String participantes = "";
		String temasAnt = "";
		
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
	        
	        if (temasAntiguos[0] != -1) {	        	
	        	for (int tem: temasAntiguos) {
	        		Temas te = tr.findOne(tem);
	        		temasAnt += "  - " + te.getTitulo() + "  " + "*" + te.getEtiqueta() + "*" + "\n";
	        	}
	        } else {
	        	temasAnt = "";
	        }
	        
	        message.setSubject("Información reuniones.");
	        if(temasAnt == "") {
	        	message.setText("Estimados colaboradores, adjuntamos información sobre la próxima reunión:" + "\n\n" + "REUNIÓN para el día: " +
	        			"\n" + "  * " + fechaReunion + " *" + "\n\n" + "TEMAS a debatir: " + "\n"+ temas + "\n" + "PARTICIPANTES.- " + "\n" + participantes + "\n" + "Saludos.");
	        }else {
	        	message.setText("Estimados colaboradores, adjuntamos información sobre la próxima reunión:" + "\n\n" + "REUNIÓN para el día: " +
	        			"\n" + "  * " + fechaReunion + " *" + "\n\n" + "TEMAS a debatir: " + "\n"+ temas + "\n" + "TEMAS ANTIGUOS: " + "\n" + temasAnt + "\n" + "PARTICIPANTES.- " + "\n" + participantes + "\n" + "Saludos.");
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
