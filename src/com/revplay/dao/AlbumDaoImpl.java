package com.revplay.dao;

import com.revplay.model.Album;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl implements IAlbumDao {

    @Override
    public void addAlbum(Album album) {

        String sql = "INSERT INTO album (album_name, artist_id, release_date) VALUES (?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, album.getAlbumName());
            ps.setInt(2, album.getArtistId());
            ps.setDate(3, album.getReleaseDate());

            ps.executeUpdate();
            System.out.println("Album added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Album> getAlbumsByArtist(int artistId) {

        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM album WHERE artist_id = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Album album = new Album();
                album.setAlbumId(rs.getInt("album_id"));
                album.setAlbumName(rs.getString("album_name"));
                album.setArtistId(rs.getInt("artist_id"));
                album.setReleaseDate(rs.getDate("release_date"));
                albums.add(album);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }
}
