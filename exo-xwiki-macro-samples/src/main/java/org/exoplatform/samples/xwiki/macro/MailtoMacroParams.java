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

import org.xwiki.properties.annotation.PropertyDescription;
import org.xwiki.properties.annotation.PropertyMandatory;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 12, 2015  
 */
public class MailtoMacroParams {
  
  /**
   * The MailtoMeMacro expects a user name. If the user name is not valid, it appends a question sign to the user name.
   */
  
  private String username;

  public String getUsername() {
    return username;
  }

  @PropertyDescription("Somebody's ID. His email will be added inline.")
  @PropertyMandatory
  public void setUsername(String username) {
    this.username = username;
  }
  
  
}
