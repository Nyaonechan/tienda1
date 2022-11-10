package curso.java.tienda.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zips {
	
	public static void zip(String destZipFile) throws FileNotFoundException, IOException {
    	//String[] files={"C:\\Users\\anixx\\Desktop\\Spring\\tienda"}; // cambiar la ruta de donde está el proyecto
    	String[] files={"C:\\Users\\Formacion\\eclipse-workspace\\tienda1"};
    	
        List<File> listFiles = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            listFiles.add(new File(files[i]));
        }
        zip(listFiles, destZipFile);
    }
    
    public static void zip(List<File> listFiles, String destZipFile) throws FileNotFoundException,
    IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
		for (File file : listFiles) {
		    if (file.isDirectory()) {
		        zipDirectory(file, file.getName(), zos);
		    } else {
		        zipFile(file, zos);
		    }
		}
		zos.flush();
		zos.close();
		}
	
    private static void zipDirectory(File folder, String parentFolder,
            ZipOutputStream zos) throws FileNotFoundException, IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipDirectory(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }
            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
            }
            zos.closeEntry();
        }
    }
    
    
    private static void zipFile(File file, ZipOutputStream zos)
            throws FileNotFoundException, IOException {
        zos.putNextEntry(new ZipEntry(file.getName()));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = bis.read(bytesIn)) != -1) {
            zos.write(bytesIn, 0, read);
        }
        zos.closeEntry();
    }
    
    public static void backupBBDD() {
       
        String username = "root";
        String password = "";
        String dbname = "tiendaweb";
    	File f = new File(dbname + ".sql");
    	String path = f.getPath();
        String executeCmd = "C:/Program Files/MySQL/MySQL Workbench 8.0 CE/mysqldump -u" + username + " -p" + password
                + " --add-drop-database -B " + dbname + " -r " + path;
        Process runtimeProcess;
        try {
//            System.out.println(executeCmd);//this out put works in mysql shell
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            System.out.println(executeCmd);
//            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            //int processComplete = runtimeProcess.waitFor();
            //System.out.println("processComplete" + processComplete);
            //if (processComplete == 0) {
            //    System.out.println("Backup created successfully");

           // } else {
            //    System.out.println("Could not create the backup");
            //}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	
    }

}
