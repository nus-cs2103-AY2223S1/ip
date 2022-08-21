
public class Mia {
    private static final TaskManager tasksManager = new TaskManager();
    private static final String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                                       "┃ You are talking to MIA... ┃\n" +
                                       "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

    public void run() {
        System.out.println(logo);
        final ChatWindow window = new ChatWindow(50);

        window.printResponse(new Span("Hello there!"));

        while (true) {
            final String line = window.prompt("Enter a command: ");

            // Replaces the entered command (previous line) with a bubble
            System.out.print("\u001B[1A\u001B[K");
            window.printCommand(new Span(line));

            if (line.equals("bye")) {
                window.printResponse(new Span("See you!"));
                break;
            } else if (line.equals("list")) {
                window.printResponse(new Span(tasksManager.toString()));
            } else if (line.startsWith("delete ")) {
                final int number = Integer.parseInt(line.substring(7));
                if (tasksManager.deleteTask(number)) {
                    window.printResponse(new Span("Task has been deleted!"));
                } else {
                    window.printResponse(new Span(String.format(
                            "Something went wrong when deleting task %d! Likely, you specified an invalid task number.",
                            number)));
                }
            } else if (line.startsWith("mark ")) {
                final int number = Integer.parseInt(line.substring(5));
                if (tasksManager.checkTask(number)) {
                    window.printResponse(new Span("Task has been marked as done!"));
                } else {
                    window.printResponse(new Span(String.format(
                            "Task not modified! Either the task is already done, or you specified an invalid task number %d.",
                            number)));
                }
            } else if (line.startsWith("unmark ")) {
                final int number = Integer.parseInt(line.substring(7));
                if (tasksManager.uncheckTask(number)) {
                    window.printResponse(new Span("Task has been marked as not done!"));
                } else {
                    window.printResponse(new Span(String.format(
                            "Task not modified! Either the task is still not done, or you specified an invalid task number %d.",
                            number)));
                }
            } else if (line.startsWith("todo ")) {
                final Task todo = new Todo(line.substring(5));
                tasksManager.addTask(todo);
                window.printResponse(new Span(String.format("Added todo \"%s\" to tasks list!", todo.getTitle())));
            } else if (line.startsWith("deadline ")) {
                final String[] data = line.substring(9).split("/by", 2);
                if (data.length == 2) {
                    final Task deadline = new Deadline(data[0], data[1]);
                    tasksManager.addTask(deadline);
                    window.printResponse(new Span(String.format("Added \"%s\" (task with deadline) to tasks list!", deadline.getTitle())));
                } else {
                    window.printResponse(new Span("Incorrect format of deadline command!"));
                }
            } else if (line.startsWith("event ")) {
                final String[] data = line.substring(6).split("/at", 2);
                if (data.length == 2) {
                    final Task event = new Event(data[0], data[1]);
                    tasksManager.addTask(event);
                    window.printResponse(new Span(String.format("Added new event \"%s\" to tasks list!", event.getTitle())));
                } else {
                    window.printResponse(new Span("Incorrect format of event command!"));
                }
            } else {
                window.printResponse(new Span("Sorry boss, I don't know what you are trying to say 😟"));
            }
        }
        window.dispose();
    }

}
