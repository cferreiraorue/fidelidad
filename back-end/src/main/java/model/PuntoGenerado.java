package model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name ="puntos_generados")

public class PuntoGenerado {
    @Id
    @Column(name = "puge_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "puntosSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "puntosSec", sequenceName = "puge_id_seq", allocationSize = 0)
    private Integer idBolsa;

    @Column(name = "clie_id")
    @Basic(optional = false )
    private Integer cliente;

    @Column(name = "puge_fecha_asignacion")
    @Basic(optional = false )
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private  Date  asignacion;

    @Column(name = "puge_fecha_vencimiento")
    @Basic(optional = false )
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private  Date  vencimiento;
     
    @Column(name = "puge_puntos_asignado")
    @Basic(optional = false )
    private Integer asignado;
    
    
    @Column(name = "puge_puntos_utilizado")
    @Basic(optional = false )
    private Integer utilizado;
        
     @Column(name = "puge_saldo_puntos")
    @Basic(optional = false )
    private Integer saldo;
    
    @Column(name = "puge_monto_operacion")
    @Basic(optional = false )
    private Integer montoOperacion;
    
    
    public Integer getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(Integer idBolsa) {
        this.idBolsa = idBolsa;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Integer getAsignado() {
        return asignado;
    }

    public void setAsignado(Integer asignado) {
        this.asignado = asignado;
    }

    public Integer getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Integer utilizado) {
        this.utilizado = utilizado;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    @Override
    public String toString() {
        return "PuntoGenerado{" + "idBolsa=" + idBolsa + ", cliente=" + cliente + ", asignacion=" + asignacion + ", vencimiento=" + vencimiento + ", asignado=" + asignado + ", utilizado=" + utilizado + ", saldo=" + saldo + ", montoOperacion=" + montoOperacion + '}';
    }   
   
}
