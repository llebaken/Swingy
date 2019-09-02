package za.co.wethinkcode.view.console;

import za.co.wethinkcode.model.Hero;
import za.co.wethinkcode.model.Villain;
import za.co.wethinkcode.resources.database.Database;
import za.co.wethinkcode.resources.exceptions.InputErrorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Console {
    Scanner myObj = new Scanner(System.in);
    String temp;
    Integer i;
    public int startScreen(){
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("|***************************************|");
            System.out.println("|***************************************|");
            System.out.println("|*****           SWINGY            *****|");
            System.out.println("|***************************************|");
            System.out.println("|***************************************|");
            System.out.println("----------------------------------------\n");
            System.out.println("1) Create a character");
            System.out.println("2) Select a character");
            System.out.println("3) Quit");

            temp = myObj.nextLine();
            i  = Integer.parseInt(temp);
            if(i >= 1 && i <=3){
                break;
            }else{
                System.out.println("\n----------------------------------------\n");
                System.out.println("Input Error");
                System.out.println("\n----------------------------------------\n");
            }
        }
        return i;
    }

    public static void newCharacterName(){
        System.out.println("\n----------------------------------------\n");
        System.out.println("Enter Character Name");
    }

    public static void newCharacterType(){
        System.out.println("\n----------------------------------------\n");
        System.out.println("Select Character Type (Enter Character Value)");
        System.out.println("1) Braavosian");
        System.out.println("2) Dothraki");
        System.out.println("3) GoldenCloaks");
        System.out.println("4) HighBorn");
        System.out.println("5) Knight");
        System.out.println("6) Peasant");
        System.out.println("7) SellSword");
        System.out.println("8) Unsullied");
        System.out.println("9) Wildlings");
    }

    public static void selectWeapon(String[] weapons){
        System.out.println("\n----------------------------------------\n");
        int i;
        int no;
        System.out.println("Select Weapon");
        for (i = 0; i < weapons.length; i++){
            no = i + 1;
            System.out.println(no + ") " + weapons[i]);
        }
    }

    public int selectHero(Database database){
        System.out.println("\n----------------------------------------\n");
        String query = "SELECT * FROM hero";
        i = 0;
        try {
            Statement statement = database.getConn().createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                System.out.println("Enter Hero Id");
                System.out.print("Id = " + rs.getInt("id") + "\t");
                System.out.print("Name = " + rs.getString("name") + "\t");
                System.out.print("Type = " + rs.getString("type") + "\t");
                System.out.print("Level = " + rs.getInt("level") + "\t");
                System.out.print("Xp = " + rs.getInt("xp") + "\t");
                System.out.println();
                while (rs.next()) {
                    System.out.print("Id = " + rs.getInt("id") + "\t");
                    System.out.print("Name = " + rs.getString("name") + "\t");
                    System.out.print("Type = " + rs.getString("type") + "\t");
                    System.out.print("Level = " + rs.getInt("level") + "\t");
                    System.out.print("Xp = " + rs.getInt("xp") + "\t");
                    System.out.println();
                }
                temp = myObj.nextLine();
                i = Integer.parseInt(temp);
            }
            else{
                System.out.println("No characters to be displayed");
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public static void displayHeroStats(Hero hero){
        System.out.println("\n----------------------------------------\n");
//        String name = hero.name;
//        String type = hero.type;
//        int level = hero.level;
//        int xp = hero.xp;
//        int attack = hero.attack;
//        int defense = hero.defense;
//        int hitPoints = hero.hitPoints;

        System.out.println("Name - " + hero.getName());
        System.out.println("Type - " + hero.getType());
        System.out.println("Level - " + hero.getLevel());
        System.out.println("xp - " + hero.getXp());
        System.out.println("attack - " + hero.getAttack());
        System.out.println("defense - " + hero.getDefense());
        System.out.println("hitPoints - " + hero.getHitPoints());
        System.out.println("Armor - " + hero.getArmor().getType() + "(" + hero.getArmor().getValue() + ")");
        System.out.println("Helm - " + hero.getHelm().getType() + "(" + hero.getHelm().getValue() + ")");
        System.out.println("Weapon - " + hero.getWeapon().getType() + "(" + hero.getWeapon().getValue() + ")");

    }

    public int startgame(){
        while(true) {
            System.out.println("\n----------------------------------------\n");
            System.out.println("Would you like to:");
            System.out.println("1) Start Game");
            System.out.println("2) Go Back");
            System.out.println("3) Quit");

            temp = myObj.nextLine();
            i  = Integer.parseInt(temp);
            if(i >= 1 && i <= 3){
                break;
            }else{
                System.out.println("\n----------------------------------------\n");
                System.out.println("Input Error");
                System.out.println("\n----------------------------------------\n");
            }
        }
        return i;
    }

    public static void displayMap(char[][] map, int mapSize, int posX, int posY){
        System.out.println("\n----------------------------------------\n");
        for(int y = 0; y < mapSize; y++){
            for(int x = 0; x < mapSize; x++){
                if(y == posY && x == posX){
                    System.out.print("H");
                }else {
                    System.out.print(map[y][x]);
                }
            }
            System.out.println();
        }
    }

    public int move(){
        while(true) {
            System.out.println("\n----------------------------------------\n");
            System.out.println("Which direction would you like to move:");
            System.out.println("1) North");
            System.out.println("2) East");
            System.out.println("3) South");
            System.out.println("4) West");
            System.out.println("5) Quit");

            temp = myObj.nextLine();
            i  = Integer.parseInt(temp);
            if(i >= 1 && i <= 5){
                break;
            }else{
                System.out.println("\n----------------------------------------\n");
                System.out.println("Input Error");
                System.out.println("\n----------------------------------------\n");
            }
        }
        return i;
    }

    public int enemyFound(){
        while(true) {
            System.out.println("Would you like to:");
            System.out.println("1) Fight");
            System.out.println("2) Run");

            temp = myObj.nextLine();
            i  = Integer.parseInt(temp);
            if(i >= 1 && i <= 2){
                break;
            }else{
                System.out.println("\n----------------------------------------\n");
                System.out.println("Input Error");
                System.out.println("\n----------------------------------------\n");
            }
        }
        return i;
    }

    public void enemyStats(Villain villain){
        System.out.println("\n----------------------------------------\n");
        System.out.println("You have encountered Villian.");
        System.out.println("Type - " + villain.getType());
        System.out.println("attack - " + villain.getAttack());
        System.out.println("defense - " + villain.getDefense());
        System.out.println("hitPoints - " + villain.getHitPoints());
        System.out.println("Armor - " + villain.getArmor().getType() + "(" + villain.getArmor().getValue() + ")");
        System.out.println("Helm - " + villain.getHelm().getType() + "(" + villain.getHelm().getValue() + ")");
        System.out.println("Weapon - " + villain.getWeapon().getType() + "(" + villain.getWeapon().getValue() + ")");
        System.out.println("\n----------------------------------------\n");
    }

    public static void fightLost(){
        System.out.println("\n----------------------------------------\n");
        System.out.println("You lost the fight GAME OVER.");
        System.out.println("\n----------------------------------------\n");
    }

    public static void noArtifact(){
        System.out.println("\n----------------------------------------\n");
        System.out.println("You have won the fight but no artifact was found");
    }

    public int artifactFound(Villain villain, String artifact){
        System.out.println("\n----------------------------------------\n");
        System.out.println("You have won the fight and found a new artifact");
        switch (artifact) {
            case "Armor":
                System.out.println(villain.getArmor().getType() + "(" + villain.getArmor().getValue() +")" );
                break;
            case "Weapon":
                System.out.println(villain.getWeapon().getType() + "(" + villain.getWeapon().getValue() +")" );
                break;
            case "Helm":
                System.out.println(villain.getHelm().getType() + "(" + villain.getHelm().getValue() +")" );
                break;
        }
        while(true) {
            System.out.println("Would you like to:");
            System.out.println("1) Take");
            System.out.println("2) Leave behind");

            temp = myObj.nextLine();
            i  = Integer.parseInt(temp);
            if(i >= 1 && i <= 2){
                break;
            }else{
                System.out.println("\n----------------------------------------\n");
                System.out.println("Input Error");
                System.out.println("\n----------------------------------------\n");
            }
        }
        return i;
    }

    public void getMessage(String message){
        System.out.println(message);
    }
}
