package Duke;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private final Scanner sc;



    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public List<String> showLogo() {
        ArrayList<String> text = new ArrayList<>();
        String logo = (" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n");
        text.add("Hello from\n" + logo);
        text.add("___________________________________");
        text.add("Hello! I'm Duke.Duke\n What can I do for you?");
        text.add("____________________________________");
        return text;
    }

    public List<String> chatBox(String text) {
        return chatBox(List.of(text));
    }

    public List<String> chatBox(List<String> lines) {
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            result.add("   " + line);
        }
        return result;
    }

    public String readInput() {
        return sc.nextLine().trim();
    }

    public void printLine(Object line) {
        System.out.println(line);
    }

    public String printBye() {
       return ("Bye. Hope to see you again soon!");
    }

    public void printDash() {
        System.out.println("___________________________________");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}


