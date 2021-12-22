package coupon.system.core.facade;

import coupon.system.core.dao.CompanyDAO;
import coupon.system.core.dao.CompanyDBDAO;
import coupon.system.core.dao.CouponDAO;
import coupon.system.core.dao.CouponDBDAO;
import coupon.system.core.dao.CustomerDAO;
import coupon.system.core.dao.CustomerDBDAO;
import coupon.system.core.exceptions.CouponSystemException;

public abstract class ClientFacade {

	protected CompanyDAO companyDAO = new CompanyDBDAO();
	protected CustomerDAO customerDAO = new CustomerDBDAO();
	protected CouponDAO couponDAO = new CouponDBDAO();

	public abstract boolean login(String email, String password) throws CouponSystemException;

}
