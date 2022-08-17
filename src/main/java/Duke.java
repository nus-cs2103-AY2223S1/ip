import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
        String instruction = sc.next();
        while (instructionReader(instruction)) {
            instruction = sc.next();
        }
    }

    public static boolean instructionReader(String instruction) {
        if (instruction.equals("bye")) {
            System.out.println("Goodbye!");
            return false;
        }
        switch (instruction) {
            case "list": {
                listOut();
                break;
            }
            case "mark": {
                tasks[sc.nextInt() - 1].markTask();
                break;
            }
            case "unmark": {
                tasks[sc.nextInt() - 1].unmarkTask();
                break;
            }
            default: {
                addList(instruction);
            }
        }
        return true;
    }
    public static void addList(String instruction) {
        Task newTask;
        switch (instruction) {
            case "todo": {
                newTask = new ToDos(sc.nextLine());
                break;
            }
            case "deadline": {
                String[] input = sc.nextLine().split(" /by ");
                newTask = new Deadlines(input[0], input[1]);
                break;
            }
            case "event": {
                String[] input = sc.nextLine().split(" /at ");
                newTask = new Events(input[0], input[1]);
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + instruction);
            }
        }
        tasks[Task.getNumTasks() - 1] = newTask;
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        if (Task.getNumTasks() == 1) {
            System.out.println("Now you have 1 task in the list.\n");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.\n", Task.getNumTasks()));
        }
    }

    public static void listOut() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < Task.getNumTasks(); i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks[i].toString()));
        }
        System.out.println("");
    }
}
