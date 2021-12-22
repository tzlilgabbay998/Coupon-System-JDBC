package coupon.system.core.test;

import java.sql.SQLException;

import coupon.system.core.beans.Category;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.facade.CustomerFacade;
import coupon.system.core.login.ClientType;
import coupon.system.core.login.LoginManager;

public class TestCustomer {

	private static LoginManager loginManager = LoginManager.getInstance();

	public static void nowTestCustomer() throws CouponSystemException, SQLException {

		CustomerFacade customerFacade = (CustomerFacade) loginManager.login(ClientType.Customer, "shum@gmail",
				"BaSushi");

		customerFacade.addCouponPurchase(3);
		customerFacade.addCouponPurchase(4);
		System.out.println(customerFacade.customerCoupons());
		System.out.println(customerFacade.customerCouponsByCategory(Category.DRINKS));
		System.out.println(customerFacade.customerCouponsByMaxPrice(6.5));
		System.out.println(customerFacade.customerInfo());
	}
//		companyFacade.addCoupon(newCoupon);
//		companyFacade.addCoupon(newCoupon1);
//		companyFacade.addCoupon(newCoupon3);
//		companyFacade.addCoupon(newCoupon4);
//		companyFacade.UpdateCoupon(newCoupon2);
//		System.out.println(companyFacade.getCouponsByCategory(Category.DRINKS));
//		System.out.println(companyFacade.getCompanyCoupons(1));
//		System.out.println(companyFacade.getCouponsByMaxPrice(1, 7));
//		companyFacade.deleteCoupon(newCoupon1.getCompanyID());
//		System.out.println(companyFacade.companyInfo());
//	}

}
