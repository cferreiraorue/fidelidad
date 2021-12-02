
package model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name ="puntos_utilizados_detalles")

public class PuntosUtilizadoDetalle implements Serializable {
    
    @Id
    @Column(name = "pude_id")
    @Basic(optional = false )
    @GeneratedValue(generator = "pudeSec",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "pudeSec", sequenceName = "pude_id_seq", allocationSize = 0)
    private Integer id_detalle;

 

    @Column(name = "punt_id")
    private  Integer  id_punto;
            
    @Column(name = "punt_puntos_utilizado")        
    private  Integer  utilizado;
    
    
    @Column(name = "puge_id")
    private Integer  idBolsa;

    public Integer getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(Integer id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Integer getId_punto() {
        return id_punto;
    }

    public void setId_punto(Integer id_punto) {
        this.id_punto = id_punto;
    }

    public Integer getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Integer utilizado) {
        this.utilizado = utilizado;
    }

    public Integer getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(Integer idBolsa) {
        this.idBolsa = idBolsa;
    }
    
    
}
