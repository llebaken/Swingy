package za.co.wethinkcode.model.map;

import lombok.Data;
import za.co.wethinkcode.controller.CharacterFactory;
import za.co.wethinkcode.model.Hero;
import za.co.wethinkcode.model.Villain;

import java.util.ArrayList;
import java.util.Random;
@Data
public class Map {
    private int mapSize;
    private char[][] gamemap;
    private int noVillains;
    private ArrayList<Coordinates> occupiedCoordinates = new ArrayList<>();
    private ArrayList<Villain> villains = new ArrayList<>();
    private CharacterFactory cf = new CharacterFactory();

    public Map(Hero hero){
        mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
        hero.setCoordinates(mapSize);
        occupiedCoordinates.add(hero.getCoordinates());
        initialiseArray();
        noVillains = (mapSize * mapSize) / 3;
        addVillains();
        occupiedCoordinates.remove(hero.getCoordinates());
    }

    private void initialiseArray(){
        gamemap = new char[mapSize][mapSize];

        for(int y = 0; y < mapSize; y++){
            for(int x = 0; x < mapSize; x++){
                gamemap[y][x] = '*';
            }
        }
    }

    private void addVillains(){
        Random random = new Random();
        int tempx;
        int tempy;
        for(int i = 0; i < noVillains; i++){
            tempx = random.nextInt(mapSize);
            tempy = random.nextInt(mapSize);
            Coordinates coordinates = new Coordinates(tempx, tempy);
            while(isOccupied(coordinates) == 1){
                tempx = random.nextInt(mapSize);
                tempy = random.nextInt(mapSize);
                coordinates = new Coordinates(tempx, tempy);
            }
            occupiedCoordinates.add(coordinates);
            Villain villain = cf.createVillian();
            villain.setCoordinates(coordinates);
            addVillainsToMap(villain);
            this.villains.add(villain);
        }
    }

    private void addVillainsToMap(Villain villain){
        gamemap[villain.getCoordinates().getY()][villain.getCoordinates().getX()] = '*';

    }

    public int isOccupied(Coordinates coordinates){
        int occupied = 0;
        int count = occupiedCoordinates.size();

        for(int i = 0; i < count; i++){
            Coordinates tempCoordinates = occupiedCoordinates.get(i);
            if((tempCoordinates.getX() == coordinates.getX()) && (tempCoordinates.getY() == coordinates.getY())){
                occupied = 1;
            }
        }
        return occupied;
    }

    public Villain getVillian(Coordinates coordinates){
        int count = villains.size();
        Villain currentVillian = null;

        for(int i = 0; i < count; i++){
            Villain villain = villains.get(i);
            if((villain.getCoordinates().getX() == coordinates.getX()) && (villain.getCoordinates().getY() == coordinates.getY())){
                currentVillian = villain;
            }
        }
        return currentVillian;
    }

    public void villainKilled(Villain villain){
        int x = villain.getCoordinates().getX();
        int y = villain.getCoordinates().getY();

        gamemap[y][x] = '*';
        occupiedCoordinates.remove(villain.getCoordinates());
        villains.remove(villain);
    }

    public int checkLevel(Hero hero){
        int i = 0;
        int tempLevel = hero.getLevel() + 1;
        int tempXP = tempLevel * 1000 + (tempLevel - 1) * 450;

        if(hero.getXp() >= tempXP){
            i = 1;
            hero.levelUp();
        }
        return i;
    }

    public void isVillainGone(Villain villain){
        Random random = new Random();
        int i = random.nextInt(2);
        if(i == 1){
            villainKilled(villain);
        }
    }
}
