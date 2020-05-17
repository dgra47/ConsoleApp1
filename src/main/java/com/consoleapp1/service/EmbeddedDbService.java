package com.consoleapp1.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.jdbc.ScriptRunner;

public class EmbeddedDbService {
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:./consoleAppDb;create=true");
        conn.setAutoCommit(true);

        return conn;
    }

    public static void createIfNotExists(Connection con) throws SQLException, IOException {
        Set<String> tables = getDBTables(con);
        if (!tables.contains("tasks")) {
            ScriptRunner sr = new ScriptRunner(con);
            try (InputStream resource = EmbeddedDbService.class.getClassLoader().getResourceAsStream("createTasksTable.sql");
                 InputStreamReader isr = new InputStreamReader(resource)) {
                sr.runScript(isr);
            }
        }
    }

    private static Set<String> getDBTables(Connection targetDBConn) throws SQLException {
        Set<String> set = new HashSet<String>();
        DatabaseMetaData dbmeta = targetDBConn.getMetaData();
        readDBTable(set, dbmeta, "TABLE", null);
        return set;
    }

    private static void readDBTable(Set<String> set, DatabaseMetaData dbmeta, String searchCriteria, String schema)
            throws SQLException {
        ResultSet rs = dbmeta.getTables(null, schema, null, new String[] { searchCriteria });
        while (rs.next()) {
            set.add(rs.getString("TABLE_NAME").toLowerCase());
        }
    }
}
