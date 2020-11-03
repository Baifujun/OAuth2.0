package com.gzhtdq.daas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

@SpringBootTest
public class HanaTest {

    public static String connectionString = "jdbc:sap://192.168.128.231:31015";
    public static String user = "SAPABAP1";
    public static String password = "MOCo2017";
    @Resource(name = "hanaDataSource")
    private DataSource dataSource;

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("select 'hello world' from dummy");
                resultSet.next();
                String hello = resultSet.getString(1);
                System.out.println(hello);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void connectHana() {
        System.out.println(dataSource);
    }
}
