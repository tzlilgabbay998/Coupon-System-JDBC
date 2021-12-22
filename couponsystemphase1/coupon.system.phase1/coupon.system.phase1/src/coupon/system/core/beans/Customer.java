package coupon.system.core.beans;

import java.util.List;
import java.util.Objects;

public class Customer {

	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupones;

	public Customer() {
	}

	public Customer(int customerID, String firstName, String lastName, String email, String password) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupones() {
		return coupones;
	}

	public void setCoupones(List<Coupon> coupones) {
		this.coupones = coupones;
	}

	@Override
	public String toString() {
		return "Customers [coustomerID= " + customerID + ", firstName= " + firstName + ", lastName= " + lastName
				+ ", email= " + email + ", password= " + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(coupones, customerID, email, firstName, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		return Objects.equals(coupones, other.coupones) && customerID == other.customerID
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password);
	}

}
