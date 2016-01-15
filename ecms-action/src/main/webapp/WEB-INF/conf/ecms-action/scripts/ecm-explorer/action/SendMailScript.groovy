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
package org.exoplatform.samples.ecms.action;

import java.util.Map;

import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.Item;

import org.exoplatform.services.log.Log;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.cms.scripts.CmsScript;

import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.commons.utils.CommonsUtils;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 30, 2015  
 */
public class SampleScript implements CmsScript {
  
  private static final Log LOG  = ExoLogger.getLogger(SampleScript.class);  

  public void execute(Object context) throws Exception {
    Map values = (Map) context;
    String to = (String) values.get("exo:to");
    String subject = (String) values.get("exo:subject");
    String srcWorkspace = (String) values.get("srcWorkspace");
    String srcPath = (String) values.get("srcPath");
    
    if (to == null) {
      LOG.warn("A SendMailAction at " + srcWorkspace + ":" + srcPath + " is canceled because the TO address is not determined");
      return;
    }
    
    Message message = new Message();
    message.setTo(to);
    message.setSubject(subject);
    message.setBody("There is content update in " + srcWorkspace + ":" + srcPath);
    
    try {
      ((MailService) CommonsUtils.getService(MailService.class)).sendMessage(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setParams(String[] arg0) {}
  
}
