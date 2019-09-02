package za.co.wethinkcode.model.artifacts;

import lombok.Data;
@Data
public class Helm {
    private int value;
    private String type;

    public Helm(String type, int value){
        setType(type);
        setValue(value);
    }

    public Helm(String type){
        switch (type){
            case "Braavosian":
            case "HighBorn":
            case "Dothraki":
            case "Peasant":
            case "SellSword":
            case "Wildlings":
                setValue(0);
                setType("None");
                break;
            case "GoldenCloaks":
                setValue(20);
                setType("Cloaks helm");
                break;
            case "Knight":
                setValue(15);
                setType("Cloaks helm");
                break;
            case "Unsullied":
                setValue(15);
                setType("Unsullied helm");
                break;
            case "The Hound":
                setValue(35);
                setType("Hound Helm");
                break;

        }

    }
}
