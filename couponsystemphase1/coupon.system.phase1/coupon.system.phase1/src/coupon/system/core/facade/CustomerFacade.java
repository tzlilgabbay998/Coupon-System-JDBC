package coupon.system.core.facade;

import java.time.LocalDate;
import java.util.List;

import coupon.system.core.beans.Category;
import coupon.system.core.beans.Coupon;
import coupon.system.core.beans.Customer;
import coupon.system.core.exceptions.CouponSystemException;

public class CustomerFacade extends ClientFacade {

	private int customerID;

	public CustomerFacade() {
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (customerDAO.isCustomerExist(email, password)) {
			customerID = customerDAO.getCustomerID(email);
			return true;
		}
		System.out.println("one or more of the details are incorrect");
		return false;
	}

	public String addCouponPurchase(int couponID) throws CouponSystemException {
		Coupon ct = null;
		try {
			ct = couponDAO.getOneCoupon(couponID);
			if (customerDAO.isCustomerPurchasedThisCouponAlready(customerID, couponID)) {
				throw new CouponSystemException("you have already purchased this coupon");
			} else if (ct.getAmount() == 0) {
				throw new CouponSystemException("this coupon is out of stock");
			} else if (ct.getEndDate().isBefore(LocalDate.now())) {
				throw new CouponSystemException("coupon had expired");
			} else {
				ct.setAmount(ct.getAmount() - 1);
				couponDAO.addCouponPurchase(customerID, couponID);
				return "you have purchased the coupon successfully";
			}

		} catch (CouponSystemException e) {
			throw new CouponSystemException("trying to purchase coupon had failed.", e);
		}
	}

	public List<Coupon> customerCoupons() throws CouponSystemException {
		while (customerDAO.getAllCustomerCoupons(customerID) == null) {
			throw new CouponSystemException("List is empty. coupons of yours doesn't exist");
		}
		return customerDAO.getAllCustomerCoupons(customerID);
	}

	public List<Coupon> customerCouponsByCategory(Category category) throws CouponSystemException {
		while (customerDAO.getCustomerCouponsByCategory(customerID, category) == null) {
			throw new CouponSystemException("List is empty. coupons by this category doesn't exist");
		}
		return customerDAO.getCustomerCouponsByCategory(customerID, category);
	}

	public List<Coupon> customerCouponsByMaxPrice(double maxPrice) throws CouponSystemException {
		while (customerDAO.getCustomerCouponsByMaxPrice(customerID, maxPrice) == null) {
			throw new CouponSystemException("List is empty. coupons by this price doesn't exist");
		}
		return customerDAO.getCustomerCouponsByMaxPrice(customerID, maxPrice);
	}

	public Customer customerInfo() throws CouponSystemException {
		return customerDAO.getOneCustomer(customerID);
	}

}