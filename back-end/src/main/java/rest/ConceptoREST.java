package rest;

import ejb.ConceptoDAO;
import model.Concepto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("concepto")
@Consumes("application/json")
@Produces("application/json")

public class ConceptoREST {

    @Inject
    private ConceptoDAO conceptoDAO;

    @GET
    @Path("/listar")
    public Response listar(){
        return Response.ok(conceptoDAO.obtenerListaConceptos()).build();
    }
    
   @GET
   @Path("/recuperar/{id}")
    public Response listarid(@PathParam("id") int id){
        return Response.ok(conceptoDAO.obtenerConcepto(id)).build();
    }
    

    @POST
    @Path("/crear")
    public Response crear(Concepto p){
         conceptoDAO.guardaConcepto(p);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/actualizar")
    public Response actualizar(Concepto p){
         conceptoDAO.actualizaConcepto(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/eliminar") 
    public Response delete(Concepto p){
         conceptoDAO.eliminaConcepto(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/delete/{id}")
    public Response deleteid(@PathParam("id") int id){
        
        Concepto c = new Concepto();
        c.setIdConcepto(id);        
        conceptoDAO.eliminaConcepto(c);
        return Response.ok().build();
    }
    
}
