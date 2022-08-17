import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK = "\t" + "-------------------------------------------------";
    private String input;
    private Task[] tasks = new Task[100];
    private int taskIndex = 0;

    private void greet() {
        System.out.println("Hello! I'm Ee Suan!\nWhat can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo() {
        System.out.println(LINE_BREAK + "\n\t added: " + input + "\n" + LINE_BREAK);
    }

    private void echoSpecialTask(Task task) {
        System.out.println(LINE_BREAK + "\n\tGot it. I've added this task: \n\t  " + task + "\n\tNow you have " + taskIndex + " task(s) in the list.\n" + LINE_BREAK);
    }

    private void list() {
        System.out.println(LINE_BREAK);
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

    private void createAndAddTask() {
        tasks[taskIndex] = new Task(input);
        taskIndex++;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scan = new Scanner(System.in);
        duke.input = scan.next();
        while (!duke.input.equals("bye")) {
            switch (duke.input) {
                case "list":
                    duke.list();
                    break;
                case "mark": {
                    int index = scan.nextInt();
                    duke.mark(index);
                    break;
                }
                case "unmark": {
                    int index = scan.nextInt();
                    duke.unmark(index);
                    break;
                }
                case "todo": {
                    duke.input += scan.nextLine();
                    Task todo = duke.createToDo();
                    duke.addTask(todo);
                    duke.echoSpecialTask(todo);
                    break;
                }
                case "deadline": {
                    duke.input += scan.nextLine();
                    Task deadline = duke.createDeadline();
                    duke.addTask(deadline);
                    duke.echoSpecialTask(deadline);
                    break;
                }
                case "event": {
                    duke.input += scan.nextLine();
                    Task event = duke.createEvent();
                    duke.addTask(event);
                    duke.echoSpecialTask(event);
                    break;
                }
                default:
                    duke.input += scan.nextLine();
                    duke.createAndAddTask();
                    duke.echo();
                    break;
            }
            duke.input = scan.next();
        }
        duke.exit();

    }
}
