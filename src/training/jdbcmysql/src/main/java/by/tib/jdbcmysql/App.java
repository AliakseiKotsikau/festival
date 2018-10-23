package by.tib.jdbcmysql;

import java.sql.*;


public class App {
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/festivaldb", "student", "student")) {
			Statement stat = conn.createStatement();

			// Select prepared statement
			PreparedStatement prepStat = conn.prepareStatement(
					"SELECT username, password, permission FROM logins " + "Where user_id BETWEEN ? AND ?");
			prepStat.setInt(1, 1);
			prepStat.setInt(2, 3);

			ResultSet set = prepStat.executeQuery();
			while (set.next()) {
				System.out.println(set.getString("username") + " " + set.getString("password") + " "
						+ set.getString("permission"));
			}

			// Update statement
			int count = stat.executeUpdate("UPDATE festivaldb.logins SET username = 'updated name' WHERE user_id>1");
			System.out.println("Rows affected: " + count);

			// Select after update
			prepStat.setInt(1, 1);
			prepStat.setInt(2, 3);

			ResultSet setUpd = prepStat.executeQuery();
			while (setUpd.next()) {
				System.out.println(setUpd.getString("username") + " " + setUpd.getString("password") + " "
						+ setUpd.getString("permission"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
