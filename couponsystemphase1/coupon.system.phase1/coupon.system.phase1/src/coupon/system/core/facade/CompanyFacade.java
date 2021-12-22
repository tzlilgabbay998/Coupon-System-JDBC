package coupon.system.core.facade;

import java.util.List;

import coupon.system.core.beans.Category;
import coupon.system.core.beans.Company;
import coupon.system.core.beans.Coupon;
import coupon.system.core.exceptions.CouponSystemException;

public class CompanyFacade extends ClientFacade {

	private int companyID;

	public CompanyFacade() {
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (companyDAO.isCompanyExist(email, password)) {
			companyID = companyDAO.getCompanyID(email);
			return true;
		}
		System.out.println("one or more of the details are incorrect");
		return false;
	}

	public void addCoupon(Coupon coupon) throws CouponSystemException {
		if (coupon.getCompanyID() == companyID) {
			if (couponDAO.isCouponExist(coupon.getTitle(), companyID)) {
				throw new CouponSystemException("this coupon for your company, by this title, is already exist");
			} else {
				couponDAO.addCoupon(coupon);
			}
		} else {
			throw new CouponSystemException("companyID isn't equals to the coupon.companyID");
		}

	}

	public void UpdateCoupon(Coupon coupon) throws CouponSystemException {
		Coupon coupon1 = null;
		try {
			coupon1 = couponDAO.getOneCoupon(coupon.getCouponID());
			if (!(coupon.getCompanyID() == companyID)) {
				throw new CouponSystemException("companyID isn't equals to the coupon.companyID");
			}
			if (couponDAO.isCouponExist(coupon.getTitle(), coupon.getCompanyID())
					&& (!(coupon.getTitle() == coupon1.getTitle()))) {
				throw new CouponSystemException("this title for coupon in your company, is already exist");
			}
			couponDAO.updateCoupon(coupon);
		} catch (CouponSystemException e) {
			System.out.println("couldn't find this coupon id");
		}
	}

	public void deleteCoupon(int couponID) throws CouponSystemException {
		couponDAO.deleteCoupon(couponID);
		couponDAO.deleteCouponPurchase(couponID);
	}

	public List<Coupon> getCompanyCoupons() throws CouponSystemException {
		while (companyDAO.getCompanyCoupons(companyID) == null) {
			throw new CouponSystemException("List is empty. coupons of your company doesn't exist");
		}
		return companyDAO.getCompanyCoupons(companyID);

	}

	public List<Coupon> getCouponsByCategory(Category category) throws CouponSystemException {
		while (companyDAO.getCompanyCouponsByCategory(companyID, category) == null) {
			throw new CouponSystemException("List is empty. coupons of your company by this category doesn't exist");
		}
		return companyDAO.getCompanyCouponsByCategory(companyID, category);
	}

	public List<Coupon> getCouponsByMaxPrice(double maxPrice) throws CouponSystemException {
		while (companyDAO.getCompanyCouponsByMaxPrice(companyID, maxPrice) == null) {
			throw new CouponSystemException("List is empty. coupons of your company by this price doesn't exist");
		}
		return companyDAO.getCompanyCouponsByMaxPrice(companyID, maxPrice);
	}

	public Company companyInfo() throws CouponSystemException {
		return companyDAO.getOneCompany(companyID);
	}
}