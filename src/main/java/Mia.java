
public class Mia {
    private static final String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                                       "┃ You are talking to MIA... ┃\n" +
                                       "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

    private final TaskManager tasksManager = new TaskManager();
    private final ChatWindow window = new ChatWindow(50);

    public void respond(String message) {
        window.printResponse(new Span(message));
    }

    public void run() {
        System.out.println(logo);

        respond("Hello there!");

        while (true) {
            final String line = window.prompt("Enter a command: ");

            // Replaces the entered command (previous line) with a bubble
            System.out.print("\u001B[1A\u001B[K");
            window.printCommand(new Span(line));

            if (line.equals("bye")) {
                respond("See you!");
                break;
            } else if (line.equals("list")) {
                respond(tasksManager.toString());
            } else if (line.startsWith("delete ")) {
                final int number = Integer.parseInt(line.substring(7));
                if (tasksManager.deleteTask(number)) {
                    respond("Task has been deleted!");
                } else {
                    respond(String.format(
                            "Something went wrong when deleting task %d! Likely, you specified an invalid task number.",
                            number));
                }
            } else if (line.startsWith("mark ")) {
                final int number = Integer.parseInt(line.substring(5));
                if (tasksManager.checkTask(number)) {
                    respond("Task has been marked as done!");
                } else {
                    respond(String.format(
                            "Task not modified! Either the task is already done, or you specified an invalid task number %d.",
                            number));
                }
            } else if (line.startsWith("unmark ")) {
                final int number = Integer.parseInt(line.substring(7));
                if (tasksManager.uncheckTask(number)) {
                    respond("Task has been marked as not done!");
                } else {
                    respond(String.format(
                            "Task not modified! Either the task is still not done, or you specified an invalid task number %d.",
                            number));
                }
            } else if (line.startsWith("todo ")) {
                final Task todo = new Todo(line.substring(5));
                tasksManager.addTask(todo);
                respond(String.format("Added todo \"%s\" to tasks list!", todo.getTitle()));
            } else if (line.startsWith("deadline ")) {
                final String[] data = line.substring(9).split("/by", 2);
                if (data.length == 2) {
                    final Task deadline = new Deadline(data[0], data[1]);
                    tasksManager.addTask(deadline);
                    respond(String.format("Added \"%s\" (task with deadline) to tasks list!", deadline.getTitle()));
                } else {
                    respond("Incorrect format of deadline command!");
                }
            } else if (line.startsWith("event ")) {
                final String[] data = line.substring(6).split("/at", 2);
                if (data.length == 2) {
                    final Task event = new Event(data[0], data[1]);
                    tasksManager.addTask(event);
                    respond(String.format("Added new event \"%s\" to tasks list!", event.getTitle()));
                } else {
                    respond("Incorrect format of event command!");
                }
            } else {
                respond("Sorry boss, I don't know what you are trying to say 😟");
            }
        }
        window.dispose();
    }

}
