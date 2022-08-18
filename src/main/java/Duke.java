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

            if(input.equals("bye")) {

                quit = true;
                chatBot.bye();

            } else {
                chatBot.echo(input);
            }
        }
    }
}
