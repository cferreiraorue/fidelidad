package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name ="reglas")

public class Regla implements Serializable { 
    
    @Id
    @Column(name = "regl_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "reglSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reglSec", sequenceName = "regl_id_seq", allocationSize = 0)
    private Integer idRegla;

 
    @Column(name = "regl_limite_inferior")
    @Basic(optional = true )
    private  Integer limiteInferior;
    
    @Column(name = "regl_limite_superior")
    @Basic(optional = true )
    private Integer  limiteSuperior;

    @Column(name = "regl_monto_equivalencia")
    @Basic(optional = false )
    private Integer  montoEquivalencia;
    
    @Column(name = "regl_puntos")
    @Basic(optional = false )
    private Integer  puntos;

    public Integer getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(Integer idRegla) {
        this.idRegla = idRegla;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Regla{" + "idRegla=" + idRegla + ", limiteInferior=" + limiteInferior + ", limiteSuperior=" + limiteSuperior + ", montoEquivalencia=" + montoEquivalencia + ", puntos=" + puntos + '}';
    }
 
    
}
