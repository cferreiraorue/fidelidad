
package model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name ="puntos_utilizados")

public class PuntosUtilizado implements Serializable {
    
    @Id
    @Column(name = "punt_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "puntSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "puntSec", sequenceName = "punt_id_seq", allocationSize = 0)
    private Integer id_punto;

 
     @Column(name = "clie_id")
    @Basic(optional = false )
    private  Integer  cliente;
    
    @Column(name = "punt_puntos_utilizado")
    @Basic(optional = false )
    private Integer  puntoUtilizado;

    
    @Column(name = "punt_fecha_utilizacion")
    @Basic(optional = false )
    private Date  fecha;
    
    
      @Column(name = "conc_id")
     @Basic(optional = false )
     private  Integer  concepto;
     
         public Integer getId_punto() {
        return id_punto;
    }

    public void setId_punto(Integer id_punto) {
        this.id_punto = id_punto;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getPuntoUtilizado() {
        return puntoUtilizado;
    }

    public void setPuntoUtilizado(Integer puntoUtilizado) {
        this.puntoUtilizado = puntoUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getConcepto() {
        return concepto;
    }

    public void setConcepto(Integer concepto) {
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return "PuntosUtilizado{" + "id_punto=" + id_punto + ", cliente=" + cliente + ", puntoUtilizado=" + puntoUtilizado + ", fecha=" + fecha + ", concepto=" + concepto + '}';
    }
    
      
}
