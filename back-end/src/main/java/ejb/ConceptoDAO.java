package ejb;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Concepto;
import resources.HibernateUtil;
//import  HibernateUtil;        


//@Stateless


public class ConceptoDAO {
       
      
    private Session sesion; 
    private Transaction tx;  

    public int guardaConcepto(Concepto concepto) throws HibernateException 
    { 
        int  id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(concepto); 
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

    public void actualizaConcepto(Concepto concepto) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(concepto); 
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
    
    public void eliminaConcepto(Concepto concepto) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(concepto); 
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

    public Concepto obtenerConcepto(int  idConcepto) throws HibernateException 
    { 
        Concepto concepto = null;  
        try 
        { 
            iniciaOperacion(); 
            concepto = (Concepto) sesion.get(Concepto.class, idConcepto); 
        } finally 
        { 
            sesion.close(); 
        }  

        return concepto; 
    }  

    public List<Concepto> obtenerListaConceptos() throws HibernateException 
    { 
        List<Concepto> listaConceptos = null;  

        try 
        { 
            iniciaOperacion(); 
            listaConceptos = sesion.createQuery(" from Concepto ").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaConceptos; 
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

