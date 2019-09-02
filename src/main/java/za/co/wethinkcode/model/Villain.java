package za.co.wethinkcode.model;

import lombok.Data;
import za.co.wethinkcode.model.artifacts.Armor;
import za.co.wethinkcode.model.artifacts.Helm;
import za.co.wethinkcode.model.artifacts.Weapon;
import za.co.wethinkcode.model.map.Coordinates;

import java.util.Random;
@Data
public class Villain {
    private String type;
    private int attack;
    private int defense;
    private int hitPoints;
    private Weapon weapon;
    private Armor armor;
    private Helm helm;
    private int found = 0;
    private char villainSymbol;
    private Coordinates coordinates;

    public Villain(String type){
        switch (type) {
            case "Boss":
                createBoss();
                villainSymbol = 'B';
                break;
            case "Special":
                createSpecial();
                villainSymbol = 'S';
                break;
            case "Normal":
                createNormal();
                villainSymbol = 'N';
                break;
        }

    }

    public void createBoss(){
        Random random = new Random();
        String[] Boss = {"Daenerys Targaryen", "Jon Snow", "Arya Stark", "Brienne", "The Night King"};
        int rnd = random.nextInt(Boss.length);
        this.type = Boss[rnd];

        switch (this.type) {
            case "Daenerys Targaryen":
                weapon = new Weapon("Drogon");
                armor = new Armor("HighBorn");
                helm = new Helm("HighBorn");
                this.attack = 30 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
            case "Jon Snow":
                weapon = new Weapon("Longclaw");
                armor = new Armor("HighBorn");
                helm = new Helm("HighBorn");
                this.attack = 30 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 200 + helm.getValue();
                break;
            case "Arya Stark":
                weapon = new Weapon("Needle");
                armor = new Armor("HighBorn");
                helm = new Helm("HighBorn");
                this.attack = 30 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 200 + helm.getValue();
                break;
            case "Brienne":
                weapon = new Weapon("Oathkeeper");
                armor = new Armor("GoldenCloaks");
                helm = new Helm("GoldenCloaks");
                this.attack = 30 + weapon.getValue();
                this.defense = 35 + armor.getValue();
                this.hitPoints = 200 + helm.getValue();
                break;
            case "The Night King":
                weapon = new Weapon("Ice Sword");
                armor = new Armor("Knight");
                helm = new Helm("SellSword");
                this.attack = 38 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 200 + helm.getValue();
                break;
        }
    }

    public void createSpecial(){
        Random random = new Random();
        String[] Special = {"Khal Drogo", "The Hound", "Grey Worm",  "Daario", "Oberyn Martell", "Ser Arthur Dayne"};
        int rnd = random.nextInt(Special.length);
        this.type = Special[rnd];

        switch (this.type) {
            case "Khal Drogo":
                weapon = new Weapon("Scimitar");
                armor = new Armor("Dothraki");
                helm = new Helm("Dothraki");
                this.attack = 30 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
            case "The Hound":
                weapon = new Weapon("Sword");
                armor = new Armor("Knight");
                helm = new Helm("The Hound");
                this.attack = 30 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
            case "Grey Worm":
                weapon = new Weapon("Dragonglass Steel Spear");
                armor = new Armor("Unsullied");
                helm = new Helm("Unsullied");
                this.attack = 40 + weapon.getValue();
                this.defense = 40 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
            case "Daario":
                weapon = new Weapon("Valyrian Steel Dagger");
                armor = new Armor("Braavosian");
                helm = new Helm("Braavosian");
                this.attack = 40 + weapon.getValue();
                this.defense = 40 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
            case "Oberyn Martell":
                weapon = new Weapon("Valyrian Steel Spear");
                armor = new Armor("Braavosian");
                helm = new Helm("Braavosian");
                this.attack = 38 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
            case "Ser Arthur Dayne":
                weapon = new Weapon("Dual Swords");
                armor = new Armor("Knight");
                helm = new Helm("Knight");
                this.attack = 39 + weapon.getValue();
                this.defense = 30 + armor.getValue();
                this.hitPoints = 150 + helm.getValue();
                break;
        }
    }

    public void createNormal(){
        Random random = new Random();
        String[] Normal = {"Knight", "SellSword", "Thief"};
        int rnd = random.nextInt(Normal.length);
        this.type = Normal[rnd];

        switch (this.type) {
            case "Knight":
                weapon = new Weapon("Sword");
                armor = new Armor("Knight");
                helm = new Helm("Knight");
                this.attack = 20 + weapon.getValue();
                this.defense = 20 + armor.getValue();
                this.hitPoints = 100 + helm.getValue();
                break;
            case "SellSword":
                weapon = new Weapon("Sword");
                armor = new Armor("SellSword");
                helm = new Helm("SellSword");
                this.attack = 15 + weapon.getValue();
                this.defense = 15 + armor.getValue();
                this.hitPoints = 100 + helm.getValue();
                break;
            case "Thief":
                weapon = new Weapon("Dagger");
                armor = new Armor("Peasant");
                helm = new Helm("Peasant");
                this.attack = 5 + weapon.getValue();
                this.defense = 5 + armor.getValue();
                this.hitPoints = 100 + helm.getValue();
                break;
        }
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
}
