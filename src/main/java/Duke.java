import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private boolean hasExited = false;
    private ArrayList<String> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public void chat() {
        greetUser();
        Scanner sc = new Scanner(System.in);
        while(!hasExited) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                hasExited = true;
            } else if (input.equals("list")) {
                printList();
            } else {
                echo(input);
                addToList(input);
            }
        }
        exitMessage();
    }

    public void addToList(String input) {
        this.list.add(input);
    }

    public void printList() {
        generateLine();
        for (int i = 0; i < this.list.size(); i++) {
            String currLine = "\t " + (i + 1) + ". " + this.list.get(i);
            System.out.println(currLine);
        }
        generateLine();
    }

    public void greetUser() {
        generateLine();
        System.out.println("\t Hello! I'm Zeus");
        System.out.println("\t What can I do for you?");
        generateLine();
    }

    public void exitMessage() {
        generateLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        generateLine();
    }

    public void echo(String input) {
        generateLine();
        System.out.println("\t added: " + input);
        generateLine();

    }

    public void generateLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke zeus = new Duke();
        zeus.chat();
    }
}
