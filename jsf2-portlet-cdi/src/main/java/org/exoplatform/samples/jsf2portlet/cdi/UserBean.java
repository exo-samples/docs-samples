package org.exoplatform.samples.jsf2portlet.cdi;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.identity.model.*;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;

@ManagedBean
@SessionScoped
public class UserBean {
  
  private String userEmail;
  
  public UserBean() {
    IdentityManager identityManager = (IdentityManager) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(IdentityManager.class);
    String currentUserId = ConversationState.getCurrent().getIdentity().getUserId();
    Identity currentIdentity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, currentUserId, false);
    Profile profile = currentIdentity.getProfile();
    userEmail = profile.getEmail();
  }
  
  public String getUserEmail() {
    return userEmail;
  }
}
