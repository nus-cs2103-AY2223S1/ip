import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
    private String emoji = "<_>";
    private Scanner reader = new Scanner(System.in);
    private String[] todos = new String[100];
    private int pointer = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke bot = new Duke();
        bot.greet();
        while (bot.isActive) {
            bot.respond();
        }
    }

    public void greet() {
        System.out.print("Hello! I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<\n\n");
    }

    public void respond() {
        String userInput = reader.nextLine();
        if (userInput.equals("bye")) {
            bye();
        } else if (userInput.equals("list")) {
            listTasks();
        } else {
           addTask(userInput);
        }

        System.out.println();
    }

    private void addTask(String task) {
        System.out.println("\tlazily added: " + task + " " + emoji);
        todos[pointer] = task;
        pointer++;
    }

    private void listTasks() {
        System.out.println("Really?");
        for (int i = 1; i <= pointer; i++) {
            System.out.println(i + ". " + todos[i - 1]);
        }
    }

    private void bye() {
        this.isActive = false;
        System.out.println("\tBye. zzz FINALLY~~" + " " + emoji);
        reader.close();
    }
}
