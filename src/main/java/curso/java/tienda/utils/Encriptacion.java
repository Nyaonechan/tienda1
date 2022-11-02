package curso.java.tienda.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encriptacion {
	
	public static final String PASSWORD = "miclave";
	
	public static String encriptar(String contra) {
	
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(PASSWORD);

        String encriptada = s.encrypt(contra);
        System.out.println("encriptado: " + encriptada);
        
        return encriptada;
        
	}
	
	public static String desencriptar(String contra) {
		
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(PASSWORD);

        String desencriptada = s.decrypt(contra); 
        System.out.println("desencriptado: " + desencriptada);
        
        return desencriptada;
	}

}
