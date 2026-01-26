package com.revplay.dao;

import com.revplay.model.UserAccount;
import com.revplay.util.JDBCUtil;

import java.sql.*;

public class UserDAOImpl {

    public boolean saveUser(UserAccount user) {
        String sql = """
            INSERT INTO users
            (full_name, email, password_hash, security_question, security_answer, status)
            VALUES (?, ?, ?, ?, ?, 'ACTIVE')
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getSecurityQuestion());
            ps.setString(5, user.getSecurityAnswer());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserAccount findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new UserAccount(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("security_question"),
                        rs.getString("security_answer"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password_hash = ? WHERE email = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}