package com.revplay.dao;

import com.revplay.model.Album;
import com.revplay.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOImpl implements IAlbumDAO {

    @Override
    public int createAlbum(Album album) {

        String sql = "INSERT INTO albums (artist_id, title, genre, release_date) " +
                "VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"album_id"})) {

            if (con == null) return 0;

            ps.setInt(1, album.getArtistId());
            ps.setString(2, album.getTitle());
            ps.setString(3, album.getGenre());
            ps.setString(4, album.getReleaseDate());

            int rows = ps.executeUpdate();
            if (rows == 0) return 0;

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1); // ✅ album_id
            }

        } catch (Exception e) {
            System.out.println("Create Album Error: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public List<Album> getAlbumsByArtist(int artistId) {

        List<Album> list = new ArrayList<>();

        String sql = "SELECT album_id, artist_id, title, genre, TO_CHAR(release_date, 'YYYY-MM-DD') AS release_date " +
                "FROM albums WHERE artist_id=? ORDER BY album_id";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return list;

            ps.setInt(1, artistId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Album a = new Album();
                a.setAlbumId(rs.getInt("album_id"));
                a.setArtistId(rs.getInt("artist_id"));
                a.setTitle(rs.getString("title"));
                a.setGenre(rs.getString("genre"));
                a.setReleaseDate(rs.getString("release_date"));
                list.add(a);
            }

        } catch (Exception e) {
            System.out.println("View Albums Error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public boolean updateAlbum(Album album) {

        String sql = "UPDATE albums SET title=?, genre=?, release_date=TO_DATE(?, 'YYYY-MM-DD') " +
                "WHERE album_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return false;

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

        // delete song_stats -> songs -> album
        String deleteStats = "DELETE FROM song_stats WHERE song_id IN (SELECT song_id FROM songs WHERE album_id=?)";
        String deleteSongs = "DELETE FROM songs WHERE album_id=?";
        String deleteAlbum = "DELETE FROM albums WHERE album_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection()) {

            if (con == null) return false;

            try (PreparedStatement ps1 = con.prepareStatement(deleteStats)) {
                ps1.setInt(1, albumId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(deleteSongs)) {
                ps2.setInt(1, albumId);
                ps2.executeUpdate();
            }

            try (PreparedStatement ps3 = con.prepareStatement(deleteAlbum)) {
                ps3.setInt(1, albumId);
                ps3.setInt(2, artistId);
                return ps3.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Delete Album Error: " + e.getMessage());
            return false;
        }
    }
}
