package pikachu;

import java.util.Scanner;

public class Ui {

    final static String HORIZON = "____________________________________________________________\n";
    Scanner sc = new Scanner(System.in);
    
    static String withFormat(String str) {
        return HORIZON + str + '\n' + HORIZON;
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void showLine() {
        System.out.println(HORIZON);
    }

    public void bye() {
        System.out.println("Pi-ka...");
    }

    public void intro() {
        System.out.println(withFormat("Pika Pikachu! (I am Pikachu!)\nPi-ka-chu?(Do you need any help?)"));
    }

    public void showLoadingError() {
        System.out.println("Pi? Pikapi!");
    }

    public void showError(String error) {
        System.out.println(error);
    }
 }
