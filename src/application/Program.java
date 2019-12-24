package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;

		try {

			conn = DB.getConnection();
			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller " + "SET BaseSalary=3999.99 " + "WHERE departmentId = 1");

			// Force a false error

			int x = 0;
			if (x < 1) {
				throw new SQLException("Fake error");
			}
			int rows2 = st.executeUpdate("UPDATE seller " + "SET BaseSalary=7400.99 " + "WHERE departmentId = 2");

			System.out.println("rows1= " + rows1);
			System.out.println("rows2= " + rows2);

		}

		catch (SQLException error) {

			error.printStackTrace();

		}

		finally {

			DB.closeStatement(st);
			DB.closeResultSet(res);
			DB.closeConnection();

		}

	}
}
