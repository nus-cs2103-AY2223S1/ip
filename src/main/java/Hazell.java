import java.util.Scanner;

public class Hazell {
    public static void reply(String msg) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : msg.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        String logo = "  _    _               _ _ \n"
                + " | |  | |             | | |\n"
                + " | |__| | __ _ _______| | |\n"
                + " |  __  |/ _` |_  / _ \\ | |\n"
                + " | |  | | (_| |/ /  __/ | |\n"
                + " |_|  |_|\\__,_/___\\___|_|_|\n";
        System.out.println(logo);

        TaskStore taskStore = new TaskStore();

        reply("Hello, I am Hazell!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userinput = scanner.nextLine().strip();
            Command command = Command.parse(userinput);
            if (command.startsWith("bye")) {
                reply("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (command.startsWith("list")) {
                reply(taskStore.toString());
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                Task task;
                try {
                    task = taskStore.getTask(index);
                } catch (IndexOutOfBoundsException ex) {
                    reply(String.format("There's no such task! Please choose a task from 1 to %d", taskStore.size()));
                    continue;
                }
                task.markAsDone();
                reply(String.format("Nice! I've marked this task as done:\n\t%s", task.toString()));
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                Task task;
                try {
                    task = taskStore.getTask(index);
                } catch (IndexOutOfBoundsException ex) {
                    reply(String.format("There's no such task! Please choose a task from 1 to %d", taskStore.size()));
                    continue;
                }
                task.markAsUndone();
                reply(String.format("OK, I've marked this task as not done yet:\n\t%s", task.toString()));
            } else if (command.startsWith("todo")) {
                String description = String.join(" ", command.getTrailingArgs());
                String response = taskStore.addTask(new ToDo(description));
                reply(response);
            } else if (command.startsWith("deadline")) {
                String description = String.join(" ", command.getTrailingArgs());
                String time;
                try {
                    time = command.getKwarg("by");
                } catch (Command.KwargNotFoundException ex) {
                    reply("To create a deadline, please specify a \"/by\" option.");
                    continue;
                }
                String response = taskStore.addTask(new Deadline(description, time));
                reply(response);
            } else if (command.startsWith("event")) {
                String description = String.join(" ", command.getTrailingArgs());
                String time;
                try {
                    time = command.getKwarg("at");
                } catch (Command.KwargNotFoundException ex) {
                    reply("To create an event, please specify an \"/at\" option.");
                    continue;
                }

                String response = taskStore.addTask(new Event(description, time));
                reply(response);
            } else {
                reply(String.format("Unknown command :("));
            }
        }
    }
}
