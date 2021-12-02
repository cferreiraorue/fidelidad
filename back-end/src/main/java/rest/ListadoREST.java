package rest;

import ejb.ClienteDAO;
import ejb.ListadoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("listados")
@Consumes("application/json")
@Produces("application/json")

public class ListadoREST {

    @Inject
    private ListadoDAO listadoDAO;
   
   @GET
   @Path("/recuperar/nombre/{criterio}")
        public Response listarnombre(@PathParam("criterio") String criterio){
        return Response.ok(listadoDAO.obtenerClienteNombre(criterio)).build();
    }
    
    @GET
   @Path("/recuperar/apellido/{criterio}")
    public Response listarapellido(@PathParam("criterio") String criterio){
        return Response.ok(listadoDAO.obtenerClienteApellido(criterio)).build();
    }
    
   @GET
   @Path("/recuperar/nacimiento/{criterio}")
    public Response listarnacimiento(@PathParam("criterio") String criterio) throws ParseException{
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       //Date dateObj = sdf.parse(criterio);
        return Response.ok(listadoDAO.obtenerClienteNacimiento(criterio)).build();
    }
    
   @GET
   @Path("/bolsa/cliente/{criterio}")
    public Response obtenerBolsaPuntos(@PathParam("criterio") Integer criterio) throws ParseException{
        return Response.ok(listadoDAO.obtenerBolsaPuntos(criterio)).build();
    }
    
   @GET
   @Path("/bolsa/puntos/{criterio1}/{criterio2}")
    public Response obtenerBolsaPuntos(@PathParam("criterio1") Integer criterio1, @PathParam("criterio2") Integer criterio2) throws ParseException{        
        return Response.ok(listadoDAO.obtenerBolsaPuntos(criterio1,criterio2)).build();
    }
    
   @GET
   @Path("/bolsa/vencimientos/{criterio}")
    public Response obtenerVencimientos(@PathParam("criterio") Integer criterio) throws ParseException{        
        return Response.ok(listadoDAO.obtenerClienteVencimientos(criterio)).build();
    } 
    
    
}
