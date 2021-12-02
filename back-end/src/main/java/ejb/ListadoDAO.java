package ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Cliente;
import model.PuntoGenerado;
import org.apache.tools.ant.util.DateUtils;
import org.hibernate.Query;
import resources.HibernateUtil;
 

public class ListadoDAO {
       
      
    private Session sesion; 
    private Transaction tx;  


    public  List<Cliente> obtenerClienteNombre(String  criterio) throws HibernateException 
    { 
        List<Cliente> listaClientes = null;        
         
        try 
        { 
            iniciaOperacion(); 
            String queryString = "from Cliente as c where c.nombre like ?";
           Query query = sesion.createQuery(queryString);
            query.setString(0, "%" + criterio + "%");
           
           listaClientes = query.list();
                                   
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClientes; 
    }  
    
    public  List<Cliente> obtenerClienteApellido(String  criterio) throws HibernateException 
    { 
        List<Cliente> listaClientes = null;        
         
        try 
        { 
            iniciaOperacion(); 
            String queryString = "from Cliente as c where c.apellido like ?";
           Query query = sesion.createQuery(queryString);
            query.setString(0, "%" + criterio + "%");
           
           listaClientes = query.list();
                                   
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClientes; 
    } 
    
    public  List<Cliente> obtenerClienteNacimiento(String  criterio) throws HibernateException, ParseException 
    { 
        List<Cliente> listaClientes = null;        
         
        try 
        {                   
           iniciaOperacion(); 

           String queryString = "from Cliente as c where  cast (c.fechaNacimiento as date) = :fecha";
           Query query = sesion.createQuery(queryString);
         
           Date fecha =new SimpleDateFormat("dd-MM-yyyy").parse(criterio);  
           query.setTimestamp("fecha", fecha);
           
           listaClientes = query.list();
                                   
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClientes; 
    }  
    
     public  List<PuntoGenerado> obtenerBolsaPuntos(Integer  criterio) throws HibernateException 
    { 
        List<PuntoGenerado> listaPuntos = null;        
        try 
        {                   
           iniciaOperacion(); 

           String queryString = "from PuntoGenerado as c where c.cliente =   :criterio ";
           Query query = sesion.createQuery(queryString);
           query.setInteger("criterio", criterio);           
           
           listaPuntos = query.list();
                                   
        } finally 
        { 
            sesion.close(); 
        }  
        return listaPuntos; 
    }
     
    public  List<PuntoGenerado> obtenerBolsaPuntos(Integer  criterio1 , Integer criterio2) throws HibernateException 
    { 
        List<PuntoGenerado> listaPuntos = null;        
         
        try 
        {                   
           iniciaOperacion(); 
           
            String queryString = "from PuntoGenerado as c where c.asignado BETWEEN   :criterio1  AND  :criterio2";
           Query query = sesion.createQuery(queryString);
           query.setInteger("criterio1", criterio1);
           query.setInteger("criterio2", criterio2);
           
           listaPuntos = query.list();
                                   
        } finally 
        { 
            sesion.close(); 
        }  

        return listaPuntos; 
    }
    
     public  List<Cliente> obtenerClienteVencimientos(Integer  dias) throws HibernateException, ParseException 
    { 
        List<Cliente> listaClientes = null;        
         
        Date date = new Date();
        Date fecha ;         
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        fecha = calendar.getTime();
     
                
        try 
        {                   
           iniciaOperacion(); 

           String queryString = "from PuntoGenerado as c where  cast (c.vencimiento as date)  = :fecha";
           Query query = sesion.createQuery(queryString);
   
          SimpleDateFormat formatter = new SimpleDateFormat(      "dd/MM/yyyy");
          formatter.parse(formatter.format(fecha));
  
           query.setTimestamp("fecha", fecha);
           
           listaClientes = query.list();
                                   
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

