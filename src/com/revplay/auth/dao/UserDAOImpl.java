package com.revplay.auth.dao;

import com.revplay.auth.util.JDBCUtil;
import com.revplay.auth.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean register(UserAccount user) {

        String sql = """
            INSERT INTO users
            (full_name, email, password_hash,
             security_question, security_answer_hash, status)
            VALUES (?, ?, ?, ?, ?, 'ACTIVE')
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getSecurityQuestion());
            ps.setString(5, user.getSecurityAnswerHash());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserAccount login(String email, String password) {

        String sql = """
            SELECT * FROM users
            WHERE email=? AND password_hash=? AND status='ACTIVE'
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserAccount u = new UserAccount();
                u.setUserId(rs.getInt("user_id"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changePassword(String email, String newPassword) {

        String sql =
                "UPDATE users SET password_hash=? WHERE email=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean forgotPassword(String email, String securityAnswer) {

        String sql =
                "SELECT 1 FROM users WHERE email=? AND security_answer_hash=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, securityAnswer);

            return ps.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
