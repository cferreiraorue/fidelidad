package ejb;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Vencimiento;
import resources.HibernateUtil;



public class VencimientoDAO {
       
      
    private Session sesion; 
    private Transaction tx;  

    public int guardaVencimiento(Vencimiento vencimiento) throws HibernateException 
    { 
        int  id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(vencimiento); 
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

    public void actualizaVencimiento(Vencimiento vencimiento) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(vencimiento); 
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
    
    public void eliminaVencimiento(Vencimiento vencimiento) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(vencimiento); 
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

    public Vencimiento obtenerVencimiento(int  idVencimiento) throws HibernateException 
    { 
        Vencimiento vencimiento = null;  
        try 
        { 
            iniciaOperacion(); 
            vencimiento = (Vencimiento) sesion.get(Vencimiento.class, idVencimiento); 
        } finally 
        { 
            sesion.close(); 
        }  

        return vencimiento; 
    }  

    public List<Vencimiento> obtenerListaVencimientos() throws HibernateException 
    { 
        List<Vencimiento> listaVencimientos = null;  

        try 
        { 
            iniciaOperacion(); 
            listaVencimientos = sesion.createQuery(" from Vencimiento ").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaVencimientos; 
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

