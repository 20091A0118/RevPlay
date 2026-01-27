package com.revplay.dao;

import com.revplay.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revplay.util.JDBCUtil;


public class GenreDaoImpl implements IGenreDao {

    @Override
    public void addGenre(Genre genre) {
        String sql = "INSERT INTO genre (genre_name) VALUES (?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, genre.getGenreName());
            ps.executeUpdate();
            System.out.println("Genre added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGenre(Genre genre) {
        String sql = "UPDATE genre SET genre_name=? WHERE genre_id=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, genre.getGenreName());
            ps.setInt(2, genre.getGenreId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGenre(int genreId) {
        String sql = "DELETE FROM genre WHERE genre_id=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, genreId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> list = new ArrayList<>();
        String sql = "SELECT * FROM genre";

        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("genre_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

