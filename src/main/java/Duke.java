package main.java;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        while (!str.equalsIgnoreCase("bye")) {
            System.out.println(str + "\n");
            str= sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
