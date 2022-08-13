package duke;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
public class Duke {

    private static TaskList tasks;
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";




    public Duke() {
        tasks = Storage.load();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    public static void run() {
        Ui.WelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                Ui.DisplayMessage(ENDING_MESSAGE);
                break;
            }
            //if not, parser can parse data
            Parser.ParseData(input, tasks);
        }
    }


}

