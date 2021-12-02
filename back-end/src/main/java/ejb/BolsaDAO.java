package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.PuntoGenerado;
import model.PuntosUtilizado;
import model.PuntosUtilizadoDetalle;
import org.hibernate.Query;
import resources.HibernateUtil;   


public class BolsaDAO {
       
      
    private Session sesion; 
    private Transaction tx;  

    public int  guardaBolsa( Integer cliente, Integer monto) throws HibernateException 
    { 
       
        int id = 0;
        try 
        { 
            
            iniciaOperacion(); 
            String queryString = " select (:monto/r.montoEquivalencia) from  Regla  as r  where :monto  BETWEEN   r.limiteInferior  AND r.limiteSuperior  ";   
            Query query = sesion.createQuery(queryString);         
            query.setInteger("monto", monto );
            
            int  puntos  = (int) query.list().get(0);
             sesion.close(); 
             
           Date date = new Date(); 
             
            iniciaOperacion(); 
            queryString  = " select v.diasDuracion from  Vencimiento  as v where :fecha   BETWEEN v.fechaInicio and v.fechaFin ";   
            query = sesion.createQuery(queryString);         
            query.setDate("fecha", date );
            
              int  dias  = (int) query.list().get(0);
             sesion.close(); 
             
             
   
            Date venci = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); 
            calendar.add(Calendar.DAY_OF_YEAR, dias);  
            venci = calendar.getTime();
             
            
            
            
             iniciaOperacion(); 
             PuntoGenerado bolsa  = new PuntoGenerado();
             bolsa.setCliente(cliente);
             bolsa.setAsignacion(date);             
             bolsa.setVencimiento(venci);
             bolsa.setAsignado(puntos);
             bolsa.setUtilizado(0);
              bolsa.setSaldo(puntos);
             bolsa.setMontoOperacion(monto);
             
            id = (int) sesion.save(bolsa);
            tx.commit(); 
            
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  
        return id; 
    }  

    
    public   Integer recuperarPuntos(  Integer monto) throws HibernateException 
    {        
        List<Integer>  resultado  = null; 
        try 
        { 
            
            iniciaOperacion(); 
            String queryString = " select (:monto/r.montoEquivalencia) from  Regla  as r  where :monto  BETWEEN   r.limiteInferior  AND r.limiteSuperior  ";   
            Query query = sesion.createQuery(queryString);         
            query.setInteger("monto", monto );
            
             resultado = query.list();

        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  
        return resultado.get(0); 
    } 
    
        
    public  Integer   utilizarPuntos(  Integer cliente, Integer concepto) throws HibernateException 
    {        
        try
        {
                      
            //recuperar puntos requeridos del concepto
            Integer  requeridos  = null; 
            iniciaOperacion(); 
            String queryString = " select c.puntosRequeridos from  Concepto  as c where c.idConcepto  = :concepto";   
            Query query = sesion.createQuery(queryString);         
            query.setInteger("concepto", concepto );
            requeridos = (Integer) query.list().get(0);
            sesion.close(); 
            
            Long  totalpuntos  = null; 
            iniciaOperacion(); 
            queryString = " select sum(p.saldo) from  PuntoGenerado  as p  where p.cliente  = :cliente and p.saldo <> 0 ";   
            query = sesion.createQuery(queryString);         
            query.setInteger("cliente", cliente);
            totalpuntos =   (Long) query.list().get(0);
            sesion.close(); 
            
            if ( totalpuntos>=requeridos) {
                
                      Date date = new Date();
                      Calendar calendar = Calendar.getInstance();
                      calendar.setTime(date); 
                       //insertar cabecera 
                        iniciaOperacion(); 
                        PuntosUtilizado pu = new PuntosUtilizado(); 
                        pu.setCliente(cliente);
                        pu.setConcepto(concepto);
                        pu.setFecha(date);
                        pu.setPuntoUtilizado(requeridos);

                        int cabecera = (int) sesion.save(pu);
                        tx.commit(); 
                        sesion.close(); 

                        
                         //recuperar puntos generados del cliente de forma ordenada  por fecha de vencimiento
                        List<PuntoGenerado>  resultado  = null; 
                        iniciaOperacion(); 
                        queryString = " from  PuntoGenerado  as p  where p.cliente  = :cliente and p.saldo <> 0 order by p.vencimiento";   
                        query = sesion.createQuery(queryString);         
                        query.setInteger("cliente", cliente );
                        resultado = query.list();
                        sesion.close(); 
                             
                             
                        for(int i = 0; i < resultado.size()  ; i++)
                        {
                            
                            if (requeridos !=0){
                                
                                   //cargar detalles
                                    iniciaOperacion(); 
                                    PuntosUtilizadoDetalle pud = new PuntosUtilizadoDetalle(); 
                                    pud.setId_punto(cabecera);
                                    pud.setIdBolsa(resultado.get(i).getIdBolsa());
                                    pud.setUtilizado(resultado.get(i).getAsignado());
                                    requeridos= requeridos-resultado.get(i).getAsignado();
                                    sesion.save(pud);
                                    tx.commit(); 
                                    sesion.close(); 
                                    
                                    //actualizar bolsa
                                    iniciaOperacion(); 
                                    int utilizado = pud.getUtilizado();
                                    int id =  pud.getIdBolsa();
                                    
                                    queryString = " update PuntoGenerado  as p  set p.utilizado = :utilizado , p.saldo = 0   where p.idBolsa = :id";  
                                    query = sesion.createQuery(queryString);    
                                    query.setInteger("utilizado", utilizado );
                                    query.setInteger("id", id );
                                    
                                    query.executeUpdate();
                                    tx.commit(); 
                                    sesion.close(); 
                   
                             }       
                                    
                       }
            }


        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 

         } 
        return null;
    }    
    
    

    private void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    } 

    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 
}

