package coupon.system.core.dao;

import java.util.List;

import coupon.system.core.beans.Category;
import coupon.system.core.beans.Company;
import coupon.system.core.beans.Coupon;
import coupon.system.core.exceptions.CouponSystemException;

public interface CompanyDAO {

	boolean isCompanyExist(String email, String password) throws CouponSystemException;

	int getCompanyID(String email) throws CouponSystemException;

	void addCompany(Company company) throws CouponSystemException;

	Company UpdateCompany(Company company) throws CouponSystemException;

	void deleteCompany(int companyID) throws CouponSystemException;

	void deleteAllCompanyCoupons(int companyID) throws CouponSystemException;

	void deleteCompanyCouponsHistory(int companyID) throws CouponSystemException;

	List<Company> getAllCompanies() throws CouponSystemException;

	List<Coupon> getCompanyCoupons(int companyID) throws CouponSystemException;

	List<Coupon> getCompanyCouponsByCategory(int companyID, Category category) throws CouponSystemException;

	List<Coupon> getCompanyCouponsByMaxPrice(int companyID, double maxPrice) throws CouponSystemException;

	Company getOneCompany(int companyID) throws CouponSystemException;

	boolean isCompanyExist(int companyID) throws CouponSystemException;

	boolean doesCompanyExist(String email, String name) throws CouponSystemException;
}