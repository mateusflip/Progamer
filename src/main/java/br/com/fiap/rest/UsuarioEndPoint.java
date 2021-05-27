package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEndPoint {

	UsuarioDAO dao = new UsuarioDAO();
	
	//Mostrar todos
	@GET
	public List<Usuario> index(){
		return dao.getAll();
	}
	
	//Criar
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Usuario usuario) {
		if(usuario == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}try {
			dao.save(usuario);
		}catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.CREATED).entity(usuario).build();
	}
	
	//Procurar pelo ID
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") int id) {
		Usuario usuario = dao.findById(id);
		
		if(usuario == null) {
			return Response.status(Response.Status.NOT_FOUND).build();	
		}
		return Response.status(Response.Status.OK).entity(usuario).build();
	}
	
	//Atualizar um usuário
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Usuario usuario) {
		usuario.setId(id);
		dao.update(usuario);
		return Response.status(Response.Status.OK).entity(usuario).build();
	}
	
	
}
