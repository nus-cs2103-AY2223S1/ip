package jarvis.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(">> " + message);
    }

    public void greet() {
        System.out.println(">> Hello! I am Jarvis! What can I do for you?");
    }
}
