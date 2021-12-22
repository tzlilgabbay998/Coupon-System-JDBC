package coupon.system.core.login;

import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.facade.AdminFacade;
import coupon.system.core.facade.ClientFacade;
import coupon.system.core.facade.CompanyFacade;
import coupon.system.core.facade.CustomerFacade;

public class LoginManager {

	private final static LoginManager INSTANCE = new LoginManager();

	public static LoginManager getInstance() {
		return INSTANCE;
	}

	private LoginManager() {
	}

	public ClientFacade login(ClientType ct, String email, String password) throws CouponSystemException {
		switch (ct) {
		case Administrator:
			ClientFacade admin = new AdminFacade();
			if (admin.login(email, password)) {
				return admin;
			}
			break;
		case Company:
			ClientFacade company = new CompanyFacade();
			if (company.login(email, password)) {
				return company;
			}
			break;
		case Customer:
			ClientFacade customer = new CustomerFacade();
			if (customer.login(email, password)) {
				return customer;
			}
			break;
		}
		return null;
	}

}
