package clases;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo { 
	  
	private Properties props = new Properties();
	private Session session = null;
	
	
	public Correo(){
		props.put("mail.smtp.host", Xml.getHost());
		props.put("mail.smtp.socketFactory.port", Xml.getPuertoFactory());
		props.put("mail.smtp.socketFactory.class", Xml.getClase());
		props.put("mail.smtp.auth", Xml.getAutenticacion());
		props.put("mail.smtp.port", Xml.getPuerto());
		
		session = Session.getDefaultInstance(props, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Xml.getUsuario(), Xml.getContrasena());
				}
			}
		);
	}
	
	
	public void enviar(final String para_, final String asunto_, final String cuerpo_, final String nombreArchivo_){
		try {
			session.setDebug(Boolean.valueOf(Xml.getDebug()));
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Xml.getUsuario()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para_));
			message.setSubject(asunto_);
			
	         BodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setText(cuerpo_);
	         Multipart multipart = new MimeMultipart();
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	         String filename = nombreArchivo_;
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         //messageBodyPart.setFileName(Xml.getNombreArchivo());
	         messageBodyPart.setFileName(nombreArchivo_);
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart );
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void enviar(final String para_, final String asunto_, final String cuerpo_){
		try {
			session.setDebug(Boolean.valueOf(Xml.getDebug()));
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Xml.getUsuario()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para_));
			message.setSubject(asunto_);
			message.setContent(cuerpo_, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new Correo().enviar("csantana@nexusfuel.com, huosantana@hotmail.com", "Test", "Cuerpo Test"); 
	}
}