package curso.java.tienda.utils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import curso.java.tienda.dao.ProductosDao;
import curso.java.tienda.entities.Productos;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Component
public class Excel {
	
	@Autowired
	ProductosDao productoDao;
	
	public void exportarCatalogo() {
		
		ArrayList<Productos> productos = productoDao.getProductos();
		
		File fichero = new File("src/main/resources/ficheros/catalogo.xls");
		
        try {
        	WritableWorkbook w = Workbook.createWorkbook(fichero);
        	

        	//Workbook wb = Workbook.getWorkbook(fichero);
        	//WritableWorkbook w = Workbook.createWorkbook(fichero, wb);
        	
        	
        	//Nombre de la hoja
        	WritableSheet sheet = w.createSheet("Catálogo", 0);
        	
        	jxl.write.Label cadena1 = new jxl.write.Label(0, 0, "Id Producto");
            sheet.addCell(cadena1);
        	jxl.write.Label cadena2 = new jxl.write.Label(1, 0, "Id Categoría");
            sheet.addCell(cadena2);
        	jxl.write.Label cadena3 = new jxl.write.Label(2, 0, "Nombre");
            sheet.addCell(cadena3);
        	jxl.write.Label cadena4 = new jxl.write.Label(3, 0, "Descripción");
            sheet.addCell(cadena4);
        	jxl.write.Label cadena5 = new jxl.write.Label(4, 0, "Precio");
            sheet.addCell(cadena5);
        	jxl.write.Label cadena6 = new jxl.write.Label(5, 0, "Stock");
            sheet.addCell(cadena6);
        	jxl.write.Label cadena7 = new jxl.write.Label(6, 0, "Impuesto");
            sheet.addCell(cadena7);
        	jxl.write.Label cadena8 = new jxl.write.Label(7, 0, "Baja");
            sheet.addCell(cadena8);
        	jxl.write.Label cadena9 = new jxl.write.Label(8, 0, "Fecha alta");
            sheet.addCell(cadena9);
        	
            jxl.write.Label cadena;
        	
        	// bucle con el contenido del catalog
            int i = 2;
        	for (Productos e:productos) {
        		
            	cadena = new jxl.write.Label(0, i, String.valueOf(e.getId()));
                sheet.addCell(cadena);
            	cadena = new jxl.write.Label(1, i, String.valueOf(e.getId_categoria()));
                sheet.addCell(cadena);
                cadena = new jxl.write.Label(2, i, e.getNombre());
                sheet.addCell(cadena);
                cadena = new jxl.write.Label(3, i, e.getDescripcion());
                sheet.addCell(cadena);
            	cadena = new jxl.write.Label(4, i, String.valueOf(e.getPrecio()));
                sheet.addCell(cadena);
            	cadena = new jxl.write.Label(5, i, String.valueOf(e.getStock()));
                sheet.addCell(cadena);
            	cadena = new jxl.write.Label(6, i, String.valueOf(e.getImpuesto()));
                sheet.addCell(cadena);
            	cadena = new jxl.write.Label(7, i, String.valueOf(e.getBaja()));
                sheet.addCell(cadena);
            	cadena = new jxl.write.Label(8, i, String.valueOf(e.getFecha_alta()));
                sheet.addCell(cadena);
                i++;
        	}
            w.write();
            w.close();
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
		
	
	public void importarProductos() {
		
		File fichero = new File("src/main/resources/ficheros/productosParaCatalogo.xls");
		
        try {
        	Workbook w = Workbook.getWorkbook(fichero);
        	
        	//Se lee la primera hoja de la excel
        	Sheet sheet = w.getSheet(0);

        	leerExcel(sheet);
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
	}
	
	public void leerExcel(Sheet sheet) {
		
		ArrayList<Productos> productos= new ArrayList<Productos>();
		Productos producto;
		
		for (int f=2; f<sheet.getRows(); f++) {
			
			producto = new Productos();
    		producto.setId_categoria(Integer.valueOf(sheet.getCell(0,f).getContents()));
    		producto.setNombre(sheet.getCell(1,f).getContents()); 
    		producto.setDescripcion(sheet.getCell(2,f).getContents()); 
    		producto.setPrecio(Double.valueOf(sheet.getCell(3,f).getContents())); 
    		producto.setStock(Integer.valueOf(sheet.getCell(4,f).getContents())); 
    		producto.setImpuesto(Float.valueOf(sheet.getCell(5,f).getContents())); 
    		producto.setImagen(sheet.getCell(6,f).getContents());
    		producto.setBaja(Boolean.parseBoolean(sheet.getCell(7,f).getContents()));
    		producto.setFecha_alta(LocalDate.parse(sheet.getCell(8,f).getContents()));

    		productos.add(producto);
    	}
		
		for (Productos e:productos) {
			productoDao.insertOrUpdateProducto(e);
		}
		
	}

}
