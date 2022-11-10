package curso.java.tienda.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import curso.java.tienda.dao.ConfiguracionDao;
import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.entities.Configuracion;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.entities.Usuarios;

@Component
public class FacturasPdf {
	
	@Autowired
	PedidosDao pedidoDao;
	@Autowired
	Detalles_pedidoDao detalles_pedidoDao;
	@Autowired
	ConfiguracionDao configuracionDao;
	
	public String crearFactura(int id, Model modelo) {
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		Pedidos pedido = pedidoDao.getPedidoById(id);
		ArrayList<Detalles_pedido> detalles = detalles_pedidoDao.getDetallesByIdPedido(pedido.getId());
		
		Configuracion configuracion = configuracionDao.getByClave("nombre");
		String nombre  = configuracion.getValor();
		
		Configuracion configuracion2 = configuracionDao.getByClave("direccion");
		String direccion = configuracion2.getValor();
		
		Configuracion configuracion3 = configuracionDao.getByClave("provincia");
		String provincia = configuracion3.getValor();
		
		Configuracion configuracion4 = configuracionDao.getByClave("ciudad");
		String ciudad = configuracion4.getValor();
		
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		
	    try {      
	    	//Obtenemos la instancia del archivo a utilizar
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream("src/main/resources/ficheros/factura"+pedido.getNum_factura()+".pdf"));
	    	
		    //Para insertar cabeceras/pies en todas las páginas
	    	writer.setPageEvent(new PdfHeaderFooter());
	    	
		    writer.setPageEvent(new PdfHeaderFooter());
	        
		    //Abrimos el documento para edición
		    documento.open();

		    
		    //Datos empresa
		    Paragraph paragraph = new Paragraph();
		    paragraph.add("\n");
		    paragraph.add(nombre + "\n");
		    paragraph.add(direccion +"\n");
		    paragraph.add(ciudad + "\n");
		    paragraph.add(provincia + "\n");
		    
		    paragraph.setAlignment(Element.ALIGN_RIGHT);
		    documento.add(paragraph);
		    
		    
		    //Datos usuario y pedido
		    
		    Phrase datosUser= new Phrase("Datos del cliente\n");
		    Phrase userNombreCompleto= new Phrase("Nombre: " + user.getNombre()+ " " + user.getApellido1() + "  " + user.getApellido2() + "\n");
		    Phrase userDni= new Phrase("Dni: " + user.getDni() + "\n");
		    Phrase userDireccion= new Phrase("Dirección: " +user.getDireccion() + "\n");
		    Phrase userLocalidad= new Phrase("Localidad: " + user.getLocalidad() + "\n");
		    Phrase userProvincia= new Phrase("Provincia: " + user.getProvincia() + "\n\n");
		    
		    documento.add(datosUser);
		    documento.add(userNombreCompleto);
		    documento.add(userDni);
		    documento.add(userDireccion);
		    documento.add(userLocalidad);
		    documento.add(userProvincia);
		    
		    Phrase datosPedido= new Phrase("Datos del pedido" + "\n");
		    Phrase pedidoId= new Phrase("N. pedido: " + pedido.getId() + "\n");
		    Phrase pedidoFecha= new Phrase("Fecha pedido: " + pedido.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
		    Phrase pedidoFactura= new Phrase("N. factura: " + pedido.getNum_factura() + "\n");
		    
		    documento.add(datosPedido);
		    documento.add(pedidoId);
		    documento.add(pedidoFecha);
		    documento.add(pedidoFactura);
		    
		    
		
	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
		    PdfPTable tabla = new PdfPTable(5);
		    
		    //Ancho de cada columna
	        int[] values = new int[]{40,40,40,40,40};
	        tabla.setWidths(values);
		    tabla.setWidthPercentage(new Float(100));

		    Phrase texto1= new Phrase("Id Producto");
		    Phrase texto2= new Phrase("Unidades");
		    Phrase texto3= new Phrase("Precio Unidad");
		    Phrase texto4= new Phrase("Impuesto");
		    Phrase texto5= new Phrase("Total");
			PdfPCell cabecera1 = new PdfPCell(texto1);
			PdfPCell cabecera2 = new PdfPCell(texto2);
			PdfPCell cabecera3 = new PdfPCell(texto3);
			PdfPCell cabecera4 = new PdfPCell(texto4);
			PdfPCell cabecera5 = new PdfPCell(texto5);
			
			//Propiedades concretas de formato
			cabecera1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera1.setBorderWidth(1);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			cabecera3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera3.setBorderWidth(1);
			cabecera4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera4.setBorderWidth(1);
			cabecera5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera5.setBorderWidth(1);

		    
		    tabla.addCell(cabecera1);
		    tabla.addCell(cabecera2);
		    tabla.addCell(cabecera3);
		    tabla.addCell(cabecera4);
		    tabla.addCell(cabecera5);
		    
		    for (Detalles_pedido e: detalles) {
			    Phrase t1= new Phrase(String.valueOf(e.getId()));
			    Phrase t2= new Phrase(String.valueOf(e.getUnidades()));
			    Phrase t3= new Phrase(e.getPrecio_unidad() + " €");
			    Phrase t4= new Phrase(String.valueOf(e.getImpuesto()));
			    Phrase t5= new Phrase(String.valueOf(e.getTotal()) + " €");
				PdfPCell c1 = new PdfPCell(t1);
				PdfPCell c2 = new PdfPCell(t2);
				PdfPCell c3 = new PdfPCell(t3);
				PdfPCell c4 = new PdfPCell(t4);
				PdfPCell c5 = new PdfPCell(t5);
			    tabla.addCell(c1);
			    tabla.addCell(c2);
			    tabla.addCell(c3);
			    tabla.addCell(c4);
			    tabla.addCell(c5);
		    }

		    
		    documento.add(tabla);
		    
		    Phrase pedidoTotal= new Phrase("Total del pedido: " + pedido.getTotal() + " €");
		    documento.add(pedidoTotal);
	    	
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
		    
		    //Abrir automáticamente el fichero creado
		    //http://docs.oracle.com/javase/6/docs/api/java/awt/Desktop.html
		    
		    
		   /* try {
		        File path = new File("src/main/resources/ficheros/factura"+pedido.getNum_factura()+".pdf");
		        Desktop.getDesktop().open(path);
		        //Desktop.getDesktop().browse(path);
		       // Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL factura"+pedido.getNum_factura()+".pdf");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }*/
			
	    } catch (Exception ex) {
	    	ex.getMessage();
	    	ex.printStackTrace();
	    }
	    
	    String factura = "factura"+pedido.getNum_factura()+".pdf";
	    return factura;
	}
		
}
