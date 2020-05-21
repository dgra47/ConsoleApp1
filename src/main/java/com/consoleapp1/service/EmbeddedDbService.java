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

import com.consoleapp1.enums.PropertyEnum;

public class EmbeddedDbService {
    private final PropertiesService propertiesService;
    private Connection connection;

    public EmbeddedDbService(PropertiesService propertiesService) {
        super();
        this.propertiesService = propertiesService;
    }

    public Connection setupDatabase() throws SQLException, IOException {
        connection = getConnection();
        createIfNotExists(connection);
        return getConnection();
    }

    private Connection getConnection() throws SQLException {
        String connectionString = propertiesService.getProperty(PropertyEnum.JDBC_CONNECTION_STRING);
        Connection conn = DriverManager.getConnection(connectionString);
        conn.setAutoCommit(true);

        return conn;
    }

    private void createIfNotExists(Connection con) throws SQLException, IOException {
        Set<String> tables = initDatabaseScheme(con);
        if (!tables.contains("tasks")) {
            ScriptRunner sr = new ScriptRunner(con);
            String initScript = propertiesService.getProperty(PropertyEnum.INTITIAL_SQL_SCRIPT);
            try (InputStream resource = EmbeddedDbService.class.getClassLoader().getResourceAsStream(initScript);
                 InputStreamReader isr = new InputStreamReader(resource)) {
                sr.runScript(isr);
            }
        }
    }

    private Set<String> initDatabaseScheme(Connection targetDBConn) throws SQLException {
        Set<String> set = new HashSet<String>();
        DatabaseMetaData dbmeta = targetDBConn.getMetaData();
        ResultSet rs = dbmeta.getTables(null, null, null, new String[] { "TABLE" });
        while (rs.next()) {
            set.add(rs.getString("TABLE_NAME").toLowerCase());
        }
        return set;
    }
}
