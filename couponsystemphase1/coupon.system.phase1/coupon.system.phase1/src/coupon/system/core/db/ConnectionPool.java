package coupon.system.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import coupon.system.core.exceptions.CouponSystemException;

public class ConnectionPool {

	private Set<Connection> connections = new HashSet<Connection>();
	private String url = "jdbc:mysql://localhost:3306/coupon_sys_db";
	private String user = "root";
	private String pass = "1234";
	private boolean poolOpen;

	public static final int max = 10;

	private static ConnectionPool instance;

	private ConnectionPool() throws SQLException {
		for (int i = 0; i < max; i++) {
			this.connections.add(DriverManager.getConnection(url, user, pass));
		}
		System.out.println("connection pool initialized with " + connections.size());
		poolOpen = true;
	}

	public static ConnectionPool getInstance() throws CouponSystemException {
		if (instance == null) {
			try {
				instance = new ConnectionPool();
			} catch (SQLException e) {
				throw new CouponSystemException("connection pool init failed", e);
			}
		}
		return instance;
	}

	public synchronized Connection getConnection() throws CouponSystemException {
		if (!poolOpen) {
			throw new CouponSystemException("getConnection failed - pool is closed");
		}
		while (this.connections.isEmpty()) {
			// while there are no connections - wait
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// when connections are available - remove one from the set and return it
		Iterator<Connection> it = this.connections.iterator();
		Connection con = it.next();

		it.remove();
		return con;
	}

	public synchronized void restoreConnection(Connection con) {
		connections.add(con);
		notify();
	}

	public synchronized void closeAllConnections() throws CouponSystemException {
		int c = 0;
		this.poolOpen = false;
		while (this.connections.size() < max) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				for (Connection connection : connections) {
					connection.close();
					c++;
				}
			} catch (SQLException e) {
				throw new CouponSystemException("close all connection failed", e);
			}
			System.out.println("connection pool closed " + c + " connections");
		}
		closeAllConnections();

	}
}
