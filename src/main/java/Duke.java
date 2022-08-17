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
        Task task;
        int index;
        String[] items;
        switch(command) {
            case "list":
                OutputList();
                break;
            case "done":
                index = Integer.valueOf(words[1]);
                MarkItemDone(index);
                break;
            case "unmark":
                index = Integer.valueOf(words[1]);
                MarkItemUndone(index);
                break;
            case "todo":
                task = new Todo(words[1]);
                InsertTask(task);
                break;
             case "deadline": 
                items = s.substring(9).split(" /by ");
                task = new Deadline(items[0], items[1]);
                InsertTask(task);
                break;
             case "event":
                items = s.substring(6).split(" /at ");
                task = new Event(items[0], items[1]);
                InsertTask(task);
                break;
             default:
                task = new Task(s);
                InsertTask(task);
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

    private static void InsertTask(Task task) {
        taskList.add(task);
        System.out.println("added: ");
        System.out.println("\t" + task);
        System.out.format("you have %d task(s) in the list\n", taskList.size());
    }

    private static void MarkItemDone(int index) {
        taskList.get(index - 1).markDone();
        System.out.println("cool, this task is marked as done");
        System.out.println("\t" + taskList.get(index - 1));
    }

    private static void MarkItemUndone(int index) {
        taskList.get(index - 1).markUndone();
        System.out.println("ok, this task is marked as not done yet");
        System.out.println("\t" + taskList.get(index - 1));
    }
}
