package controllers;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import beans.Login;



@ManagedBean
@ViewScoped
public class LoginController implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String onSubmit(Login login) {
		//Forwards to view along with the user bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LoginFail.xhtml", login);
		return "LoginFail.xhtml";


	}
	
}