package org.exoplatform.samples.jsf2portlet.cdi;

import javax.inject.*;
import javax.faces.bean.*;

import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.commons.utils.CommonsUtils;

@ManagedBean
public class MailSender {
  
  private String subject, body;
  
  @Inject @Customer MailList customerMailList;
  @Inject @Partner MailList partnerMailList;
  @Inject UserBean userBean;
  
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  
  public void sendCustomers() {
    Message message = new Message();
    message.setSubject(subject);
    message.setBody(body);
    message.setFrom(userBean.getUserEmail());
    message.setTo(customerMailList.getMailList());
    
    try {
      ((MailService) CommonsUtils.getService(MailService.class)).sendMessage(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void sendPartners() {
    Message message = new Message();
    message.setSubject(subject);
    message.setBody(body);
    message.setFrom(userBean.getUserEmail());
    message.setTo(partnerMailList.getMailList());
    
    try {
      ((MailService) CommonsUtils.getService(MailService.class)).sendMessage(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
