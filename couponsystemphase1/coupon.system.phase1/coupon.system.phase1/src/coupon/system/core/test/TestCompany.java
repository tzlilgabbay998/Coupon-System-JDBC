package coupon.system.core.test;

import java.sql.SQLException;
import java.time.LocalDate;

import coupon.system.core.beans.Category;
import coupon.system.core.beans.Coupon;
import coupon.system.core.dao.CompanyDAO;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.facade.CompanyFacade;
import coupon.system.core.login.ClientType;
import coupon.system.core.login.LoginManager;

public class TestCompany {

	static Coupon newCoupon = new Coupon(1, 1, Category.DRINKS, "Kola", "tasty soda drink with an addiction effect",
		LocalDate.parse("2022-12-03")	,LocalDate.parse("2022-12-12"), 200, 5.3, "each can is heaven");
	static Coupon newCoupon1 = new Coupon(2, 1, Category.FROZEN, "Hamburger", "tasty fast food with an addiction effect",
			LocalDate.parse("2022-12-03")	,LocalDate.parse("2022-12-12"), 100, 11, "each can is heaven");
	static Coupon newCoupon2 = new Coupon(1, 1, Category.DRINKS, "Kola Zero", "tasty soda drink with an addiction effect",
			LocalDate.parse("2022-02-03")	,LocalDate.parse("2022-05-12"), 150, 5.3, "each can is heaven");
	static Coupon newCoupon3 = new Coupon(3, 1, Category.DRINKS, "Fanta", "tasty soda drink with the orange effect",
			LocalDate.parse("2022-07-03")	,LocalDate.parse("2022-12-12"), 60, 5.3, "each can is FANTA");
	static Coupon newCoupon4 = new Coupon(4, 1, Category.ELECTRICITY, "Lamp", "lightning product",
			LocalDate.parse("2022-07-03")	,LocalDate.parse("2022-12-12"), 30, 7, "hit the light");

	private static LoginManager loginManager = LoginManager.getInstance();

	public static void nowTestCompany() throws CouponSystemException, SQLException {
		CompanyFacade companyFacade = (CompanyFacade) loginManager.login(ClientType.Company, "tarG@gmail", "sisma128");
		companyFacade.addCoupon(newCoupon);
		companyFacade.addCoupon(newCoupon1);
		companyFacade.addCoupon(newCoupon3);
		companyFacade.addCoupon(newCoupon4);
		companyFacade.UpdateCoupon(newCoupon2);
		System.out.println(companyFacade.getCouponsByCategory(Category.DRINKS));
		System.out.println(companyFacade.getCompanyCoupons());
		System.out.println(companyFacade.getCouponsByMaxPrice(7));
		companyFacade.deleteCoupon(newCoupon1.getCompanyID());
		System.out.println(companyFacade.companyInfo());
	}

}
