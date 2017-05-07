package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cj.discount.db.GetConnection;
import com.cj.discount.model.Activity;
import com.cj.discount.model.User;

public class CollectionDao {

	/**
	 * 添加收藏
	 */
	public void addCollection(int a_id, int c_id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		conn = GetConnection.getConnection();
		sql = "insert into collection (C_id, A_id) values(?,?)";
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, c_id);
			stmt.setInt(2, a_id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
	}

	/**
	 * 删除收藏
	 */
	public void deleteCollextion(int a_id, int c_id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			conn = GetConnection.getConnection();
			sql = "delete from collection where A_id=? and C_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a_id);
			stmt.setInt(2, c_id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
	}

	/**
	 * 判断是否已收藏
	 */
	public boolean isCollected(int a_id, int c_id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		boolean i = false;
		try {
			conn = GetConnection.getConnection();
			sql = "select * from collection where A_id=? and C_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a_id);
			stmt.setInt(2, c_id);
			ResultSet rs1 = (ResultSet) stmt.executeQuery();
			if (rs1.next()) {
				i = true;
				rs1.close();
				stmt.close();
			} else {
				i = false;
				rs1.close();
				stmt.close();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 查找所有活动id
	 */
	public ArrayList<Integer> getAllAId(int id) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = GetConnection.getConnection();
			String sql = "select * from collection where C_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Integer i = rs.getInt("A_id");
				list.add(i);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
	}

	/**
	 * 判断时候存在收藏
	 */
	public boolean isExistC(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean i = false;
		try {
			conn = GetConnection.getConnection();
			String sql = "select * from collection where C_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs1 = (ResultSet) stmt.executeQuery();
			if (rs1.next()) {
				i = true;
				rs1.close();
				stmt.close();
			} else {
				i = false;
				rs1.close();
				stmt.close();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
