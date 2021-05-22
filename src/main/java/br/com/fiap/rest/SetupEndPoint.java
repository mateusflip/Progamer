package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;

@Path("/setups")
public class SetupEndPoint {
	
	SetupDAO dao = new SetupDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Setup> index() {
		return dao.getAll();
	}

}
