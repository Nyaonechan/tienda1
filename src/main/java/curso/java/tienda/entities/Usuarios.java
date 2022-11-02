package curso.java.tienda.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name="usuarios")
public class Usuarios {
	
    @Override
	public String toString() {
		return "Usuarios [id=" + id + ", id_rol=" + id_rol + ", email=" + email + ", clave=" + clave + ", nombre="
				+ nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", direccion=" + direccion
				+ ", provincia=" + provincia + ", localidad=" + localidad + ", telefono=" + telefono + ", dni=" + dni
				+ ", imagen=" + imagen + ", baja=" + baja + ", fecha_alta=" + fecha_alta + "]";
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private int id_rol;
    
    @NotEmpty
    @NotNull
    @Email
    private String email;

    private String clave;
    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String nombre;
    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String apellido1;
    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String apellido2;
    
    private String direccion;
    
    private String provincia;
    
    private String localidad;
    @Min(600000000)
    @Max(799999999)
    private String telefono;
    @Size(min = 9, max = 9)
    private String dni;
    
    private String imagen;
    
    private boolean baja;
    
    private LocalDate fecha_alta;
    
    public Usuarios() {
    	
    }

	public Usuarios(int id, int id_rol, String email, String clave, String nombre, String apellido1, String apellido2,
			String direccion, String provincia, String localidad, String telefono, String dni, boolean baja, LocalDate fecha_alta) {
		this.id = id;
		this.id_rol = id_rol;
		this.email = email;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.provincia = provincia;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
		this.baja = baja;
		this.fecha_alta = fecha_alta;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

    
    

}
