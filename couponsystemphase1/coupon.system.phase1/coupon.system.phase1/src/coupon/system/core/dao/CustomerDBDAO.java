package coupon.system.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import coupon.system.core.beans.Category;
import coupon.system.core.beans.Coupon;
import coupon.system.core.beans.Customer;
import coupon.system.core.db.ConnectionPool;
import coupon.system.core.exceptions.CouponSystemException;

public class CustomerDBDAO implements CustomerDAO {

	@Override
	public boolean isCustomerExist(String email, String password) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer where email= ? and password= ? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t search for customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public boolean isCustomerExist(int customerID, String name) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer where id= ? and first_name= ? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, customerID);
			pstmt.setString(2, name);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t search for customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public boolean isCustomerExist(int customerID) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer where id=? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, customerID);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t search for customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public int addCustomer(Customer customer) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "insert into customer values(?,?,?,?,?) ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setInt(1, customer.getCustomerID());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getEmail());
			pstmt.setString(5, customer.getPassword());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int autoGeneratedId = rs.getInt(1);
			customer.setCustomerID(autoGeneratedId);
			return autoGeneratedId;
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t add customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public Customer UpdateCustomer(Customer customer) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "update customer set first_name= ? , last_name= ? , email= ? , password= ? where id= ? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getCustomerID());
			pstmt.executeUpdate();
			return customer;
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t update customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public Customer deleteCustomer(int customerID) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "delete from customer where id=? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, customerID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t delete customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return null;

	}

	@Override
	public List<Customer> getAllCustomers() throws CouponSystemException {
		List<Customer> list = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer ; ";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String FirstName = rs.getString("first_name");
				String LastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Customer customer = new Customer(id, FirstName, LastName, email, password);
				list.add(customer);
			}
			return list;
		} catch (Exception e) {
			throw new CouponSystemException("get All customer failed", e);
		}

	}

	@Override
	public Customer getOneCustomer(int customerID) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select* from customer where id=? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, customerID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Customer customer = new Customer();
			customer.setCustomerID(customerID);
			customer.setFirstName(rs.getString("First_Name"));
			customer.setLastName(rs.getString("Last_Name"));
			customer.setEmail(rs.getString("Email"));
			customer.setPassword(rs.getString("Password"));
			return customer;
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t find customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void deleteCustomerCoupunsHistory(int customerID) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "delete from customer_vs_coupon where customer_id=? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, customerID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t delete customer's coupons history", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public int getCustomerID(String email) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select id from customer where email= ? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new CouponSystemException("could'nt find data", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public boolean isCustomerPurchasedThisCouponAlready(int customerID, int couponID) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer_vs_coupon where customer_id= ? and coupon_id= ? ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, customerID);
			pstmt.setInt(2, couponID);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new CouponSystemException("couldn`t search this data", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where id in(select coupon_id from customer_vs_coupon where customer_id = ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);
			ResultSet rs = pstmt.executeQuery();
			List<Coupon> coupons = new ArrayList<Coupon>();
			while (rs.next()) {
				int couponID = rs.getInt("id");
				int companyID = rs.getInt("company_id");
				Category category = (Category.values()[rs.getInt("category_id")]);
				String title = rs.getString("Title");
				String description = rs.getString("Description");
				LocalDate startDate = (LocalDate.parse(rs.getDate("Start_date").toString()));
				LocalDate endDate = (LocalDate.parse(rs.getDate("End_date").toString()));
				int amount = rs.getInt("Amount");
				double price = rs.getDouble("Price");
				String image = rs.getString("Image");
				Coupon coupon = new Coupon(couponID, companyID, category, title, description, startDate, endDate,
						amount, price, image);
				coupons.add(coupon);
			}
			return coupons;
		} catch (SQLException e) {
			throw new CouponSystemException("couldn't find coupons for this customer");
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}

	@Override
	public List<Coupon> getCustomerCouponsByCategory(int customerID, Category category) throws CouponSystemException {
		List<Coupon> categoryCoupons = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where category_id = ? and id in(select coupon_id from customer_vs_coupon where customer_id = ? ) ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, category.ordinal());
			pstmt.setInt(2, customerID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int couponID = rs.getInt("id");
				int companyID = rs.getInt("company_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				LocalDate startDate = (LocalDate.parse(rs.getDate("start_date").toString()));
				LocalDate endDate = (LocalDate.parse(rs.getDate("end_date").toString()));
				int amount = rs.getInt("amount");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				Coupon coupon = new Coupon(couponID, companyID, category, title, description, startDate, endDate,
						amount, price, image);
				categoryCoupons.add(coupon);
			}
			return categoryCoupons;
		} catch (Exception e) {
			throw new CouponSystemException("get All coupons of your company by category, has failed", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public List<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice) throws CouponSystemException {
		List<Coupon> coupons = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where price <= ? and id in(select coupon_id from customer_vs_coupon where customer_id = ? ) ;";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setDouble(1, maxPrice);
			pstmt.setInt(2, customerID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int couponID = rs.getInt("id");
				int companyID = rs.getInt("company_id");
				Category category = (Category.values()[rs.getInt("category_id")]);
				String title = rs.getString("title");
				String description = rs.getString("description");
				LocalDate startDate = (LocalDate.parse(rs.getDate("start_date").toString()));
				LocalDate endDate = (LocalDate.parse(rs.getDate("end_date").toString()));
				int amount = rs.getInt("amount");
				double price = rs.getDouble("Price");
				String image = rs.getString("image");
				Coupon coupon = new Coupon(couponID, companyID, category, title, description, startDate, endDate,
						amount, price, image);
				coupons.add(coupon);
			}
			return coupons;
		} catch (Exception e) {
			throw new CouponSystemException("get All coupons by this price, has failed", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}
}
