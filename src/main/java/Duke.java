import java.util.Scanner;

public class Duke {
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

    private static void addTask(TaskList tasks, String[] taskText) throws TaskNotFoundException, ContentNotFoundException{
        if (taskText[0].contentEquals("todo")) {
            if (taskText.length > 1) {
                ToDo todo = new ToDo(taskText[1]);
                tasks.addTasks(todo);
            } else {
                throw new ContentNotFoundException(
                        "Input error: no content found after todo");
            }
        } else if (taskText[0].contentEquals("deadline")) {
            if (taskText.length > 1) {
                Deadline deadline = new Deadline(taskText[1]);
                tasks.addTasks(deadline);
            } else {
                throw new ContentNotFoundException(
                        "Input error: no content found after deadline");
            }
        } else if (taskText[0].contentEquals("event")) {
            if (taskText.length > 1) {
                Event event = new Event(taskText[1]);
                tasks.addTasks(event);
            } else {
                throw new ContentNotFoundException(
                        "Input error: no content found after event");
            }
        } else {
            throw new TaskNotFoundException(
                    "Command not found: " + taskText[0]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        TaskList tasks = new TaskList();
        System.out.println(
                "Hello! I'm Rio, the reality check you never asked for but really need.\n" +
                "What can I help with today?\n");
        Scanner userScan = new Scanner(System.in);
        while (userScan.hasNext()) {
            String input = userScan.nextLine();
            String[] keyword = input.split(" ", 2);
            if (input.contentEquals("bye")) {
                System.out.println("Goodbye, see you soon for your next healthy reality check!");
                break;
            } else if (input.contentEquals("list")) {
                tasks.list();
            } else if (keyword[0].contentEquals("mark")) {
                String message = tasks.markTask(Integer.parseInt(keyword[1].substring(0,1)));
                System.out.println("Congratulations on smashing reality!");
                System.out.println(message);
            } else if (keyword[0].contentEquals("unmark")) {
                String message = tasks.unmarkTask(Integer.parseInt(keyword[1].substring(0,1)));
                System.out.println("Oops reality is catching up... this is still undone:");
                System.out.println(message);
            } else {
                try {
                    addTask(tasks, keyword);
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
