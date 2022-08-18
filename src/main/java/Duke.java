import java.util.Scanner;

public class Duke {
    //    private static String LINE_BREAK = "-------------------------------------------------";
    private final Scanner sc;
    private String input;
    private Task[] tasks = new Task[100];
    private int taskIndex = 0;

    public Duke(Scanner sc) {
        this.sc = sc;
    }

    private void dukeReply(String message) {
        String lineBreak = "-------------------------------------------------";
        String reply = String.format("%s\n%s\n%s", lineBreak, message, lineBreak);
        System.out.println(reply);
    }

    private void greet() {
        dukeReply("Hello! I'm Ee Suan!\nWhat can I do for you?");
    }

    public void start() throws DukeException {
        this.greet();
        input = sc.next();
        while (!input.equals("bye")) {
            try {
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
                        String next = sc.nextLine();
                        if (next.equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            input += next;
                            Task todo = createToDo();
                            addTask(todo);
                            echoTask(todo);
                            break;
                        }
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
            } catch (DukeException e) {
                dukeReply(e.getMessage());
            }

            input = sc.next();
        }
}

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

//    private void echo() {
//        System.out.println(LINE_BREAK + "\n\t added: " + input + "\n" + LINE_BREAK);
//    }

    private void echoTask(Task task) {
        dukeReply("Got it. I've added this task: \n  " + task + "\nNow you have " + taskIndex + " task(s) in the list.");
    }

    private void printList() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskIndex; i++) {
            Task curTask = tasks[i];
            message +="\n" + (i + 1) + ". " + curTask;
        }
        dukeReply(message);
    }

    private void mark(int i) {
        Task curTask = tasks[i - 1];
        curTask.markAsDone();
        dukeReply("Nice! I've marked this task as done: \n  " + curTask);
    }

    private void unmark(int i) {
        Task curTask = tasks[i - 1];
        curTask.markAsUndone();
        dukeReply("OK, I've marked this task as not done yet: \n  " + curTask);
    }

    private Task createToDo() {
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
