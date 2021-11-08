package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name ="vencimientos")

public class Vencimiento implements Serializable { 
    
    @Id
    @Column(name = "venc_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "vencSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vencSec", sequenceName = "venc_id_seq", allocationSize = 0)
    private Integer idVencimiento;

 
    @Column(name = "venc_fecha_inicio_validez")
    @Basic(optional = false )
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private  Date fechaInicio;
    
    @Column(name = "venc_fecha_fin_validez")
    @Basic(optional = false )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private  Date fechaFin;
        
     @Column(name = "venc_dias_duracion")
    @Basic(optional = false )
    private  Integer diasDuracion;

    public Integer getIdVencimiento() {
        return idVencimiento;
    }

    public void setIdVencimiento(Integer idVencimiento) {
        this.idVencimiento = idVencimiento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDiasDuracion() {
        return diasDuracion;
    }

    public void setDiasDuracion(Integer diasDuracion) {
        this.diasDuracion = diasDuracion;
    }

    @Override
    public String toString() {
        return "Vencimiento{" + "idVencimiento=" + idVencimiento + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", diasDuracion=" + diasDuracion + '}';
    }
   
}
