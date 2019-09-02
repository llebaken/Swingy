package za.co.wethinkcode.controller;

import lombok.Data;
import za.co.wethinkcode.model.Hero;
import za.co.wethinkcode.model.Villain;
import za.co.wethinkcode.resources.exceptions.InputErrorException;
import za.co.wethinkcode.view.console.Console;

import java.util.Random;
import java.util.Scanner;
@Data
public class Fight {
    Hero hero;
    Villain villain;
    private int result;

    public Fight(Hero hero, Villain villain){
        this.villain = villain;
        this.hero = hero;

        result = 0;

        while(result == 0){
            heroAttack();
            if(this.villain.getHitPoints() <= 0){
                result = 1;
                break;
            }
            villainAttack();
            if(this.hero.getHitPoints() <= 0){
                result = -1;
                break;
            }
            System.out.println("Hero health - " + this.hero.getHitPoints());
            System.out.println("Villain Health - " + this.villain.getHitPoints());
        }
    }

    public void heroAttack(){
        int damage;

        damage = hero.getAttack() - villain.getDefense();
        if(damage < 0) damage = 0;
        villain.setHitPoints(villain.getHitPoints() - damage);
    }

    public void villainAttack(){
        int damage;

        damage = villain.getAttack() - hero.getDefense();
        if(damage < 0) damage = 0;
        hero.setHitPoints(hero.getHitPoints() - damage);
    }

    public String getArtifact(){
        String[] artifacts = {"Armor", "Weapon", "Helm"};

        int random = new Random().nextInt(artifacts.length);
        return artifacts[random];
    }
}
