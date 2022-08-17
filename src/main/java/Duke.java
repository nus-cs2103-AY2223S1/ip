import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
    private String emoji = "<_>";
    private Scanner reader = new Scanner(System.in);

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
        } else {
            System.out.println(userInput + " " + emoji);
        }

        System.out.println();
    }

    private void bye() {
        this.isActive = false;
        System.out.println("Bye. zzz FINALLY~~" + " " + emoji);
        reader.close();
    }
}
