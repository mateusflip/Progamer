package br.com.fiap.utils;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.Usuario;

public class AuthorizationListener implements PhaseListener{
	
	private static final Long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String page = context.getViewRoot().getViewId();
		if(page.equals("/login.xhtml")) return;
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		
		if(usuario == null) {
			NavigationHandler navigation = context.getApplication().getNavigationHandler();
			navigation.handleNavigation(context, "", "login?faces-redirect=true");
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.RESTORE_VIEW;
	}

}
