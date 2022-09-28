package org.exoplatform.samples.listener;

import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationRegistry;
import org.exoplatform.services.security.ConversationState;

// The class LoginEventListener should extend class Listener
// The class Listener takes two parametrized types that will be passed to the event object and be used respectively as :
// 1- Event source object
// 2- Event data object
public class LoginEventListener extends Listener<ConversationRegistry, ConversationState> {

  // Logger object for this Listener
  private static final Log LOG = ExoLogger.getLogger(LoginEventListener.class);

  @Override
  public void onEvent(Event<ConversationRegistry, ConversationState> event) throws Exception {
    // Retrieve the source for this event
    ConversationRegistry source = event.getSource();
    // Retrieve the data of the event
    ConversationState data = event.getData();
    LOG.info("An event was received from {} : The user {} was logged in", source.getClass(), data.getIdentity().getUserId());
  }
}
