package ch.andu.booster.sql;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
    private HikariDataSource hikari;
    public SQL(HikariDataSource hikari){
        this.hikari = hikari;
    }

    public void createTabel(){
        String update = "CREATE TABLE IF NOT EXISTS booster(player_uuid varchar(64), player_booster int);";
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = hikari.getConnection();
            stmt =  connection.prepareStatement(update);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean playerExist(String uuid){
        Connection connection = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_uuid FROM booster WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return true;
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void createPlayer(String uuid){
        if(playerExist(uuid))return;

        Connection connection = null;
        PreparedStatement stmt = null;

        String update = "INSERT INTO booster(player_uuid, player_booster) VALUES (?,?)";
        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            stmt.setInt(2,0);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//Hier könnte man noch hinzufügen, dass der spieler den booster freischalten kann
 /*   public boolean getPlayerFreigeschalten(String uuid){
        Connection connection = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_freigeschaltet FROM booster WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return true;
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
*/
    public int getPlayerBooster(String uuid){
        Connection connection = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_booster FROM booster WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               return rs.getInt(1);
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
       return 0;
    }

    public void setPlayerBooster(String uuid, int amount){
        Connection connection = null;
        PreparedStatement stmt = null;
        String update = "UPDATE booster set player_booster=? WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(2,uuid);
            stmt.setInt(1,amount);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//Ob Spieler den Booster freigeschalten hatt, dies könnte man noch hinzufgüen
 /*   public void setPlayerFreigeschalten(String uuid, double amount) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String update = "UPDATE booster set player_freigeschaltet=? FROM booster WHERE player_uuid=?";
        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(2, uuid);
            stmt.setDouble(1, amount);
            ResultSet rs = stmt.executeQuery();

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

*/

}
