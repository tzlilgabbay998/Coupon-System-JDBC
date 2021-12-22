package coupon.system.core.dao;

import java.util.List;

import coupon.system.core.beans.Category;
import coupon.system.core.beans.Coupon;
import coupon.system.core.beans.Customer;
import coupon.system.core.exceptions.CouponSystemException;

public interface CustomerDAO {

	boolean isCustomerExist(String email, String password) throws CouponSystemException;

	int getCustomerID(String email) throws CouponSystemException;

	int addCustomer(Customer customer) throws CouponSystemException;

	Customer UpdateCustomer(Customer customer) throws CouponSystemException;

	Customer deleteCustomer(int customerID) throws CouponSystemException;

	void deleteCustomerCoupunsHistory(int customerID) throws CouponSystemException;

	List<Customer> getAllCustomers() throws CouponSystemException;

	boolean isCustomerPurchasedThisCouponAlready(int customerID, int couponID) throws CouponSystemException;

	List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException;

	List<Coupon> getCustomerCouponsByCategory(int customerID, Category category) throws CouponSystemException;

	List<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice) throws CouponSystemException;

	Customer getOneCustomer(int customerID) throws CouponSystemException;

	boolean isCustomerExist(int customerID, String name) throws CouponSystemException;

	boolean isCustomerExist(int customerID) throws CouponSystemException;
}