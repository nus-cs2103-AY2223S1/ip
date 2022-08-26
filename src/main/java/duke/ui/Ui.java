package duke.ui;

import java.util.Scanner;

public class Ui {
    
    public static Scanner scanner = new Scanner(System.in);
    
    public Ui() {}
    
    public void showWelcome() {
        System.out.println("Hi... I'm Bishop... \nWhat can I do for you today?");
    }
    
    public String readCommand() {
        return scanner.nextLine();
    } 
    
    public void showLine() {
        System.out.println("_____________________________");
    }
    
    public void showError(String message) {
        System.out.println(message);
    }
    
    public void showLoadingError() {
        System.out.println("Tasks could not be loaded in");
    }
}
