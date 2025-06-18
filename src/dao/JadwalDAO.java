/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Ari Awaludin
 */

import model.Jadwal;
import config.Database;
import java.sql.*;
import java.util.*;

public class JadwalDAO {
    public void insert(Jadwal j) {
        String sql = "INSERT INTO jadwal (id_matkul, id_dosen, ruang, waktu) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, j.getIdMatkul());
            st.setInt(2, j.getIdDosen());
            st.setString(3, j.getRuang());
            st.setString(4, j.getWaktu());
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Jadwal> getAll() {
        List<Jadwal> list = new ArrayList<>();
        String sql = "SELECT * FROM jadwal";
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Jadwal(
                    rs.getInt("id_jadwal"),
                    rs.getInt("id_matkul"),
                    rs.getInt("id_dosen"),
                    rs.getString("ruang"),
                    rs.getString("waktu")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void update(Jadwal j) {
        String sql = "UPDATE jadwal SET id_matkul=?, id_dosen=?, ruang=?, waktu=? WHERE id_jadwal=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, j.getIdMatkul());
            st.setInt(2, j.getIdDosen());
            st.setString(3, j.getRuang());
            st.setString(4, j.getWaktu());
            st.setInt(5, j.getId());
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM jadwal WHERE id_jadwal=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
