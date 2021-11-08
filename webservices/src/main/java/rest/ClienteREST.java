package rest;

import ejb.ClienteDAO;
import org.omg.Security.Public;
import model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("cliente")
@Consumes("application/json")
@Produces("application/json")

public class ClienteREST {

    @Inject
    private ClienteDAO clienteDAO;

    @GET
    @Path("/listar")
    public Response listar(){
        return Response.ok(clienteDAO.obtenerListaClientes()).build();
    }
    
   @GET
   @Path("/recuperar/{id}")
    public Response listarid(@PathParam("id") int id){
        return Response.ok(clienteDAO.obtenerCliente(id)).build();
    }
    

    @POST
    @Path("/crear")
    public Response crear(Cliente p){
         clienteDAO.guardaCliente(p);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/actualizar")
    public Response actualizar(Cliente p){
         clienteDAO.actualizaCliente(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/eliminar") 
    public Response delete(Cliente p){
         clienteDAO.eliminaCliente(p);
        return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/delete/{id}")
    public Response deleteid(@PathParam("id") int id){
        
        Cliente c = new Cliente();
        c.setIdCliente(id);        
        clienteDAO.eliminaCliente(c);
        return Response.ok().build();
    }
    
}
