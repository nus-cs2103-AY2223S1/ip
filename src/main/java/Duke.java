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

            input = sc.nextLine();

            if(input.equals("bye")) {

                quit = true;
                chatBot.bye();
            } else if (input.equals("list")) {
                chatBot.printTasks();
            } else {
                chatBot.addTask(input);
            }
        }
    }
}
