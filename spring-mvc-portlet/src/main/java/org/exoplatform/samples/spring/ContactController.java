package org.exoplatform.samples.spring;
import org.springframework.web.portlet.mvc.AbstractController;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.springframework.web.portlet.ModelAndView;
import java.util.Set;
public class ContactController extends AbstractController {
	private ContactService contactService;
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	@Override
	public ModelAndView handleRenderRequestInternal(RenderRequest request, RenderResponse response) {
		Set contacts = contactService.getContacts();
		ModelAndView modelAndView = new ModelAndView("contactsView", "contacts", contacts);
		return modelAndView;
	}
}