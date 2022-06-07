package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.AccessConnection;
import model.SanPham;
public class ProductDB {

	private Connection conn;

	public boolean addProduct(SanPham product) {
		conn = AccessConnection.getConnection();
		String sqlCommand = "INSERT INTO product(tenBanh, soLuong, gia, ngaySX, hanSD, tt) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(6, product.getTt());
			pst.setString(1, product.getTenBanh());
			pst.setString(2, product.getSoLuong());
			pst.setString(3, product.getGia());
			pst.setString(4, product.getNgaySX());
			pst.setString(5, product.getHanSD());
			
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccessConnection.closePreparedStatement(pst);
			AccessConnection.closeConnect(conn);
		}
		return result;
	}

	public boolean updateProduct(SanPham product) {
		conn = AccessConnection.getConnection();
		String sqlCommand = "UPDATE product SET tenBanh = ?, soLuong = ? , gia = ? , ngaySX = ? , hanSD = ?"
				+ "WHERE tt = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(6, product.getTt());
			pst.setString(1, product.getTenBanh());
			pst.setString(2, product.getSoLuong());
			pst.setString(3, product.getGia());
			pst.setString(4, product.getNgaySX());
			pst.setString(5, product.getHanSD());
			
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccessConnection.closePreparedStatement(pst);
			AccessConnection.closeConnect(conn);
		}
		return result;
	}

	public boolean deleteProduct(String tt) {
		conn = AccessConnection.getConnection();
		String sqlCommand = "DELETE FROM product WHERE tt = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(1, tt);
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccessConnection.closePreparedStatement(pst);
			AccessConnection.closeConnect(conn);
		}
		return result;
	}

	public List<SanPham> getProductList() {
		List<SanPham> productList = new ArrayList<SanPham>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = AccessConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery("Select * from product");
			String tt;
			String tenBanh;
			String soLuong;
			String gia;
			String ngaySX;
			String hanSD;

			while (rs.next()) {
				tt = rs.getString(6);
				tenBanh = rs.getString(1);
				soLuong = rs.getString(2);
				gia = rs.getString(3);
				ngaySX = rs.getString(4);
				hanSD = rs.getString(5);
				productList.add(new SanPham(tt, tenBanh, soLuong, gia, ngaySX, hanSD));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccessConnection.closeResultSet(rs);
			AccessConnection.closeStatement(statement);
			AccessConnection.closeConnect(conn);
		}
		return productList;
	}
	
	public List<SanPham> searchProductList(String search) {
		List<SanPham> productList = new ArrayList<SanPham>();
		Statement statement = null;
		ResultSet rs = null;
		String sql_search = "Select * from product where"
				+ " tt like '%" + search + "%' or tenBanh like '5" + search
				+ "%' or soLuong like '%" + search + "%' or gia like '%" + search + "%'";
		System.out.println(sql_search);
		try {
			conn = AccessConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql_search);
			String tt;
			String tenBanh;
			String soLuong;
			String gia;
			String ngaySX;
			String hanSD;

			while (rs.next()) {
				tt = rs.getString(6);
				tenBanh = rs.getString(1);
				soLuong = rs.getString(2);
				gia = rs.getString(3);
				ngaySX = rs.getString(4);
				hanSD = rs.getString(5);
				productList.add(new SanPham(tt, tenBanh, soLuong, gia, ngaySX, hanSD));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccessConnection.closeResultSet(rs);
			AccessConnection.closeStatement(statement);
			AccessConnection.closeConnect(conn);
		}
		return productList;
	}
	
	public boolean checkProductExists(String tt) {
		Statement statement = null;
		ResultSet rs = null;
		boolean isExits = false;
		String sql = "Select * from product WHERE tt =" + tt;
		try {
			conn = AccessConnection.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			System.out.println("OOOOOOOOODIDIDD");
			System.out.println(rs);
			while (rs.next()) {
				if (rs.getString(1) != null) {
					isExits = true;
				}
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccessConnection.closeResultSet(rs);
			AccessConnection.closeStatement(statement);
			AccessConnection.closeConnect(conn);
		}
		System.out.println("CHECK TRUNG");
		System.out.println(isExits);
		return isExits;
	}
}
