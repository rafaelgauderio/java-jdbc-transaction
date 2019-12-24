package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;

		try {

			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller " + "SET BaseSalary=4200.00 " + "WHERE departmentId = 1");

			// Force a false error
			/*
			int x = 0;
			if (x < 1) {
				throw new SQLException("Fake error");					
			}
			*/
			
			int rows2 = st.executeUpdate("UPDATE seller " + "SET BaseSalary=8400.00 " + "WHERE departmentId = 2");
			
			int rows3 = st.executeUpdate("UPDATE seller " + "SET BaseSalary=7000.00 " + "WHERE Name ='Juliana da Silva' ");

			conn.commit();
			
			System.out.println("rows1= " + rows1);
			System.out.println("rows2= " + rows2);
			System.out.println("rows2= " + rows3);
			
			System.out.println();

			res = st.executeQuery("SELECT Name, BaseSalary, DepartmentID " + "FROM seller WHERE DepartmentId = 1 OR 2 OR 3");

			while (res.next()) {

				System.out.println("Updated Salarys [" + res.getString("Name") + ", "
						+ String.format("U$ %.2f", res.getDouble("BaseSalary")) + "]");
			}

		}

		catch (SQLException error) {

			try {
				conn.rollback();
				throw new DbException("Transaction rolled back because "+error.getMessage());
			}
			
			catch (SQLException error2) {
				throw new DbException("Erros trying to rollback. Caused by: "+ error2.getMessage());
				
			}

		}

		finally {

			DB.closeStatement(st);
			DB.closeResultSet(res);
			DB.closeConnection();

		}

	}
}
