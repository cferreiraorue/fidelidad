package ejb;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Cliente;
import resources.HibernateUtil;
//import  HibernateUtil;        


//@Stateless


public class ClienteDAO {
       
      
    private Session sesion; 
    private Transaction tx;  

    public int guardaCliente(Cliente cliente) throws HibernateException 
    { 
        int  id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(cliente); 
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

    public void actualizaCliente(Cliente cliente) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(cliente); 
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
    
    public void eliminaCliente(Cliente cliente) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(cliente); 
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

    public Cliente obtenerCliente(int  idCliente) throws HibernateException 
    { 
        Cliente cliente = null;  
        try 
        { 
            iniciaOperacion(); 
            cliente = (Cliente) sesion.get(Cliente.class, idCliente); 
        } finally 
        { 
            sesion.close(); 
        }  

        return cliente; 
    }  

    public List<Cliente> obtenerListaClientes() throws HibernateException 
    { 
        List<Cliente> listaClientes = null;  

        try 
        { 
            iniciaOperacion(); 
            listaClientes = sesion.createQuery(" from Cliente ").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClientes; 
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

