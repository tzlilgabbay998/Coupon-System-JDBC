package coupon.system.core.test;

import java.sql.SQLException;

import coupon.system.core.db.ConnectionPool;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.job.CouponExpirationDailyJob;

public class Test {

	public static void testAll() throws CouponSystemException, SQLException {
		CouponExpirationDailyJob startJob = new CouponExpirationDailyJob();

		try {
			startJob.start();
		} catch (IllegalThreadStateException e) {
			throw new CouponSystemException("Thread issue. could'nt start the daily job", e);
		}

		try {
			TestAdmin.nowTestAdmin();
		} catch (CouponSystemException | SQLException e) {
			throw new CouponSystemException("Admin issue: something got wrong " + e.getCause());
		}

		try {
			TestCompany.nowTestCompany();
		} catch (CouponSystemException | SQLException e) {
			throw new CouponSystemException("Company issue: something got wrong " + e.getCause());
		}

		try {
			TestCustomer.nowTestCustomer();
		} catch (CouponSystemException | SQLException e) {
			throw new CouponSystemException("Customer issue: something got wrong " + e.getCause());
		}

		try {
			startJob.stopJob();
		} catch (IllegalThreadStateException e) {
			throw new CouponSystemException("Thread issue. could'nt stop the daily job");
		}

		try {
			ConnectionPool.getInstance().closeAllConnections();
		} catch (CouponSystemException e) {
			throw new CouponSystemException("closing ConnectionPool got failed: " + e.getCause());
		}
	}

}