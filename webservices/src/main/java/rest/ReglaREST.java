package rest;

import ejb.ReglaDAO;
import org.omg.Security.Public;
import model.Regla;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("regla")
@Consumes("application/json")
@Produces("application/json")

public class ReglaREST {

    @Inject
    private ReglaDAO reglaDAO;

    @GET
    @Path("/listar")
    public Response listar(){
        return Response.ok(reglaDAO.obtenerListaReglas()).build();
    }
    
   @GET
   @Path("/recuperar/{id}")
    public Response listarid(@PathParam("id") int id){
        return Response.ok(reglaDAO.obtenerRegla(id)).build();
    }
    

    @POST
    @Path("/crear")
    public Response crear(Regla p){
         reglaDAO.guardaRegla(p);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/actualizar")
    public Response actualizar(Regla p){
         reglaDAO.actualizaRegla(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/eliminar") 
    public Response delete(Regla p){
         reglaDAO.eliminaRegla(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/delete/{id}")
    public Response deleteid(@PathParam("id") int id){
        
        Regla c = new Regla();
        c.setIdRegla(id);        
        reglaDAO.eliminaRegla(c);
        return Response.ok().build();
    }
    
}
