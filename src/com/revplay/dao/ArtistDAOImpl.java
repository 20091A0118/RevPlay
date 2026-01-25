package com.revplay.dao;

import com.revplay.model.ArtistAccount;
import com.revplay.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ArtistDAOImpl implements IArtistDAO {

    @Override
    public boolean registerArtist(ArtistAccount artist) {

        String sql = "INSERT INTO ARTIST_ACCOUNT " +
                "(stage_name, email, password_hash, bio, genre, instagram_link, youtube_link, spotify_link, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            ps.setString(1, artist.getStageName());
            ps.setString(2, artist.getEmail());
            ps.setString(3, artist.getPasswordHash());
            ps.setString(4, artist.getBio());
            ps.setString(5, artist.getGenre());
            ps.setString(6, artist.getInstagramLink());
            ps.setString(7, artist.getYoutubeLink());
            ps.setString(8, artist.getSpotifyLink());
            ps.setString(9, artist.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Register Artist Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArtistAccount loginArtist(String email, String passwordHash) {

        String sql = "SELECT * FROM ARTIST_ACCOUNT WHERE email=? AND password_hash=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return null;
            }

            ps.setString(1, email);
            ps.setString(2, passwordHash);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapArtist(rs);
            }

        } catch (Exception e) {
            System.out.println("Login Artist Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public ArtistAccount getArtistById(int artistId) {

        String sql = "SELECT * FROM ARTIST_ACCOUNT WHERE artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return null;
            }

            ps.setInt(1, artistId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapArtist(rs);
            }

        } catch (Exception e) {
            System.out.println("Get Artist Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateArtistProfile(int artistId, String bio, String genre,
                                       String instagramLink, String youtubeLink,
                                       String spotifyLink, String status) {

        String sql = "UPDATE ARTIST_ACCOUNT SET bio=?, genre=?, instagram_link=?, youtube_link=?, spotify_link=?, status=? " +
                "WHERE artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            ps.setString(1, bio);
            ps.setString(2, genre);
            ps.setString(3, instagramLink);
            ps.setString(4, youtubeLink);
            ps.setString(5, spotifyLink);
            ps.setString(6, status);
            ps.setInt(7, artistId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update Profile Error: " + e.getMessage());
            return false;
        }
    }

    private ArtistAccount mapArtist(ResultSet rs) throws Exception {

        ArtistAccount a = new ArtistAccount();
        a.setArtistId(rs.getInt("artist_id"));
        a.setStageName(rs.getString("stage_name"));
        a.setEmail(rs.getString("email"));
        a.setPasswordHash(rs.getString("password_hash"));
        a.setBio(rs.getString("bio"));
        a.setGenre(rs.getString("genre"));
        a.setInstagramLink(rs.getString("instagram_link"));
        a.setYoutubeLink(rs.getString("youtube_link"));
        a.setSpotifyLink(rs.getString("spotify_link"));
        a.setStatus(rs.getString("status"));

        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) a.setCreatedAt(ts.toLocalDateTime());

        return a;
    }
}
