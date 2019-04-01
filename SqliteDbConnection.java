package org.indreiu.travel.controller;

import java.sql.*;

public class SqliteDbConnection {
    private static SqliteDbConnection instance = null;
    private final String user = "root";
    private final String password = "root";
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    public void setSt(Statement st) {
        this.st = st;
    }

    public Connection getCon() {
        return con;
    }

    public Statement getSt() {
        return st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public SqliteDbConnection() throws SQLException {

            con = DriverManager.getConnection("jdbc:sqlite:C:/Users/User/AgentieTurism.db");
            st = con.createStatement();

    }

    public static SqliteDbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new SqliteDbConnection();
        }
        return instance;
    }

    public void close() throws SQLException {
        this.rs.close();
        this.st.close();
        this.con.close();
    }

    public ResultSet execute(String s) throws SQLException {
        if (s.startsWith("UPDATE") || s.startsWith("DELETE") || s.startsWith("INSERT")) {
            st.executeUpdate(s);
            return null;
        } else {
            rs = st.executeQuery(s);
            return rs;
        }
    }

    public static void setConnection(SqliteDbConnection connection) {
        instance = connection;
    }
}
