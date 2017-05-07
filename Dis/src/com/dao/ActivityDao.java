/*
 * Decompiled with CFR 0_121.
 */
package com.dao;

import com.cj.discount.db.GetConnection;
import com.cj.discount.model.Activity;
import com.cj.discount.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityDao {
    public Activity getActivityById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = GetConnection.getConnection();
            sql = "select * from activity where A_id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Activity activity = new Activity();
                activity.setId(id);
                activity.setContent(rs.getString("A_content"));
                activity.setFromDate(rs.getDate("A_fromTime"));
                activity.setIssueDate(rs.getDate("A_issue"));
                activity.setToDate(rs.getDate("A_toTime"));
                activity.setName(rs.getString("A_name"));
                activity.setPicture(rs.getString("A_picture"));
                activity.setType(rs.getString("A_type"));
                activity.setZan(rs.getInt("A_zan"));
                Activity activity2 = activity;
                return activity2;
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            rs.close();
            stmt.close();
        }
    }

    public ArrayList<Activity> getAllActivities(User user) throws SQLException {
        Connection conn = null;
       PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from activity where B_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setId(rs.getInt("A_id"));
                activity.setName(rs.getString("A_name"));
                activity.setIssueDate(rs.getDate("A_issue"));
                activity.setFromDate(rs.getDate("A_fromTime"));
                activity.setToDate(rs.getDate("A_toTime"));
                activity.setPicture(rs.getString("A_picture"));
                activity.setType(rs.getString("A_type"));
                activity.setContent(rs.getString("A_content"));
                activity.setZan(rs.getInt("A_zan"));
                list.add(activity);
            }
            ArrayList<Activity> arrayList = list;
            return arrayList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }

    public ArrayList<Activity> getAllActivities() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from activity;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setId(rs.getInt("A_id"));
                activity.setName(rs.getString("A_name"));
                activity.setIssueDate(rs.getDate("A_issue"));
                activity.setFromDate(rs.getDate("A_fromTime"));
                activity.setToDate(rs.getDate("A_toTime"));
                activity.setPicture(rs.getString("A_picture"));
                activity.setType(rs.getString("A_type"));
                activity.setContent(rs.getString("A_content"));
                activity.setZan(rs.getInt("A_zan"));
                list.add(activity);
            }
            ArrayList<Activity> arrayList = list;
            return arrayList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }

    public void updateType(Activity activity) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            try {
                conn = GetConnection.getConnection();
                String sql = "update activity set A_type=? where A_id=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "\u8fc7\u671f");
                stmt.setInt(2, activity.getId());
                stmt.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
                stmt.close();
                conn.close();
            }
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    public void addActivity(Activity activity, User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        conn = GetConnection.getConnection();
        sql = "insert into activity (A_name, A_issue, A_fromTime, A_toTime, A_picture, A_type, A_content, A_zan, B_id) values(?,?,?,?,?,?,?,?,?)";
        try {
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, activity.getName());
                stmt.setDate(2, activity.getIssueDate());
                stmt.setDate(3, activity.getFromDate());
                stmt.setDate(4, activity.getToDate());
                stmt.setString(5, activity.getPicture());
                stmt.setString(6, activity.getType());
                stmt.setString(7, activity.getContent());
                stmt.setInt(8, activity.getZan());
                stmt.setInt(9, user.getId());
                stmt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                stmt.close();
                conn.close();
            }
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    public void deleteActivityById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try {
            try {
                conn = GetConnection.getConnection();
                sql = "delete from activity where A_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
                stmt.close();
                conn.close();
            }
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    public void updateActivity(Activity activity) throws SQLException {
        Connection conn = null;
      PreparedStatement stmt = null;
        String sql = null;
        try {
            try {
                conn = GetConnection.getConnection();
                sql = "update activity set A_name=?,A_fromTime=?,A_toTime=?,A_content=? ,A_type=? where A_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, activity.getName());
                stmt.setDate(2, activity.getFromDate());
                stmt.setDate(3, activity.getToDate());
                stmt.setString(4, activity.getContent());
                stmt.setString(5, activity.getType());
                stmt.setInt(6, activity.getId());
                stmt.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
                stmt.close();
                conn.close();
            }
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    public void passActivityById(int id) throws SQLException {
        Connection conn = null;
       PreparedStatement stmt = null;
        String sql = null;
        try {
            try {
                conn = GetConnection.getConnection();
                sql = "update activity set A_type=? where A_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "\u8fdb\u884c");
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
                stmt.close();
                conn.close();
            }
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    public void rejectActivityById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try {
            try {
                conn = GetConnection.getConnection();
                sql = "update activity set A_type=? where A_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "\u9a73\u56de");
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
                stmt.close();
                conn.close();
            }
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    public ArrayList<Activity> getAllOngoingActivities() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from activity where A_type=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "\u8fdb\u884c");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setId(rs.getInt("A_id"));
                activity.setName(rs.getString("A_name"));
                activity.setIssueDate(rs.getDate("A_issue"));
                activity.setFromDate(rs.getDate("A_fromTime"));
                activity.setToDate(rs.getDate("A_toTime"));
                activity.setPicture(rs.getString("A_picture"));
                activity.setType(rs.getString("A_type"));
                activity.setContent(rs.getString("A_content"));
                activity.setZan(rs.getInt("A_zan"));
                list.add(activity);
            }
            ArrayList<Activity> arrayList = list;
            return arrayList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }

    public ArrayList<Activity> findActivities(String keywords) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from activity where A_type=? and A_name like ? or A_content like ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "\u8fdb\u884c");
            stmt.setString(2, "%" + keywords + "%");
            stmt.setString(3, "%" + keywords + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setId(rs.getInt("A_id"));
                activity.setName(rs.getString("A_name"));
                activity.setIssueDate(rs.getDate("A_issue"));
                activity.setFromDate(rs.getDate("A_fromTime"));
                activity.setToDate(rs.getDate("A_toTime"));
                activity.setPicture(rs.getString("A_picture"));
                activity.setType(rs.getString("A_type"));
                activity.setContent(rs.getString("A_content"));
                activity.setZan(rs.getInt("A_zan"));
                list.add(activity);
            }
            ArrayList<Activity> arrayList = list;
            return arrayList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }

    public boolean canFindActivities(String keywords) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from activity where A_name like ? or A_content like ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keywords + "%");
            stmt.setString(2, "%" + keywords + "%");
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }
}
