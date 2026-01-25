package com.revplay.dao;

import com.revplay.model.Album;
import com.revplay.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOImpl implements IAlbumDAO {

    @Override
    public boolean createAlbum(Album album) {

        String sql = "INSERT INTO albums (artist_id, title, genre, release_date) VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, album.getArtistId());
            ps.setString(2, album.getTitle());
            ps.setString(3, album.getGenre());
            ps.setString(4, album.getReleaseDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Create Album Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Album> getAlbumsByArtist(int artistId) {

        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums WHERE artist_id=? ORDER BY album_id";

        try (Connection con = JDBCUtil.getConnection();
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

        } catch (Exception e) {
            System.out.println("View Albums Error: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public boolean updateAlbum(Album album) {

        String sql = "UPDATE albums SET title=?, genre=?, release_date=TO_DATE(?, 'YYYY-MM-DD') " +
                "WHERE album_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, album.getTitle());
            ps.setString(2, album.getGenre());
            ps.setString(3, album.getReleaseDate());
            ps.setInt(4, album.getAlbumId());
            ps.setInt(5, album.getArtistId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update Album Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAlbum(int albumId, int artistId) {

        String checkSql = "SELECT COUNT(*) FROM songs WHERE album_id=?";
        String deleteSql = "DELETE FROM albums WHERE album_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection()) {

            // 1) Check songs count
            try (PreparedStatement cps = con.prepareStatement(checkSql)) {
                cps.setInt(1, albumId);
                ResultSet rs = cps.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        System.out.println("Cannot delete album. Songs exist in that album.");
                        return false;
                    }
                }
            }

            // 2) Delete album
            try (PreparedStatement dps = con.prepareStatement(deleteSql)) {
                dps.setInt(1, albumId);
                dps.setInt(2, artistId);
                return dps.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Delete Album Error: " + e.getMessage());
            return false;
        }
    }
}
