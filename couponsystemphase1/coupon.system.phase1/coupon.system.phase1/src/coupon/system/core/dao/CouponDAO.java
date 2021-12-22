package coupon.system.core.dao;

import java.util.List;

import coupon.system.core.beans.Coupon;
import coupon.system.core.exceptions.CouponSystemException;

public interface CouponDAO {

	void addCoupon(Coupon coupon) throws CouponSystemException;

	Coupon updateCoupon(Coupon coupon) throws CouponSystemException;

	String deleteCoupon(int couponID) throws CouponSystemException;

	Coupon getOneCoupon(int couponID) throws CouponSystemException;

	void addCouponPurchase(int customerID, int couponID) throws CouponSystemException;

	void deleteCouponPurchase(int couponID) throws CouponSystemException;

	List<Coupon> getAllCoupons() throws CouponSystemException;

	boolean isCouponExist(String title, int companyID) throws CouponSystemException;

	List<Integer> getCouponID(int companyID) throws CouponSystemException;
}