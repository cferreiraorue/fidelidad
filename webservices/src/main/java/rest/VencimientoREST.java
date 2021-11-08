package rest;

import ejb.VencimientoDAO;
import model.Vencimiento;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("vencimiento")
@Consumes("application/json")
@Produces("application/json")

public class VencimientoREST {

    @Inject
    private VencimientoDAO vencimientoDAO;

    @GET
    @Path("/listar")
    public Response listar(){
        return Response.ok(vencimientoDAO.obtenerListaVencimientos()).build();
    }
    
   @GET
   @Path("/recuperar/{id}")
    public Response listarid(@PathParam("id") int id){
        return Response.ok(vencimientoDAO.obtenerVencimiento(id)).build();
    }
    

    @POST
    @Path("/crear")
    public Response crear(Vencimiento p){
         vencimientoDAO.guardaVencimiento(p);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/actualizar")
    public Response actualizar(Vencimiento p){
         vencimientoDAO.actualizaVencimiento(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/eliminar") 
    public Response delete(Vencimiento p){
         vencimientoDAO.eliminaVencimiento(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/delete/{id}")
    public Response deleteid(@PathParam("id") int id){
        
        Vencimiento c = new Vencimiento();
        c.setIdVencimiento(id);        
        vencimientoDAO.eliminaVencimiento(c);
        return Response.ok().build();
    }
    
}
