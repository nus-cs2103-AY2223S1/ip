import java.util.Scanner;

public class Duke {
//    private TaskList tasks;
//    private Storage storage;

    enum COMMANDS {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE
    }

    enum TASKS {
        TODO,
        DEADLINE,
        EVENT
    }

    private static String echo() {
        Scanner userScan = new Scanner(System.in);
        String message = userScan.nextLine();
        while (!message.contentEquals("bye")) {
            System.out.println(message);
            message = userScan.nextLine();
        }
        if (message.contentEquals("bye")) {
            System.out.println("Goodbye, see you soon for your next healthy reality check!");
        }
        return message;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        tasks = storage.load();
        System.out.println(
                "Hello! I'm Rio, the reality check you never asked for but really need.\n" +
                "What can I help with today?\n");
        Scanner userScan = new Scanner(System.in);
        while (userScan.hasNext()) {
            String input = userScan.nextLine();
            String[] keywords = input.split(" ", 2);
            if (input.contentEquals("bye")) {
                System.out.println("Goodbye, see you soon for your next healthy reality check!");
                break;
            } else if (input.contentEquals("list")) {
                tasks.list();
            } else if (keywords[0].contentEquals("mark")) {
                String message = tasks.markTask(Integer.parseInt(keywords[1].substring(0,1)));
                storage.saveTasks(tasks);
                System.out.println("Congratulations on smashing reality!");
                System.out.println(message);
            } else if (keywords[0].contentEquals("unmark")) {
                String message = tasks.unmarkTask(Integer.parseInt(keywords[1].substring(0,1)));
                storage.saveTasks(tasks);
                System.out.println("Oops reality is catching up... this is still undone:");
                System.out.println(message);
            } else if (keywords[0].contentEquals("delete")) {
                String message = tasks.deleteTask(Integer.parseInt(keywords[1].substring(0,1)));
                storage.saveTasks(tasks);
                System.out.println("This task as perished from reality:");
                System.out.println(message);
            } else {
                try {
                    tasks.addTask(keywords);
                    storage.saveTasks(tasks);
                } catch (TaskNotFoundException e) {
                    System.err.println(e.toString());
                    System.out.println("Oops, I'm not sentient enough to understand that...");
                } catch (ContentNotFoundException e) {
                    System.err.println(e.toString());
                    System.out.println("Doing nothing is great... but a task needs some content!");
                } finally {
                    continue;
                }
            }
        }
    }
}
