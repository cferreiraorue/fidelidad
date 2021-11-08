package rest;

import ejb.BolsaDAO;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("bolsa")
@Consumes("application/json")
@Produces("application/json")

public class BolsaREST {

    @Inject
    private BolsaDAO bolsaDAO;

    @POST
    @Path("/cargarpuntos/{cliente}/{monto}")
    public Response crear(@PathParam("cliente") Integer cliente, @PathParam("monto") Integer monto){
         return Response.ok( bolsaDAO.guardaBolsa(cliente,monto)).build();
     
    }
    
    @GET
    @Path("/consultarpuntos/{monto}")
    public Response recuperar(@PathParam("monto") Integer monto){
         return Response.ok( bolsaDAO.recuperarPuntos(monto)).build();
    }
    
    
    @GET
    @Path("/utilizarpuntos/{cliente}/{concepto}")
    public Response utilizar(@PathParam("cliente") Integer cliente, @PathParam("concepto") Integer concepto){
         return Response.ok( bolsaDAO.utilizarPuntos(cliente,concepto)).build();
    }
    
   
    
}
