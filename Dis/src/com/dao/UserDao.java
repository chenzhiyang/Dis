/*
 * Decompiled with CFR 0_121.
 */
package com.dao;

import com.cj.discount.db.GetConnection;
import com.cj.discount.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao {
    private Connection conn;
    private PreparedStatement pstat = null;
    String sql = "";

    public String logoin(User user) throws SQLException {
        this.conn = GetConnection.getConnection();
        String i = "false";
        try {
            this.sql = "call login_a(?,?)";
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setString(1, user.getUsername());
            this.pstat.setString(2, user.getPassword());
            ResultSet rs1 = this.pstat.executeQuery();
            if (rs1.next()) {
                if (rs1.getInt("B_active") == 1) {
                    i = "true";
                    rs1.close();
                    this.pstat.close();
                } else {
                    i = "noactive";
                    rs1.close();
                    this.pstat.close();
                }
            } else {
                i = "false";
                rs1.close();
                this.pstat.close();
            }
            this.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean mailCheck(User user) throws SQLException {
        this.conn = GetConnection.getConnection();
        boolean i = false;
        try {
            this.sql = "select * from business where B_mail=?";
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setString(1, user.getMail());
            ResultSet rs1 = this.pstat.executeQuery();
            if (rs1.next()) {
                i = true;
                rs1.close();
                this.pstat.close();
            } else {
                i = false;
                rs1.close();
                this.pstat.close();
            }
            this.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean usernameCheck(User user) throws SQLException {
        this.conn = GetConnection.getConnection();
        boolean i = false;
        try {
            this.sql = "select * from business where B_username=?";
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setString(1, user.getUsername());
            ResultSet rs1 = this.pstat.executeQuery();
            if (rs1.next()) {
                i = true;
                rs1.close();
                this.pstat.close();
            } else {
                i = false;
                rs1.close();
                this.pstat.close();
            }
            this.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean nameCheck(User user) throws SQLException {
        this.conn = GetConnection.getConnection();
        boolean i = false;
        try {
            this.sql = "select * from business where B_name=?";
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setString(1, user.getName());
            ResultSet rs1 = this.pstat.executeQuery();
            if (rs1.next()) {
                i = true;
                rs1.close();
                this.pstat.close();
            } else {
                i = false;
                rs1.close();
                this.pstat.close();
            }
            this.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public void addUser(User user) throws SQLException {
        this.conn = GetConnection.getConnection();
        this.sql = "insert into business (B_username, B_password, B_name,B_type,B_add, B_mail,B_active) values(?,?,?,?,?,?,?)";
        try {
            try {
                this.pstat = this.conn.prepareStatement(this.sql);
                this.pstat.setString(1, user.getUsername());
                this.pstat.setString(2, user.getPassword());
                this.pstat.setString(3, user.getName());
                this.pstat.setString(4, user.getType());
                this.pstat.setString(5, user.getAddress());
                this.pstat.setString(6, user.getMail());
                this.pstat.setBoolean(7, user.getActive());
                this.pstat.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                this.pstat.close();
                this.conn.close();
            }
        }
        finally {
            this.pstat.close();
            this.conn.close();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        this.conn = GetConnection.getConnection();
        this.sql = "select * from business where B_username=?";
        ResultSet rs = null;
        try {
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setString(1, username);
            rs = this.pstat.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("B_id"));
                u.setName(rs.getString("B_name"));
                u.setUsername(rs.getString("B_username"));
                u.setPassword(rs.getString("B_password"));
                u.setType(rs.getString("B_type"));
                u.setAddress(rs.getString("B_add"));
                u.setMail(rs.getString("B_mail"));
                u.setActive(rs.getBoolean("B_active"));
                User user = u;
                return user;
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (this.pstat != null) {
                try {
                    this.pstat.close();
                    this.pstat = null;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void deleteBusinessById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try {
            try {
                conn = GetConnection.getConnection();
                sql = "delete from business where B_id=?";
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

    public ArrayList<User> getAllUsers() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<User> list = new ArrayList<User>();
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from business";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("B_id"));
                user.setName(rs.getString("B_name"));
                user.setUsername(rs.getString("B_username"));
                user.setPassword(rs.getString("B_password"));
                user.setType(rs.getString("B_type"));
                user.setAddress(rs.getString("B_add"));
                user.setMail(rs.getString("B_mail"));
                user.setActive(rs.getBoolean("B_active"));
                list.add(user);
            }
            ArrayList<User> arrayList = list;
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

    public void updateUserById(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            try {
                conn = GetConnection.getConnection();
                String sql = "update business set B_username=?, B_password=?, B_name=?, B_type=?,B_add=? ,B_mail=?,B_active=? where B_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getName());
                stmt.setString(4, user.getType());
                stmt.setString(5, user.getAddress());
                stmt.setString(6, user.getMail());
                stmt.setBoolean(7, user.getActive());
                stmt.setInt(8, user.getId());
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

    public User getUserById(int id) throws SQLException {
        this.conn = GetConnection.getConnection();
        this.sql = "select * from business where B_id=?";
        ResultSet rs = null;
        try {
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setInt(1, id);
            rs = this.pstat.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("B_id"));
                u.setName(rs.getString("B_name"));
                u.setUsername(rs.getString("B_username"));
                u.setPassword(rs.getString("B_password"));
                u.setType(rs.getString("B_type"));
                u.setAddress(rs.getString("B_add"));
                u.setMail(rs.getString("B_mail"));
                u.setActive(rs.getBoolean("B_active"));
                User user = u;
                return user;
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            rs.close();
            this.pstat.close();
            this.conn.close();
        }
    }
}
