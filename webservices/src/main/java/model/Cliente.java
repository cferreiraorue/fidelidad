package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name ="clientes")

public class Cliente implements Serializable { 
    
    @Id
    @Column(name = "clie_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "clienteSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clienteSec", sequenceName = "clie_id_seq", allocationSize = 0)
    private Integer idCliente;

 
    @Column(name = "clie_nombre", length = 60)
    @Basic(optional = false )
    private  String nombre;
    
    @Column(name = "clie_apellido", length = 60)
    @Basic(optional = false )
    private String apellido;
    
     @Column(name = "clie_tipo_documento", length = 4)
    @Basic(optional = false )
    private String tipoDocumento;
    
    @Column(name = "clie_nro_documento", length = 60)
    @Basic(optional = false )
    private String nroDocumento;

    @Column(name = "clie_nacionalidad", length = 120)
    @Basic(optional = false )
    private String nacionalidad;

    @Column(name = "clie_email", length = 60)
    @Basic(optional = false )
    private String email;

    @Column(name = "clie_telefono", length = 20)
    @Basic(optional = false )
    private String telefono;
        
    
    @Column(name = "clie_fecha_nacimiento")
    @Basic(optional = false )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date  fechaNacimiento;


        
    public Cliente() {

    }
   

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer id_cliente) {
        this.idCliente = id_cliente;
    }

 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento + ", nacionalidad=" + nacionalidad + ", email=" + email + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
   
}
