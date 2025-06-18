package dao;

import model.Dosen;
import config.Database;
import java.sql.*;
import java.util.*;

public class DosenDAO {

    public List<Dosen> getAll() {
        List<Dosen> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM dosen")) {

            while (rs.next()) {
                Dosen m = new Dosen(
                    rs.getInt("id_dosen"),
                    rs.getString("nama"),
                    rs.getString("alamat")
                );
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertDosen(Dosen m) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO dosen (nama, alamat) VALUES (?, ?)")) {

            ps.setString(1, m.getNama());
            ps.setString(2, m.getAlamat());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDosen(Dosen m) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE dosen SET nama = ?, alamat = ? WHERE id_dosen = ?")) {

            ps.setString(1, m.getNama());
            ps.setString(2, m.getAlamat());
            ps.setInt(3, m.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDosen(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM dosen WHERE id_dosen = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
