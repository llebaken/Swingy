package za.co.wethinkcode.model.map;

import lombok.Data;

@Data
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveUp(){
        this.y--;
    }

    public void moveDown(){
        this.y++;
    }

    public void moveRight(){
        this.x++;
    }

    public void moveLeft(){
        this.x--;
    }
}
