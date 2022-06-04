package controllers;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import beans.Login;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LoginController implements Serializable{



	public String onSubmit(Login login) {
		//Forwards to view along with the user bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("login", login);
		return null;


	}
	
}