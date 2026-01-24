package com.revplay.dao;

import com.revplay.model.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public boolean createAlbum(Album album) {
        String sql = "INSERT INTO albums (artist_id, title, genre, release_date) VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, album.getArtistId());
            ps.setString(2, album.getTitle());
            ps.setString(3, album.getGenre());
            ps.setString(4, album.getReleaseDate());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Create album failed: " + e.getMessage());
        }
        return false;
    }

    public List<Album> viewAlbumsByArtist(int artistId) {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums WHERE artist_id=? ORDER BY album_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Album a = new Album();
                a.setAlbumId(rs.getInt("album_id"));
                a.setArtistId(rs.getInt("artist_id"));
                a.setTitle(rs.getString("title"));
                a.setGenre(rs.getString("genre"));
                Date d = rs.getDate("release_date");
                a.setReleaseDate(d == null ? null : d.toString());
                albums.add(a);
            }

        } catch (SQLException e) {
            System.out.println("View albums failed: " + e.getMessage());
        }
        return albums;
    }

    public boolean updateAlbum(int albumId, int artistId, String title, String genre, String releaseDate) {
        String sql = "UPDATE albums SET title=?, genre=?, release_date=TO_DATE(?, 'YYYY-MM-DD') WHERE album_id=? AND artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, releaseDate);
            ps.setInt(4, albumId);
            ps.setInt(5, artistId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Update album failed: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteAlbum(int albumId, int artistId) {
        // Check songs exist
        String checkSql = "SELECT COUNT(*) FROM songs WHERE album_id=?";
        String deleteSql = "DELETE FROM albums WHERE album_id=? AND artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, albumId);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    System.out.println("Cannot delete album. Songs exist in this album.");
                    return false;
                }
            }

            try (PreparedStatement delPs = con.prepareStatement(deleteSql)) {
                delPs.setInt(1, albumId);
                delPs.setInt(2, artistId);

                int rows = delPs.executeUpdate();
                return rows > 0;
            }

        } catch (SQLException e) {
            System.out.println("Delete album failed: " + e.getMessage());
        }
        return false;
    }
}
