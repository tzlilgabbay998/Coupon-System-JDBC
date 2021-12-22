package coupon.system.core.job;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import coupon.system.core.beans.Coupon;
import coupon.system.core.dao.CouponDAO;
import coupon.system.core.dao.CouponDBDAO;
import coupon.system.core.exceptions.CouponSystemException;

public class CouponExpirationDailyJob extends Thread {

	private CouponDAO couponDAO;

	public CouponExpirationDailyJob() {
		couponDAO = new CouponDBDAO();
	}

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.DAYS.sleep(1);
				List<Coupon> coupons = couponDAO.getAllCoupons();
				for (Coupon coupon : coupons) {
					if (coupon.getEndDate().isBefore(LocalDate.now())) {
						couponDAO.deleteCouponPurchase(coupon.getCouponID());
						couponDAO.deleteCoupon(coupon.getCouponID());
					}

				}
			}
		} catch (InterruptedException e) {
			System.out.println("job interrupted");
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void stopJob() {
		this.interrupt();
	}

}
