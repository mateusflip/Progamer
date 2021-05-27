package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	
	public String login() {
		System.out.println("Realizando login");
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		boolean exist = new UsuarioDAO().exist(usuario);
		
		if(exist) {
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "index?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário ou senha incorretos", ""));
			return "login?faces-redirect=true";
			
	}
	
	public void save() {
		new UsuarioDAO().save(this.usuario);
		
		System.out.println("Salvando" + this.usuario);
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Usuario cadastrado com sucesso"));
	}
	
	public List<Usuario> getUsuarios(){
		return new UsuarioDAO().getAll();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
