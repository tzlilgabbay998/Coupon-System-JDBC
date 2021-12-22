package coupon.system.core.test;

import java.sql.SQLException;

import coupon.system.core.beans.Company;
import coupon.system.core.beans.Customer;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.facade.AdminFacade;
import coupon.system.core.login.ClientType;
import coupon.system.core.login.LoginManager;

public class TestAdmin {

	static Company newCompany = new Company(1, "TAR-G", "tar@gmail", "128");
	static Company new2Company = new Company(1, "TAR-G", "tarG@gmail", "sisma128");
	static Company new3Company = new Company(2, "Gili&Tzili", "G&T@gmail", "lll1");
	static Company new4Company = new Company(3, "Gilda", "Gilda@gmail", "1209");
	static Company new5Company = new Company(4, "Stemi", "kvar@gmail", "Metumtemet");
	
	static Customer newCustomer = new Customer(1, "Sara", "Shara", "shir@gmail", "Sameach");
	static Customer new2Customer = new Customer(2, "Sasha", "Sama", "shum@gmail", "BaSushi");
	static Customer new3Customer = new Customer(3, "Ein", "Lee", "Odd@gmail", "Raayonot");
	static Customer new4Customer = new Customer(3, "Adayeen", "Ein", "Lee@gmail", "Raayonot");
	private static LoginManager loginManager = LoginManager.getInstance();

	public static void nowTestAdmin() throws CouponSystemException, SQLException {
		AdminFacade adminFacade = (AdminFacade) loginManager.login(ClientType.Administrator, "admin@admin.com",
				"admin");
		adminFacade.addCompany(newCompany);
		adminFacade.addCompany(new3Company);
		adminFacade.addCompany(new4Company);
		adminFacade.addCompany(new5Company);
		System.out.println(adminFacade.UpdateCompany(new2Company));
		System.out.print(adminFacade.getAllCompanies());
		adminFacade.deleteCompany(new3Company.getCompanyID());
		adminFacade.getOneCompany(newCompany.getCompanyID());
		
		adminFacade.addCustomer(newCustomer);
		adminFacade.addCustomer(new2Customer);
		adminFacade.addCustomer(new3Customer);
		System.out.println(adminFacade.getAllCustomers());
		adminFacade.getOneCustomer(newCustomer.getCustomerID());
		System.out.println(adminFacade.UpdateCustomer(new4Customer));
		adminFacade.deleteCustomer(newCustomer.getCustomerID());
	}
		
	}

