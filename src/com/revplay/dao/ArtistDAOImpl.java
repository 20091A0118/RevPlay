package com.revplay.dao;

import com.revplay.model.Artist;
import com.revplay.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistDAOImpl implements IArtistDAO {

    @Override
    public boolean registerArtist(Artist artist) {

        String sql = "INSERT INTO artists (name, email, password, bio, genre, instagram, youtube) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, artist.getName());
            ps.setString(2, artist.getEmail());
            ps.setString(3, artist.getPassword());
            ps.setString(4, artist.getBio());
            ps.setString(5, artist.getGenre());
            ps.setString(6, artist.getInstagram());
            ps.setString(7, artist.getYoutube());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Register Artist Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Artist loginArtist(String email, String password) {

        String sql = "SELECT * FROM artists WHERE email=? AND password=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Artist a = new Artist();
                a.setArtistId(rs.getInt("artist_id"));
                a.setName(rs.getString("name"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
                a.setBio(rs.getString("bio"));
                a.setGenre(rs.getString("genre"));
                a.setInstagram(rs.getString("instagram"));
                a.setYoutube(rs.getString("youtube"));
                return a;
            }

        } catch (Exception e) {
            System.out.println("Login Artist Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Artist getArtistById(int artistId) {

        String sql = "SELECT * FROM artists WHERE artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Artist a = new Artist();
                a.setArtistId(rs.getInt("artist_id"));
                a.setName(rs.getString("name"));
                a.setEmail(rs.getString("email"));
                a.setBio(rs.getString("bio"));
                a.setGenre(rs.getString("genre"));
                a.setInstagram(rs.getString("instagram"));
                a.setYoutube(rs.getString("youtube"));
                return a;
            }

        } catch (Exception e) {
            System.out.println("Get Artist Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateArtistProfile(int artistId, String bio, String genre, String instagram, String youtube) {

        String sql = "UPDATE artists SET bio=?, genre=?, instagram=?, youtube=? WHERE artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bio);
            ps.setString(2, genre);
            ps.setString(3, instagram);
            ps.setString(4, youtube);
            ps.setInt(5, artistId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update Profile Error: " + e.getMessage());
            return false;
        }
    }
}
