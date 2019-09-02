package za.co.wethinkcode.model.artifacts;

import lombok.Data;
@Data
public class Armor {
    private int value;
    private String type;

    public Armor(String type, int value){
        setType(type);
        setValue(value);
    }

    public Armor(String type){
        switch (type){
            case "Braavosian":
                setValue(10);
                setType("Leather");
                break;
            case "Dothraki":
                setValue(5);
                setType("Light Leather");
                break;
            case "GoldenCloaks":
                setValue(25);
                setType("Plate Armor");
                break;
            case "HighBorn":
            case "Knight":
                setValue(15);
                setType("Strong leather with plate armor");
                break;
            case "Peasant":
            case "Wildlings":
                setValue(0);
                setType("None");
                break;
            case "SellSword":
                setValue(10);
                setType("Chain mail and Strong Leather");
                break;
            case "Unsullied":
                setValue(15);
                setType("Leather and Metal");
                break;
        }

    }
}
