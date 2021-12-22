package coupon.system.core.test;

import java.sql.SQLException;

import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.facade.CompanyFacade;

public class Program {

	public static void main(String[] args) throws CouponSystemException, SQLException {
		Test.testAll();
	}

}
