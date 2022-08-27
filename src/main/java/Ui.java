import java.util.Scanner;

// deals with interactions with the user
public class Ui {

    private Scanner s;

    public void start() {
        // Creating the scanner to get input
        this.s = new Scanner(System.in);
    }

    public String getInput() {
        return s.nextLine();
    }

    public void end() {
        s.close();
    }

    public void greet() {
        String logo = " ____                 \n"
                + "|  _ \\ _ _ _ __ _____ \n"
                + "| | | |  _  | |/ / _ \\\n"
                + "| |_| | |_| |   /  __/\n"
                + "|____/ \\__,_|\\_/ \\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        Duke.printLine();
    }

}
