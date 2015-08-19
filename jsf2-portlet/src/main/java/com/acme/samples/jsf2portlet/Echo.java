package com.acme.samples.jsf2portlet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
@ManagedBean(name = "echo")
@SessionScoped
public class Echo {
	String str = "hello";
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public void reset(ActionEvent ae) {
		str = "";
	}
}