package za.co.wethinkcode.model;

import lombok.Data;
import za.co.wethinkcode.model.artifacts.Armor;
import za.co.wethinkcode.model.artifacts.Weapon;
import za.co.wethinkcode.model.artifacts.Helm;
import za.co.wethinkcode.model.map.Coordinates;
import za.co.wethinkcode.resources.database.Database;

import javax.validation.constraints.Size;
import java.sql.ResultSet;
import java.sql.SQLException;
@Data
public class Hero {
    @Size(min = 1)
    private String name;
    private String type;
    private int level;
    private int xp;
    private int attack;
    private int attackTmp;
    private int defense;
    private int defenseTmp;
    private int hitPoints;
    private int hitPointsTmp;
    private Weapon weapon;
    private Armor armor;
    private Helm helm;
    private Coordinates coordinates;
    private int id;

    public Hero(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.name = rs.getString("name");
            this.type = rs.getString("type");
            this.level = rs.getInt("level");
            this.xp = rs.getInt("xp");
            this.attackTmp = rs.getInt("attack");
            this.defenseTmp = rs.getInt("defense");
            this.hitPointsTmp = rs.getInt("hitPoints");
            weapon = new Weapon(rs.getString("weaponType"), rs.getInt("weaponValue"));
            armor = new Armor(rs.getString("armorType"), rs.getInt("armorValue"));
            helm = new Helm(rs.getString("helmType"), rs.getInt("helmValue"));
            this.defense = defenseTmp + this.armor.getValue();
            this.attack = this.attackTmp + weapon.getValue();
            this.hitPoints = this.hitPointsTmp + helm.getValue();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Hero(String name, String type, String weaponchoice){
        this.name = name;
        this.type = type;
        this.level = 0;
        this.xp = 0;
        armor = new Armor(type);
        weapon = new Weapon(weaponchoice);
        helm = new Helm(type);

        switch (this.type){
            case "Braavosian":
            case "Unsullied":
                this.attackTmp = 30;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 30;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "Dothraki":
                this.attackTmp = 40;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 20;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "GoldenCloaks":
                this.attackTmp = 20;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 20;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "HighBorn":
                this.attackTmp = 10;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 10;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "Knight":
                this.attackTmp = 12;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 12;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "Peasant":
                this.attackTmp = 5;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 5;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "SellSword":
                this.attackTmp = 15;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 15;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
            case "Wildlings":
                this.attackTmp = 25;
                this.attack = this.attackTmp + weapon.getValue();
                this.defenseTmp = 13;
                this.defense = this.defenseTmp + armor.getValue();
                this.hitPointsTmp = 100;
                this.hitPoints = this.hitPointsTmp + helm.getValue();
                break;
        }

    }

    public void setCoordinates(int mapSize){
        int px = mapSize / 2;
        int py = mapSize / 2;

        coordinates = new Coordinates(px, py);
    }

    public void updateArmor(Armor armor){
        this.armor = armor;
    }

    public void updateWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void updateHelm(Helm helm){
        this.helm = helm;
    }

    public void recoverHealth(){
        this.hitPoints = this.hitPointsTmp + helm.getValue();
    }

    public void saveHero(Database database){
        database.saveHero(this);
        database.getId(this);
    }

    public void updateHero(Database database){
        database.updateHero(this);
    }

    public void updateXp(Villain villain){
        switch (villain.getVillainSymbol()) {
            case 'B':
                xp = xp + 1000;
                break;
            case 'S':
                xp = xp + 500;
                break;
            case 'N':
                xp = xp + 250;
                break;
        }
    }

    public void levelUp(){
        level++;
    }
}
