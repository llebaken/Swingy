package za.co.wethinkcode;

import za.co.wethinkcode.controller.ConsoleController;
//import za.co.wethinkcode.controller.GuiController;
import za.co.wethinkcode.controller.GuiController;
import za.co.wethinkcode.resources.database.Database;
import za.co.wethinkcode.resources.exceptions.InputErrorException;

import java.util.Scanner;
import java.io.*;
public class App 
{
    public static void main( String[] args ) {
        if (args.length == 1) {
            try {

                if (args[0].equals("console")) {
                    ConsoleController consoleController = new ConsoleController();
                    consoleController.startGame();
                } else if (args[0].equals("gui")) {
                    GuiController guiController = new GuiController();
                    guiController.startGame();
                } else {
                    throw new InputErrorException("Incorrect argument");
                }
            } catch (InputErrorException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("No arguments");
        }
    }
}
