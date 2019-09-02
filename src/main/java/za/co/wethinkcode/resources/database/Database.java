package za.co.wethinkcode.resources.database;

import za.co.wethinkcode.model.Hero;
import za.co.wethinkcode.view.console.Console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Database {
    private Connection conn;
    private String url;
    private String query;

    public void connect() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            url = "jdbc:sqlite:C:swingy.db";
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Database Error");
            System.out.println(e.getMessage());
        }finally {
            try {
                if (conn != null) {
                    createNewTable();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void createNewTable() {
        try {
            query = "CREATE TABLE IF NOT EXISTS hero(\n"
                    + " id integer PRIMARY KEY,\n"
                    + " name text NOT NULL,\n"
                    + " type text NOT NULL,\n"
                    + " level integer NOT NULL,\n"
                    + " xp integer NOT NULL,\n"
                    + " attack integer NOT NULL,\n"
                    + " defense integer NOT NULL,\n"
                    + " hitPoints integer NOT NULL,\n"
                    + " weaponType text NOT NULL,\n"
                    + " weaponValue integer NOT NULL,\n"
                    + " armorType text NOT NULL,\n"
                    + " armorValue integer NOT NULL,\n"
                    + " helmType text NOT NULL,\n"
                    + " helmValue integer NOT NULL\n"
                    + ");";

            if(conn != null) {
                Statement statement = conn.createStatement();
                statement.execute(query);
            }
        }catch (SQLException e){
            System.out.println("Database Error");
            System.out.println(e.getMessage());
        }
    }

    public void saveHero(Hero hero){
        query = "INSERT INTO hero(name, type, level, xp, attack, defense, hitPoints, weaponType, weaponValue, armorType, armorValue, helmType, helmValue) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hero.getName());
            pstmt.setString(2, hero.getType());
            pstmt.setInt(3, hero.getLevel());
            pstmt.setInt(4, hero.getXp());
            pstmt.setInt(5, hero.getAttackTmp());
            pstmt.setInt(6, hero.getDefenseTmp());
            pstmt.setInt(7, hero.getHitPointsTmp());
            pstmt.setString(8, hero.getWeapon().getType());
            pstmt.setInt(9, hero.getWeapon().getValue());
            pstmt.setString(10, hero.getArmor().getType());
            pstmt.setInt(11, hero.getArmor().getValue());
            pstmt.setString(12, hero.getHelm().getType());
            pstmt.setInt(13, hero.getHelm().getValue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("hi");
            System.out.println(e.getMessage());
        }
    }

    public void getId(Hero hero){
        query = "SELECT * FROM hero";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            while (rs.next()){
                hero.setId(rs.getInt("id"));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateHero(Hero hero){
        System.out.println("Updateing player");
        query = "UPDATE hero SET name = ?,"
                + "type = ? ,"
                + "level = ? ,"
                + "xp = ? ,"
                + "attack = ? ,"
                + "defense = ? ,"
                + "hitPoints = ? ,"
                + "weaponType = ? ,"
                + "weaponValue = ? ,"
                + "armorType = ? ,"
                + "armorValue = ? ,"
                + "helmType = ? ,"
                + "helmValue = ? "
                + "WHERE id = ?";

        try{
            Console console = new Console();
            console.displayHeroStats(hero);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hero.getName());
            pstmt.setString(2, hero.getType());
            pstmt.setInt(3, hero.getLevel());
            pstmt.setInt(4, hero.getXp());
            pstmt.setInt(5, hero.getAttackTmp());
            pstmt.setInt(6, hero.getDefenseTmp());
            pstmt.setInt(7, hero.getHitPointsTmp());
            pstmt.setString(8, hero.getWeapon().getType());
            pstmt.setInt(9, hero.getWeapon().getValue());
            pstmt.setString(10, hero.getArmor().getType());
            pstmt.setInt(11, hero.getArmor().getValue());
            pstmt.setString(12, hero.getHelm().getType());
            pstmt.setInt(13, hero.getHelm().getValue());
            pstmt.setInt(14, hero.getId());
            pstmt.executeUpdate();
            System.out.println("Player updated");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Hero getHeroById(int id){
        Hero hero = null;
        query = "SELECT * FROM hero WHERE id='" +id+"'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if(rs.next()){
                hero = new Hero(rs);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return hero;
    }

    public Connection getConn() {
        return conn;
    }

    public void closeConn(){
        try {
            conn.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
