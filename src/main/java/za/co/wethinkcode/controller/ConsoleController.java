package za.co.wethinkcode.controller;

import za.co.wethinkcode.model.Hero;
import za.co.wethinkcode.model.Villain;
import za.co.wethinkcode.resources.database.Database;
import za.co.wethinkcode.resources.exceptions.InputErrorException;
import za.co.wethinkcode.view.console.Console;
import za.co.wethinkcode.model.map.Map;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
public class ConsoleController {
    Hero hero;
    Database database;
    CharacterFactory cf;
    Console console;
    Fight fight;
    private int gameloop;
    private int i;
    private String artifact;

    public void startGame(){
        cf = new CharacterFactory();
        console = new Console();
        try{
            database = new Database();
            database.connect();
            i = console.startScreen();
            if(i == 1){
                hero = cf.createCharacter(database);
                if(hero == null){
                    startGame();
                }
            }
            else if(i == 2){
                hero = cf.selectCharacter(database);
                if(hero == null){
                    startGame();
                }
            }
            else if (i == 3){
                database.closeConn();
                System.exit(0);
            }

            console.displayHeroStats(hero);
            i = console.startgame();

            if(i == 1){
                playGame();
            }else if(i == 2){
                startGame();
            }else if(i == 3){
                database.closeConn();
                System.exit(0);
            }
        }
        catch (InputErrorException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void playGame(){
        gameloop = 1;
        try {
            while (gameloop == 1) {
                Map map = new Map(hero);
                while ((hero.getCoordinates().getX() < map.getMapSize() && hero.getCoordinates().getX() >= 0) && (hero.getCoordinates().getY() < map.getMapSize() && hero.getCoordinates().getY() >= 0)) {
                    console.displayMap(map.getGamemap(), map.getMapSize(), hero.getCoordinates().getX(), hero.getCoordinates().getY());
                    if (map.isOccupied(hero.getCoordinates()) != 1) {
                        i = console.move();

                        if (i == 1) {
                            hero.getCoordinates().moveUp();
                        } else if (i == 2) {
                            hero.getCoordinates().moveRight();
                        } else if (i == 3) {
                            hero.getCoordinates().moveDown();
                        } else if (i == 4) {
                            hero.getCoordinates().moveLeft();
                        } else if (i == 5) {
                            hero.updateHero(database);
                            gameloop = 0;
                            database.closeConn();
                            break;
                        }
                    }

                    if (map.isOccupied(hero.getCoordinates()) == 1) {
                        Villain villain = map.getVillian(hero.getCoordinates());
                        Console.displayHeroStats(hero);
                        console.enemyStats(villain);
                        if(villain.getFound() == 1){
                            i = 1;
                            System.out.println("Enemy has spotted you twice, you are forced to fight");
                        }else {
                            i = console.enemyFound();
                        }
                        if (i == 1) {
                            fight = new Fight(hero, villain);
                            if (fight.getResult() == -1) {
                                console.fightLost();
                                gameloop = 0;
                                break;
                            } else if (fight.getResult() == 1) {
                                artifact = fight.getArtifact();
                                switch (artifact) {
                                    case "Armor":
                                        if (villain.getArmor().getType() == "None") {
                                            console.noArtifact();
                                        } else {
                                            i = console.artifactFound(villain, "Armor");
                                            if (i == 1) {
                                                hero.updateArmor(villain.getArmor());
                                                Console.displayHeroStats(hero);
                                            }
                                        }
                                        break;
                                    case "Weapon":
                                        if (villain.getWeapon().getType() == "None") {
                                            Console.noArtifact();
                                        } else {
                                            i = console.artifactFound(villain, "Weapon");
                                            if (i == 1) {
                                                hero.updateWeapon(villain.getWeapon());
                                                Console.displayHeroStats(hero);
                                            }
                                        }
                                        break;
                                    case "Helm":
                                        if (villain.getHelm().getType() == "None") {
                                            Console.noArtifact();
                                        } else {
                                            i = console.artifactFound(villain, "Helm");
                                            if (i == 1) {
                                                hero.updateHelm(villain.getHelm());
                                                Console.displayHeroStats(hero);
                                            }
                                        }
                                        break;
                                }
                                hero.updateXp(villain);
                                map.villainKilled(villain);
                                hero.recoverHealth();
                                if (map.checkLevel(hero) == 1) {
                                    hero.updateHero(database);
                                    break;
                                }
                                hero.updateHero(database);
                            }
                        } else if (i == 2) {
                            villain.setFound(1);
                            map.isVillainGone(villain);
                            hero.getCoordinates().moveRight();
                        } else {
                            throw new InputErrorException("Input must be 1 or 2");
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
