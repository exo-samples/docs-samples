package org.exoplatform.samples.spring;
import java.util.Set;
import java.util.LinkedHashSet;
import org.exoplatform.samples.spring.Contact;
public class ContactServiceImpl implements ContactService {
	private static Set contactList = new LinkedHashSet();
	public Set getContacts() {
		if (contactList.size() == 0) {
			initContacts();
		}
		return contactList;
	}
	public void initContacts() {
		contactList.add(new Contact("John", "Smith", "John Smith", "john.smith@exo.com"));
		contactList.add(new Contact("Mary", "Williams", "Mary Williams", "mary.williams@exo.com"));
		contactList.add(new Contact("Jack", "Miller", "Jack Miller", "jack.miller@exo.com"));
		contactList.add(new Contact("James", "Davis", "James Davis", "james.davis@exo.com"));
	}
}