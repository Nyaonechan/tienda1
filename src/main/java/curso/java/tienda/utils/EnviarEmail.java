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
		message.setText("Te has registrado en nuestra tienda!\nGracias por formar parte de nuestra comunidad " + destinatario + "!" );
		emailSender.send(message);
		
	}
	
	public void enviarEmailSuscrito(String destinatario, String nombreCliente) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("tienda-online-curso@outlook.com");
		message.setTo(destinatario);
		message.setSubject("Te has suscrito a la newsletter de BeFreak");
		message.setText("Gracias por suscribirte" + nombreCliente + "!\nA partir de ahora no te perderás ninguna novedad.\nAquí tienes un código de descuento para tu próxima compra:\nCódigo:diez\nDescuento:10%\nEsperamos que lo disfrutes!" );
		emailSender.send(message);
		
	}
	
	public void enviarEmailContacto(String destinatario, String nombreCliente, String subject, String mensaje) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("tienda-online-curso@outlook.com");
		message.setTo(destinatario);
		message.setSubject("Hemos recibido tu mensaje: " + subject);
		message.setText("Estimado/a " + nombreCliente + ", hemos recibido tu mensaje: \nSubject: " + subject +"\nMessage: " + mensaje + "\nResolveremos tu consulta lo antes posible." );
		emailSender.send(message);
		
	}

}
