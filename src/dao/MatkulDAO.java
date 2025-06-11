/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Ari Awaludin
 */

import model.Matkul;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatkulDAO {
    private final Connection conn;

    public MatkulDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE
    public void insert(Matkul m) throws SQLException {
        String sql = "INSERT INTO matkul (nama, sks) VALUES (?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, m.getNama());
            st.setInt   (2, m.getSks());
            st.executeUpdate();
        }
    }

    // READ (All)
    public List<Matkul> getAll() throws SQLException {
        List<Matkul> list = new ArrayList<>();
        String sql = "SELECT * FROM matkul";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Matkul(
                    rs.getInt("id_matkul"),
                    rs.getString("nama"),
                    rs.getInt("sks")
                ));
            }
        }
        return list;
    }

    // UPDATE
    public void update(Matkul m) throws SQLException {
        String sql = "UPDATE matkul SET nama=?, sks=? WHERE id_matkul=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, m.getNama());
            st.setInt   (2, m.getSks());
            st.setInt   (3, m.getId());
            st.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM matkul WHERE id_matkul=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}

