/*
 * Decompiled with CFR 0_121.
 */
package com.dao;

import com.cj.discount.db.GetConnection;
import com.cj.discount.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDao {
    private Connection conn;
    private PreparedStatement pstat = null;
    private String sql = "";

    public boolean logoin(Customer user) throws SQLException {
        this.conn = GetConnection.getConnection();
        boolean i = false;
        try {
            this.sql = "select * from customer where C_username=? and C_password=?";
            this.pstat = this.conn.prepareStatement(this.sql);
            this.pstat.setString(1, user.getUsername());
            this.pstat.setString(2, user.getPassword());
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

    public boolean usernameCheck(Customer user) throws SQLException {
        this.conn = GetConnection.getConnection();
        boolean i = false;
        try {
            this.sql = "select * from customer where C_username=?";
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

    public void addUser(Customer user) throws SQLException {
        this.conn = GetConnection.getConnection();
        this.sql = "insert into customer (C_username, C_password) values(?,?)";
        try {
            try {
                this.pstat = this.conn.prepareStatement(this.sql);
                this.pstat.setString(1, user.getUsername());
                this.pstat.setString(2, user.getPassword());
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

    public ArrayList<Customer> getAllCustomer() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Customer> list = new ArrayList<Customer>();
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from customer;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Customer user = new Customer();
                user.setId(rs.getInt("C_id"));
                user.setName(rs.getString("C_name"));
                user.setUsername(rs.getString("C_username"));
                user.setPassword(rs.getString("c_password"));
                list.add(user);
            }
            ArrayList<Customer> arrayList = list;
            return arrayList;
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
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            conn.close();
        }
    }

    public void deleteCustomerById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try {
            try {
                conn = GetConnection.getConnection();
                sql = "delete from customer where C_id=?";
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

    public boolean nameCheck(Customer user) throws SQLException {
        boolean i;
        block6 : {
            this.conn = GetConnection.getConnection();
            i = false;
            try {
                try {
                    this.sql = "select * from customer where C_name=?";
                    this.pstat = this.conn.prepareStatement(this.sql);
                    this.pstat.setString(1, user.getName());
                    ResultSet rs1 = this.pstat.executeQuery();
                    if (rs1.next()) {
                        i = true;
                        rs1.close();
                        break block6;
                    }
                    i = false;
                    rs1.close();
                }
                catch (Exception e) {
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
        return i;
    }

    public void updateCustomerById(Customer user) throws SQLException {
        Connection conn = null;
       PreparedStatement stmt = null;
        try {
            try {
                conn = GetConnection.getConnection();
                String sql = "update customer set C_username=?, C_password=?, C_name=? where C_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getName());
                stmt.setInt(4, user.getId());
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

    public Customer getCustomerById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from customer where C_id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Customer user = new Customer();
                user.setId(rs.getInt("C_id"));
                user.setName(rs.getString("C_name"));
                user.setUsername(rs.getString("C_username"));
                user.setPassword(rs.getString("c_password"));
                Customer customer = user;
                return customer;
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
            conn.close();
        }
    }

    public Customer getCustomerByUsername(String username) throws SQLException {
        Connection conn = null;
       PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = GetConnection.getConnection();
            String sql = "select * from customer where C_username=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Customer user = new Customer();
                user.setId(rs.getInt("C_id"));
                user.setName(rs.getString("C_name"));
                user.setUsername(rs.getString("C_username"));
                user.setPassword(rs.getString("c_password"));
                Customer customer = user;
                return customer;
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
            conn.close();
        }
    }
}
