package coupon.system.core.beans;

import java.util.List;
import java.util.Objects;

public class Company {

	private int companyID;
	private String name;
	private String email;
	private String password;
	private List<Coupon> coupons;

	public Company() {
	}

	public Company(int companyID, String name, String email, String password) {
		super();
		this.companyID = companyID;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyID, coupons, email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		return companyID == other.companyID && Objects.equals(coupons, other.coupons)
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Company [companyID= " + companyID + ", name= " + name + ", email= " + email + ", password= " + password
				+ "]";
	}
}
