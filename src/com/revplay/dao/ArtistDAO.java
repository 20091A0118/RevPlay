package com.revplay.dao;

import com.revplay.model.Artist;

import java.sql.*;

public class ArtistDAO {

    public boolean registerArtist(Artist artist) {
        String sql = "INSERT INTO artists (name, email, password, bio, genre, socials) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, artist.getName());
            ps.setString(2, artist.getEmail());
            ps.setString(3, artist.getPassword());
            ps.setString(4, artist.getBio());
            ps.setString(5, artist.getGenre());
            ps.setString(6, artist.getSocials());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Register failed: " + e.getMessage());
        }
        return false;
    }

    public Artist loginArtist(String email, String password) {
        String sql = "SELECT * FROM artists WHERE email=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Artist artist = new Artist();
                artist.setArtistId(rs.getInt("artist_id"));
                artist.setName(rs.getString("name"));
                artist.setEmail(rs.getString("email"));
                artist.setPassword(rs.getString("password"));
                artist.setBio(rs.getString("bio"));
                artist.setGenre(rs.getString("genre"));
                artist.setSocials(rs.getString("socials"));
                return artist;
            }

        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return null;
    }

    public Artist viewProfile(int artistId) {
        String sql = "SELECT * FROM artists WHERE artist_id=?";

        try (Connection con = DBConnection.getConnection();
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
                a.setSocials(rs.getString("socials"));
                return a;
            }

        } catch (SQLException e) {
            System.out.println("View profile failed: " + e.getMessage());
        }
        return null;
    }

    public boolean updateProfile(int artistId, String bio, String genre, String socials) {
        String sql = "UPDATE artists SET bio=?, genre=?, socials=? WHERE artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bio);
            ps.setString(2, genre);
            ps.setString(3, socials);
            ps.setInt(4, artistId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Update profile failed: " + e.getMessage());
        }
        return false;
    }
}

