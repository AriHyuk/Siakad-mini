/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Mahasiswa;
import config.Database;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Ari Awaludin
 */
public class MahasiswaDAO {
	public List<Mahasiswa> getAllMahasiswa() {
    	List<Mahasiswa> list = new ArrayList<>();
    	try (Connection conn = (Connection) Database.getConnection();
         	Statement st = conn.createStatement();
         	ResultSet rs = st.executeQuery("SELECT * FROM mahasiswa")) {
        	while (rs.next()) {
            	Mahasiswa m = new Mahasiswa(
                	rs.getInt("id"),
                	rs.getString("nim"),
                	rs.getString("nama"),
                	rs.getString("jurusan")
            	);
            	list.add(m);
        	}
    	} catch (Exception e) { e.printStackTrace(); }
    	return list;
	}

	public void insertMahasiswa(Mahasiswa m) {
    	try (Connection conn = (Connection) Database.getConnection();
         	PreparedStatement ps = conn.prepareStatement("INSERT INTO mahasiswa (nim, nama, jurusan) VALUES (?, ?, ?)")) {
        	ps.setString(1, m.getNim());
        	ps.setString(2, m.getNama());
        	ps.setString(3, m.getJurusan());
        	ps.executeUpdate();
    	} catch (Exception e) { e.printStackTrace(); }
	}

	public void updateMahasiswa(Mahasiswa m) {
    	try (Connection conn = (Connection) Database.getConnection();
         	PreparedStatement ps = conn.prepareStatement("UPDATE mahasiswa SET nim=?, nama=?, jurusan=? WHERE id=?")) {
        	ps.setString(1, m.getNim());
        	ps.setString(2, m.getNama());
        	ps.setString(3, m.getJurusan());
        	ps.setInt(4, m.getId());
        	ps.executeUpdate();
    	} catch (Exception e) { e.printStackTrace(); }
	}

	public void deleteMahasiswa(int id) {
    	try (Connection conn = (Connection) Database.getConnection();
         	PreparedStatement ps = conn.prepareStatement("DELETE FROM mahasiswa WHERE id=?")) {
        	ps.setInt(1, id);
        	ps.executeUpdate();
    	} catch (Exception e) { e.printStackTrace(); }
	}
}

