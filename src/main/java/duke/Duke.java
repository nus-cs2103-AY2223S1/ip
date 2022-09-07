package duke;

import java.util.Scanner;

import duke.chatbot.ChatBot;

/**
 * Duke class that runs the main program.
 */
public class Duke {
    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot christina = new ChatBot("Christina");
        christina.initialize();
        while (christina.isRunning()) {
            christina.processCommand(scanner.nextLine());
        }
        scanner.close();
        christina.terminate();
    }
}
