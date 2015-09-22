package org.exoplatform.samples.jsf2portlet.cdi;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
@Partner
public class PartnerMailList implements MailList{
  
  public String getMailList() {
    return "user3@example.com, user4@example.com";
  }
}
