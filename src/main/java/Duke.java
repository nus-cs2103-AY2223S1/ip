import java.util.*;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Introduction();
        
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Process(input);
            input = sc.nextLine();
        }
    }

    private static void Introduction() {
        System.out.println("hello");
        System.out.println("can i help you?");
    }

    private static void Process(String s) {
        String[] words = s.split(" ");
        String command = words[0];
        switch(command) {
            case "list":
                OutputList();
                break;
            case "done":
                MarkItemDone(s);
                break;
            case "unmark":
                MarkItemUndone(s);
                break;
            case "todo":
                InsertTodo(s);
                break;
            case "deadline":
                InsertDeadline(s);
                break;
            case "event":
                InsertEvent(s);
                break;
            default:
                System.out.println("sorry, I don't understand you");
                break;
        }
    }

    private static void OutputList() {
        if (taskList.size() == 0) {
            System.out.println("you got no tasks");
        } else {
            System.out.println("heres your tasks");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, taskList.get(i));
            }
        }
    }

    private static void InsertTodo(String input) {
        try {
            String description = input.substring(5);
            InsertTask(new Todo(description));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        }
    }

    private static void InsertDeadline(String input) {
        try {
            String[] items = input.substring(9).split(" /by ");
            InsertTask(new Deadline(items[0], items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        }
    }

    private static void InsertEvent(String input) {
        try {
            String[] items = input.substring(6).split(" /at ");
            InsertTask(new Event(items[0], items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        }
    }
    private static void InsertTask(Task task) {
        taskList.add(task);
        System.out.println("added: ");
        System.out.println("\t" + task);
        System.out.format("you have %d task(s) in the list\n", taskList.size());
    }

    private static void MarkItemDone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException();
            }
            int index = Integer.parseInt(words[1]);
            taskList.get(index - 1).markDone();
            System.out.println("cool, this task is marked as done");
            System.out.println("\t" + taskList.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    private static void MarkItemUndone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException();
            }
            int index = Integer.parseInt(words[1]);
            taskList.get(index - 1).markUndone();
            System.out.println("ok, this task is marked as not done yet");
            System.out.println("\t" + taskList.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }
}
