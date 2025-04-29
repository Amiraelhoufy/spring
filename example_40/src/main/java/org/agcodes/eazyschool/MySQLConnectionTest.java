package org.agcodes.eazyschool;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {
  public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/eazyschool?useSSL=false&serverTimezone=UTC";
    String user = "user";
    String password = "password";

    try (Connection conn = DriverManager.getConnection(url, user, password)) {
      System.out.println("✅ Connected to MySQL successfully!");
    } catch (Exception e) {
      System.err.println("❌ Connection failed: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
