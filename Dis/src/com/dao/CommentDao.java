package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cj.discount.db.GetConnection;
import com.cj.discount.model.Comment;

public class CommentDao {

	/**
	 * 得到该活动所有评论
	 */
	public ArrayList<Comment> getAllComments(int id) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			conn = GetConnection.getConnection();
			String sql = "select * from comment where A_id2 = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setId(rs.getInt("id"));
				com.setContent(rs.getString("content"));
				list.add(com);
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
	 * 添加评论
	 */
	public void addComment(int a_id, int c_id, String content)
			throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		conn = GetConnection.getConnection();
		sql = "insert into comment (C_id, A_id2,content) values(?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, c_id);
			stmt.setInt(2, a_id);
			stmt.setString(3, content);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
	}

	/**
	 * 得到该活动所有评论
	 */
	public ArrayList<Comment> getAllCommentsByCId(int c_id) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			conn = GetConnection.getConnection();
			String sql = "select * from comment where C_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, c_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setId(rs.getInt("id"));
				com.setContent(rs.getString("content"));
				list.add(com);
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
}
