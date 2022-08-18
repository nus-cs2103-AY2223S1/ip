import java.util.*;
public class Duke {

    private static List<Task> taskList;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to UNC\n");

        taskList = new ArrayList<>();

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
                case "todo":
                    addToDo(words[1]);
                    break;
                case "deadline":
                    addDeadline(words[1]);
                    break;
                case "event":
                    addEvent(words[1]);
                    break;
                default:
                    addToList(input);


            }
        }

        System.out.println("Bye");
    }

    private static void displayList() {
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    private static void addToList(String input) {
        System.out.println("added: " + input);
        taskList.add(new Task(input));
    }

    private static void addToDo(String input) {
        Todo newTodo = new Todo(input);
        taskList.add(newTodo);
        System.out.println("added: \n " + newTodo + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    private static void addDeadline(String input) {
        String[] phrases = input.split(" /by ", 2);
        Deadline newDeadline = new Deadline(phrases[0], phrases[1]);
        taskList.add(newDeadline);
        System.out.println("added: \n " + newDeadline + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    private static void addEvent(String input) {
        String[] phrases = input.split(" /at ", 2);
        Event newEvent = new Event(phrases[0], phrases[1]);
        taskList.add(newEvent);
        System.out.println("added: \n " + newEvent + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    private static void mark(int index) {
        taskList.get(index - 1).markAsDone();
        System.out.println("Marked as done: \n" + taskList.get(index - 1));
    }



    private static void unmark(int index) {
        taskList.get(index - 1).markAsNotDone();
        System.out.println("Marked as not done: \n" + taskList.get(index - 1));
    }

}
