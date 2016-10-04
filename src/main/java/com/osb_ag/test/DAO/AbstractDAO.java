package com.osb_ag.test.DAO;



import com.osb_ag.test.Exception.InitializationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by deHrys.
 */
public abstract class AbstractDAO {

    protected Connection con;
    protected PreparedStatement statement;

    private final String ID_FIELD = "id";
    private final String WORD_FIELD = "word";
    private static ArrayList<Character> symbols = new ArrayList<>();
    static {
        for (char i = 'a'; i <= 'z'; i++) {
            symbols.add(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            symbols.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            symbols.add(i);
        }
    };

    protected abstract String getTableName();

    protected AbstractDAO() throws InitializationException {
        init();
    }

    private void init() throws InitializationException {
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("resources/connection.properties");
            prop.load(input);
            String connectionLine = "jdbc:mysql://" + prop.getProperty("server") + ":" + prop.getProperty("port")
                    + "/" + prop.getProperty("db") + "?user=" + prop.getProperty("user") + "&password=" + prop.getProperty("password");
//            use SSL connection
            connectionLine += "&useSSL=true";
//            fix for timezone
            connectionLine += "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con = DriverManager.getConnection(connectionLine);
        } catch (SQLException | IOException e) {
            throw new InitializationException("Can't initialize properly");
        }
    }

    public void putEntry(int id, String string) {
        try {
            String query = "INSERT into " + getTableName() + " (" +  ID_FIELD + ", " + WORD_FIELD + ") VALUE (?, ?)";
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, string);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillTableWithRandomData(int size) {
        try {
            String query = "INSERT into " + getTableName() + " (" + WORD_FIELD + ") VALUE (?)";
            statement = con.prepareStatement(query);
            int batchSize = 10;
            int count = 0;
            for (int i = 0; i < size; i++) {
                statement.setString(1,randomString(5));
                statement.addBatch();
                if ( ++count % batchSize == 0 ) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String randomString(int size) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int position = (int) (Math.random() * symbols.size());
            string.append(symbols.get(position));
        }
        return string.toString();
    }

}
