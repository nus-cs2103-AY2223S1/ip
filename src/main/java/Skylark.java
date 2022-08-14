import java.util.ArrayList;
import java.util.Scanner;

public class Skylark {

    private static final String line = "________________________________________________";

    private static void printText(String text) {
        System.out.println(Skylark.line);
        System.out.println(text);
        System.out.println(Skylark.line);
    }

    private static void printText(ArrayList<String> text) {
        System.out.println(Skylark.line);
        for (int i = 0; i < text.size(); i++) {
            System.out.println((i + 1) + ". "+ text.get(i));
        }
        System.out.println(Skylark.line);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        String byeCommand = "bye";
        String listCommand = "list";
        String command;

        Skylark.printText("Hello, I am Skylark, how can I help you today?");

        while (true) {
            command = scan.nextLine();
            if (command.equals(byeCommand)) {
                Skylark.printText("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals(listCommand)) {
                Skylark.printText(taskList);
            } else {
                taskList.add(command);
                Skylark.printText("added: " + command);
            }
        }
    }
}
