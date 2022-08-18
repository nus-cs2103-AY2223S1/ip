import java.awt.desktop.SystemEventListener;
import java.util.*;
public class Duke {

    private static List<Task> todoList;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to UNC\n");

        todoList = new ArrayList<>();

        String input;
        Scanner scanner = new Scanner(System.in);
        while(!Objects.equals(input = scanner.nextLine(), "bye")) {
            String[] words = input.split(" ", 2);
            switch(words[0]) {
                case "list":
                    displayList();
                    break;
                case "mark":
                    mark(Integer.parseInt(words[1]));
                    break;
                case "unmark":
                    unmark(Integer.parseInt(words[1]));
                    break;

                default:
                    addToList(input);

            }
        }


        System.out.println("Bye");
    }

    private static void displayList() {
        for(int i = 0; i < todoList.size(); i++) {
            System.out.println(i + 1 + ". " + todoList.get(i));
        }

    }

    private static void addToList(String input) {
        System.out.println("added: " + input);
        todoList.add(new Task(input));
    }

    private static void mark(int index) {
        todoList.get(index - 1).markAsDone();
        System.out.println("Marked as done: \n" + todoList.get(index - 1));
    }

    private static void unmark(int index) {
        todoList.get(index - 1).markAsNotDone();
        System.out.println("Marked as not done: \n" + todoList.get(index - 1));
    }

}
