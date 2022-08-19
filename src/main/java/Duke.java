import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ChatBot chatBot = new ChatBot("duke");

        chatBot.greet();

        boolean quit = false;
        String input;


        while(!quit) {

            input = sc.next();

            switch (input) {

                case "bye":
                    quit = true;
                    chatBot.bye();
                    break;
                case "list":
                    chatBot.printTasks();
                    break;
                case "mark":
                    chatBot.markDone(sc.nextInt() - 1);
                    sc.nextLine();
                    break;
                case "unmark":
                    chatBot.markUndone(sc.nextInt() - 1);
                    sc.nextLine();
                    break;
                default:
                    chatBot.addTask(input + sc.nextLine());

            }
        }
    }
}
