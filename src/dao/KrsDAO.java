/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import config.Database;
import model.Krs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ari Awaludin
 */





public class KrsDAO {

    public List<Krs> getAllKrs() {
        List<Krs> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM krs")) {

            while (rs.next()) {
                Krs k = new Krs(
                    rs.getInt("id"),
                    rs.getInt("id_mahasiswa"),
                    rs.getInt("id_jadwal"),
                    rs.getString("semester")
                );
                list.add(k);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertKrs(Krs k) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO krs (id_mahasiswa, id_jadwal, semester) VALUES (?, ?, ?)")) {

            ps.setInt(1, k.getIdMahasiswa());
            ps.setInt(2, k.getIdJadwal());
            ps.setString(3, k.getSemester());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateKrs(Krs k) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE krs SET id_mahasiswa=?, id_jadwal=?, semester=? WHERE id=?")) {

            ps.setInt(1, k.getIdMahasiswa());
            ps.setInt(2, k.getIdJadwal());
            ps.setString(3, k.getSemester());
            ps.setInt(4, k.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteKrs(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM krs WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


