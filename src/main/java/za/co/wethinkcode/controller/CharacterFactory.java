package za.co.wethinkcode.controller;

import org.apache.commons.lang3.StringUtils;
import za.co.wethinkcode.model.Hero;
import za.co.wethinkcode.model.Villain;
import za.co.wethinkcode.resources.database.Database;
import za.co.wethinkcode.view.console.Console;
import za.co.wethinkcode.resources.exceptions.InputErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class CharacterFactory {
    Hero hero;
    Villain villain;
    Console console;
    private String type;
    private String name;

    public Hero createCharacter(Database database){
        String weapon = null;
        String weapontemp;
        Integer weaponint;
        try {
            Console.newCharacterName();
            Scanner myObj = new Scanner(System.in);
            name = myObj.nextLine();

            Console.newCharacterType();
            String temp = myObj.nextLine();
            Integer i = Integer.parseInt(temp);
            if(i >= 1 && i <= 9){
                switch (i) {
                    case 1:
                        type = "Braavosian";
                        weapon = "Sword";
                        break;
                    case 2:
                        type = "Dothraki";
                        String[] DothrakiWeapons = {"Whips", "Scimitar"};
                        Console.selectWeapon(DothrakiWeapons);
                        weapontemp = myObj.nextLine();
                        weaponint = Integer.parseInt(weapontemp) - 1;
                        weapon = DothrakiWeapons[weaponint];
                        break;
                    case 3:
                        type = "GoldenCloaks";
                        weapon = "Sword";
                        break;
                    case 4:
                        type = "HighBorn";
                        String[] HighBornWeapons = {"Sword", "Bow and arrow"};
                        Console.selectWeapon(HighBornWeapons);
                        weapontemp = myObj.nextLine();
                        weaponint = Integer.parseInt(weapontemp) - 1;
                        weapon = HighBornWeapons[weaponint];
                        break;
                    case 5:
                        type = "Knight";
                        weapon = "Sword";
                        break;
                    case 6:
                        type = "Peasant";
                        weapon = "Dagger";
                        break;
                    case 7:
                        type = "SellSword";
                        String[] SellSwordWeapons = {"Dagger", "Sword"};
                        Console.selectWeapon(SellSwordWeapons);
                        weapontemp = myObj.nextLine();
                        weaponint = Integer.parseInt(weapontemp) - 1;
                        weapon = SellSwordWeapons[weaponint];
                        break;
                    case 8:
                        type = "Unsullied";
                        weapon = "Spear";
                        break;
                    case 9:
                        type = "Wildlings";
                        String[] WildlingWeapons = {"Axes", "Warhammer", "Sword", "Bow and Arrow"};
                        Console.selectWeapon(WildlingWeapons);
                        weapontemp = myObj.nextLine();
                        weaponint = Integer.parseInt(weapontemp) - 1;
                        weapon = WildlingWeapons[weaponint];
                        break;
                }

                hero = new Hero(name, type, weapon);
            }else {
                throw new InputErrorException("Input a number between 1 and 9");
            }

        }catch (InputErrorException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(beanValidates(hero) == false){
            System.out.println("FAILED");
            return null;
        }
        hero.saveHero(database);
        System.out.println("Saved hero");
        return hero;
    }

    public Hero selectCharacter(Database database){
        int id;
        console = new Console();
        id = console.selectHero(database);
        hero = database.getHeroById(id);
        if(hero == null || beanValidates(hero) == false){
            System.out.println("\n----------------------------------------\n");
            System.out.println("Input Error");
            System.out.println("\n----------------------------------------\n");
            selectCharacter(database);
        }
        return hero;
    }

    public Villain createVillian(){
        Random random = new Random();

        int n = random.nextInt(10) + 1;

        if(n % 8 == 0){
            villain = new Villain("Boss");

        }else if(n % 3 == 0){
            villain = new Villain("Special");
        }else{
            villain = new Villain("Normal");
        }
        return villain;
    }

    private boolean beanValidates(Hero hero) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);
        if (constraintViolations.size() > 0) {
            Set<String> violationMessages = new HashSet<String>();
            for (ConstraintViolation<Hero> constraintViolation : constraintViolations) {
                violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            }
            console = new Console();
            console.getMessage(StringUtils.join(violationMessages, "\n"));
            return false;
        }
        return true;
    }
}
