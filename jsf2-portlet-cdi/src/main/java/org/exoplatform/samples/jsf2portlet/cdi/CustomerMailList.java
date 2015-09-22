package org.exoplatform.samples.jsf2portlet.cdi;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
@Customer
public class CustomerMailList implements MailList{
  
  public String getMailList() {
    return "user1@example.com, user2@example.com";
  }
}
