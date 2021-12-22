package coupon.system.core.facade;

import java.util.List;

import coupon.system.core.beans.Company;
import coupon.system.core.beans.Customer;
import coupon.system.core.exceptions.CouponSystemException;

public class AdminFacade extends ClientFacade {

	private final String email = "admin@admin.com";
	private final String password = "admin";

	public AdminFacade() {
	}

	@Override
	public boolean login(String email, String password) {
		if (email.equals(this.email) && password.equals(this.password)) {
			return true;
		} else {
			System.out.println("one or more of the details are incorrect");
			return false;
		}
	}

	public void addCompany(Company company) throws CouponSystemException {
		try {
			if (companyDAO.doesCompanyExist(company.getEmail(), company.getName())) {
				throw new CouponSystemException("company is already exist! ");
			} else {
				companyDAO.addCompany(company);
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("could'nt add the company.", e);
		}

	}

	public Company UpdateCompany(Company company) throws CouponSystemException {
		try {
			if (companyDAO.isCompanyExist(company.getCompanyID())) {
				companyDAO.UpdateCompany(company);
				return company;
			} else {
				throw new CouponSystemException("could'nt find this company ID to update");
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("could'nt update company", e);
		}
	}

	public void deleteCompany(int companyID) throws CouponSystemException {
		try {
			companyDAO.deleteCompany(companyID);
		} catch (CouponSystemException e) {
			throw new CouponSystemException("could'nt delete company");
		}
		try {
			companyDAO.deleteAllCompanyCoupons(companyID);
		} catch (CouponSystemException e) {
			throw new CouponSystemException("could'nt delete company coupons");
		}
		try {
			companyDAO.deleteCompanyCouponsHistory(companyID);
		} catch (CouponSystemException e) {
			throw new CouponSystemException("could'nt delete purchase history of company's coupons");
		}
	}

	public List<Company> getAllCompanies() throws CouponSystemException {
		return companyDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) throws CouponSystemException {
		System.out.println(companyDAO.getOneCompany(companyID));
		return companyDAO.getOneCompany(companyID);
	}

	public Customer addCustomer(Customer customer) throws CouponSystemException {
		try {
			if (customerDAO.isCustomerExist(customer.getEmail(), customer.getPassword())) {
				throw new CouponSystemException(customer.getEmail() + " email is already exist");
			} else {
				customerDAO.addCustomer(customer);
				System.out.println(customer);
				return customer;
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("couldn't add customer to system.");
		}
	}

	public Customer UpdateCustomer(Customer customer) throws CouponSystemException {
		try {
			if (customerDAO.isCustomerExist(customer.getCustomerID())) {
				customerDAO.UpdateCustomer(customer);
				return customer;
			}
			throw new CouponSystemException("please use the same customerID, you can update only other parameters");
		} catch (CouponSystemException e) {
			throw new CouponSystemException("could'nt update the customer");
		}
	}

	public String deleteCustomer(int customerID) throws CouponSystemException {
		try {
			customerDAO.deleteCustomer(customerID);
			customerDAO.deleteCustomerCoupunsHistory(customerID);
			return "customer has been deleted";
		} catch (CouponSystemException e) {
			throw new CouponSystemException("couldn`t delete customer", e);
		}
	}

	public List<Customer> getAllCustomers() throws CouponSystemException {
		return customerDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) throws CouponSystemException {
		System.out.println(customerDAO.getOneCustomer(customerID));
		return customerDAO.getOneCustomer(customerID);
	}
}
