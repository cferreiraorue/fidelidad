package ejb;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Regla;
import resources.HibernateUtil;
//import  HibernateUtil;        


//@Stateless


public class ReglaDAO {
       
      
    private Session sesion; 
    private Transaction tx;  

    public int guardaRegla(Regla regla) throws HibernateException 
    { 
        int  id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(regla); 
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

    public void actualizaRegla(Regla regla) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(regla); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  
    
    public void eliminaRegla(Regla regla) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(regla); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 

        } finally 
        { 
            sesion.close(); 
        } 
    }

    public Regla obtenerRegla(int  idRegla) throws HibernateException 
    { 
        Regla regla = null;  
        try 
        { 
            iniciaOperacion(); 
            regla = (Regla) sesion.get(Regla.class, idRegla); 
        } finally 
        { 
            sesion.close(); 
        }  

        return regla; 
    }  

    public List<Regla> obtenerListaReglas() throws HibernateException 
    { 
        List<Regla> listaReglas = null;  

        try 
        { 
            iniciaOperacion(); 
            listaReglas = sesion.createQuery(" from Regla ").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaReglas; 
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

