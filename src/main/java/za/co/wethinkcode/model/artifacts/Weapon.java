package za.co.wethinkcode.model.artifacts;

import lombok.Data;
@Data
public class Weapon {
    private int value;
    private String type;

    public Weapon(String type, int value){
        setType(type);
        setValue(value);
    }

    public Weapon(String weapon){
        switch (weapon){
            case "Sword":
                setValue(20);
                setType(weapon);
                break;
            case "Spear":
                setValue(17);
                setType(weapon);
                break;
            case "Whips":
                setValue(10);
                setType(weapon);
                break;
            case "Scimitar":
                setValue(25);
                setType(weapon);
                break;
            case "Bow and arrow":
            case "Axes":
            case "Valyrian Steel Dagger":
                setValue(15);
                setType(weapon);
                break;
            case "Dagger":
                setValue(5);
                setType(weapon);
                break;
            case "Drogon":
                setValue(80);
                setType(weapon);
                break;
            case "Longclaw":
                setValue(60);
                setType(weapon);
                break;
            case "Needle":
                setValue(30);
                setType(weapon);
                break;
            case "Oathkeeper":
                setValue(50);
                setType(weapon);
                break;
            case "Dragonglass Steel Spear":
                setValue(35);
                setType(weapon);
                break;
            case "Valyrian Steel Spear":
                setValue(27);
                setType(weapon);
                break;
            case "Dual Swords":
                setValue(40);
                setType(weapon);
                break;
            case "Ice Sword":
                setValue(45);
                setType(weapon);
                break;
        }

    }
}
