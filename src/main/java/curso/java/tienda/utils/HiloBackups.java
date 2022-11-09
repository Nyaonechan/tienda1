package curso.java.tienda.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author anixx
 * 
 *         Clase para el hilo de los backups Se activa o desactiva mediante un
 *         botón en la interfaz
 *
 */
public class HiloBackups extends Thread {

	private static final int TIEMPO_ENTRE_BACKUPS = 120;// 120segundos

	public static boolean HiloActivo = true;

	public void run() {
		HiloActivo = true;
		while (HiloActivo) {
			try {
				TimeUnit.SECONDS.sleep(TIEMPO_ENTRE_BACKUPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				System.out.println("Haciendo backup");
				//Zips.backupBBDD();
				Date fecha = new Date();
				String fe = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(fecha);
				Zips.zip("C:\\Users\\anixx\\OneDrive\\BackupsProyecto\\backup" + fe + ".zip");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void detenerHilo() {
		HiloActivo = false;
	}
}
