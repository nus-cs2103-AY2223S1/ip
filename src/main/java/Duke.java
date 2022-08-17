import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK = "\t" + "-------------------------------------------------";
    private final Scanner sc;
    private String input;
    private Task[] tasks = new Task[100];
    private int taskIndex = 0;

    public Duke(Scanner sc) {
        this.sc = sc;
    }

    private void greet() {
        System.out.println("Hello! I'm Ee Suan!\nWhat can I do for you?");
    }

    public void start() throws DukeException {
        this.greet();
        try {
        input = sc.next();
        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    printList();
                    break;
                case "mark": {
                    int index = sc.nextInt();
                    mark(index);
                    break;
                }
                case "unmark": {
                    int index = sc.nextInt();
                    unmark(index);
                    break;
                }
                case "todo": {
                    if (sc.nextLine().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    input += sc.nextLine();
                    Task todo = createToDo();
                    addTask(todo);
                    echoTask(todo);
                    break;
                }
                case "deadline": {
                    input += sc.nextLine();
                    Task deadline = createDeadline();
                    addTask(deadline);
                    echoTask(deadline);
                    break;
                }
                case "event": {
                    input += sc.nextLine();
                    Task event = createEvent();
                    addTask(event);
                    echoTask(event);
                    break;
                }
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
            input = sc.next();
        }
        catch (DukeException e) {
            System.out.println(LINE_BREAK);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE_BREAK);
        }
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

//    private void echo() {
//        System.out.println(LINE_BREAK + "\n\t added: " + input + "\n" + LINE_BREAK);
//    }

    private void echoTask(Task task) {
        System.out.println(LINE_BREAK + "\n\tGot it. I've added this task: \n\t  " + task + "\n\tNow you have " + taskIndex + " task(s) in the list.\n" + LINE_BREAK);
    }

    private void printList() {
        System.out.println(LINE_BREAK + "\n\tHere are the tasks in your list:\n\t");
        for (int i = 0; i < taskIndex; i++) {
            Task curTask = tasks[i];
            System.out.println("\t" + (i + 1) + ". " + curTask + "\n");
        }
        System.out.println(LINE_BREAK);
    }

    private void mark(int i) {
        System.out.println(LINE_BREAK);
        Task curTask = tasks[i - 1];
        curTask.markAsDone();
        System.out.println("\tNice! I've marked this task as done: \n\t  " + curTask + "\n" );
        System.out.println(LINE_BREAK);
    }

    private void unmark(int i) {
        System.out.println(LINE_BREAK);
        Task curTask = tasks[i - 1];
        curTask.markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet: \n\t  " + curTask + "\n" );
        System.out.println(LINE_BREAK);
    }

    private Task createToDo () {
        String description = input.substring(5);
        return new ToDo(description);
    }

    private Task createDeadline() {
        int end = input.indexOf('/');
        String description = input.substring(9, end - 1);
        String by = input.substring(end + 4);
        return new Deadline(description, by);
    }

    private Task createEvent() {
        int end = input.indexOf('/');
        String description = input.substring(6, end - 1);
        String at = input.substring(end + 4);
        return new Event(description, at);
    }

    private void addTask(Task task) {
        tasks[taskIndex] = task;
        taskIndex++;
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke(sc);
        duke.start();
        duke.exit();

    }
}
