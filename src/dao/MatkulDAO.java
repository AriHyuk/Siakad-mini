package dao;

import model.Matkul;
import config.Database;
import java.sql.*;
import java.util.*;

public class MatkulDAO {
    // CREATE
    public void insert(Matkul m) {
        String sql = "INSERT INTO matkul (kode_mk, nama, sks) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, m.getKodeMk());
            st.setString(2, m.getNama());
            st.setInt(3, m.getSks());
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // READ (All)
    public List<Matkul> getAll() {
        List<Matkul> list = new ArrayList<>();
        String sql = "SELECT * FROM matkul";
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Matkul(
                    rs.getInt("id_matkul"),
                    rs.getString("kode_mk"),
                    rs.getString("nama"),
                    rs.getInt("sks")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // UPDATE
    public void update(Matkul m) {
        String sql = "UPDATE matkul SET kode_mk=?, nama=?, sks=? WHERE id_matkul=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, m.getKodeMk());
            st.setString(2, m.getNama());
            st.setInt(3, m.getSks());
            st.setInt(4, m.getId());
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM matkul WHERE id_matkul=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}

