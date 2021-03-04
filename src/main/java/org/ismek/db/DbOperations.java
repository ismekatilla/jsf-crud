package org.ismek.db;

import org.ismek.domain.Rehber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKARTAL on 4.3.2021.
 */
public class DbOperations {

    static String conUrl="jdbc:mysql://10.0.1.50:3306/deneme?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false";
    static String conUser="deneme";
    static String conPass="1Qaz2wsx!!";

    static {
        classForName();
    }
    
    public static List<Rehber> tumRehberiGetir() {
        List<Rehber> rehberList = new ArrayList<>();
        String sql = "SELECT * FROM REHBER";

        try (
                Connection con= DriverManager.getConnection(conUrl,conUser,conPass);
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("ISIM");
                String phone = resultSet.getString("TELEFON");
                Rehber rehber = new Rehber(id, name, phone);
                rehberList.add(rehber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rehberList;
    }

    public static void rehbereEkle(String name, String phone) {
        String sql = "INSERT INTO REHBER (ISIM,TELEFON)"+" VALUES('%s','%s');";
        sql=String.format(sql,name,phone);
        connectAndExecute(sql);
    }

    public static Rehber kisiGetir(int rehberId) {
        String sql = "SELECT * FROM REHBER WHERE ID = " + rehberId;

        try (
                Connection con= DriverManager.getConnection(conUrl,conUser,conPass);
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("ISIM");
                String phone = resultSet.getString("TELEFON");
                Rehber rehber = new Rehber(id, name, phone);
                return rehber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Rehber> kisiGetir(String isim) {

        List<Rehber> rehberList = new ArrayList<>();

        String sql = "SELECT * FROM REHBER WHERE ISIM = ?";

        try (
                Connection con= DriverManager.getConnection(conUrl,conUser,conPass);
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, isim);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("ISIM");
                String phone = resultSet.getString("TELEFON");
                Rehber rehber = new Rehber(id, name, phone);
                rehberList.add(rehber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rehberList;
    }

    public static void kisiSil(int id){
        String sql = "DELETE FROM REHBER WHERE ID=%d";
        sql=String.format(sql,id);
        connectAndExecute(sql);
    }

    public static void kisiGuncelle(int id,String name,String phone){
        String sql = "UPDATE REHBER SET ISIM='%s',TELEFON='%s' WHERE ID=%d";
        sql=String.format(sql,name,phone,id);
        connectAndExecute(sql);
    }

    private static void classForName(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void connectAndExecute(String sql){
        try (Connection con= DriverManager.getConnection(
                conUrl,conUser,conPass);
             PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}