package ec.edu.ups.ppw63.demo63.services;

import java.util.List;

import ec.edu.ups.ppw63.demo63.business.GestionUniversidad;
import ec.edu.ups.ppw63.demo63.model.Universidades;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("universidades")
public class UniversidadServices {
	@Inject
	private GestionUniversidad gUniversidad;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Universidades universidad) {
		try{
			gUniversidad.guardar(universidad);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED).entity(error).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Universidades universidad) {
		try{
			gUniversidad.actualizar(universidad);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@QueryParam("codigo") int codigo) {
	    try {
	    	gUniversidad.borrar(codigo);
	        ErrorMessage error = new ErrorMessage(1, "OK");
	        return Response.ok(error).build();
	    } catch (Exception e) {
	        ErrorMessage error = new ErrorMessage(99, e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	    }
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List<Universidades> universidades = gUniversidad.getClientes();
		if(universidades.size()>0)
			return Response.ok(universidades).build();
		ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	}
}
