package curso.java.tienda.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmail  {
	
    @Autowired
    private JavaMailSender emailSender;
	
	public void enviarEmail(String destinatario) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("tienda-online-curso@outlook.com");
		message.setTo(destinatario);
		message.setSubject("Registrado con éxito en BeFreak");
		message.setText("Gracias por formar parte de nuestra comunidad " + destinatario + "!" );
		emailSender.send(message);
		
	}

}
