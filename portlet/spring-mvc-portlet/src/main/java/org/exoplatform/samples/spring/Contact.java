package org.exoplatform.samples.spring;
public class Contact {
	private String firstName;
	private String lastName;
	private String displayName;
	private String email;
	public Contact(String firstName, String lastName, String displayName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}