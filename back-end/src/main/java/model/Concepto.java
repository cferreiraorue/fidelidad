package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name ="conceptos")

public class Concepto implements Serializable { 
    
    @Id
    @Column(name = "conc_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "concSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "concSec", sequenceName = "conc_id_seq", allocationSize = 0)
    private Integer idConcepto;

 
    @Column(name = "conc_descripcion", length = 60)
    @Basic(optional = false )
    private  String descripcion;
    
    @Column(name = "conc_puntos_requeridos")
    @Basic(optional = false )
    private Integer  puntosRequeridos;

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getpuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setpuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    @Override
    public String toString() {
        return "Concepto{" + "idConcepto=" + idConcepto + ", concDescripcion=" + descripcion + ", concPuntosRequeridos=" + puntosRequeridos + '}';
    }
    
}
