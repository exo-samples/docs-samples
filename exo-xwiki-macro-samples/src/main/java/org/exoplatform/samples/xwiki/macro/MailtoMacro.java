/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.samples.xwiki.macro;

import java.util.Arrays;
import java.util.List;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.block.LinkBlock;
import org.xwiki.rendering.block.RawBlock;
import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.listener.reference.ResourceType;
import org.xwiki.rendering.macro.AbstractMacro;
import org.xwiki.rendering.macro.MacroExecutionException;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.transformation.MacroTransformationContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 12, 2015  
 */

@Component("mailto")
public class MailtoMacro extends AbstractMacro<MailtoMacroParams> {

  public MailtoMacro() {
    super("mailto", "Add an email contact inline.", MailtoMacroParams.class);
  }

  public boolean supportsInlineMode() {
    return true;
  }

  public List<Block> execute(MailtoMacroParams parameters,
                             String content,
                             MacroTransformationContext context) throws MacroExecutionException {
    IdentityManager identityManager = (IdentityManager) PortalContainer.getInstance().getComponentInstanceOfType(IdentityManager.class);
    try {
      // Get user info.
      Identity identity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, parameters.getUsername(), false);
      Profile profile = identity.getProfile();
      String displayName = profile.getFullName(); //to be displayed.
      String email = profile.getEmail(); //to be linked.
      
      // Build the blocks.
      RawBlock rawblock = new RawBlock(displayName, Syntax.XHTML_1_0);
      LinkBlock linkblock = new LinkBlock(Arrays.<Block>asList(rawblock), new ResourceReference(email, ResourceType.MAILTO), true);
      return Arrays.<Block>asList(linkblock);
    } catch (Exception e) {
      
      // In case the parameter is not a valid user id.
      RawBlock rawblock = new RawBlock(parameters.getUsername()+"(?)", Syntax.XHTML_1_0);
      return Arrays.<Block>asList(rawblock);
    }
  }
}
