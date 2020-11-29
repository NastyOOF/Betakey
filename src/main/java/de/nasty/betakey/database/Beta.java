/*
 * Class created by Nasty / Ron S.
 * 29.11.20, 05:48
 */

package de.nasty.betakey.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Beta {

    public static int getWhitelisted(String uuid) {
        try {
            PreparedStatement preparedStatement = MySQL.con.prepareStatement("SELECT whitelisted FROM beta WHERE uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("whitelisted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



    public static void setWhitelisted(String uuid, Integer whitelisted) {
        try {
            PreparedStatement preparedStatement = MySQL.con.prepareStatement("UPDATE beta SET whitelisted = ? WHERE uuid = ?");
            preparedStatement.setInt(1, whitelisted);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean isExistingAccount(String uuid) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM beta WHERE uuid='" + uuid + "'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void createAccount(String uuid) {
        MySQL.update("INSERT INTO beta( uuid, whitelisted) VALUES('" + uuid + "', '0')");
    }

    public static void insertKey(String key) {
        MySQL.update("INSERT INTO betacodes(codes, used) VALUES('" + key + "', '0')");
    }

}
