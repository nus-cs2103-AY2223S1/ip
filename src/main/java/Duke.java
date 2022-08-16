package main.java;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        Task[] storage = new Task[100];
        int counter = 0;
        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.print(i+1);
                    System.out.println(". " + storage[i].getTaskDescription() + "\n");
                }
            } else {
                System.out.println("Task added: " + str + "\n");
                storage[counter] = new Task(str);
                counter++;
            }
            str= sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
