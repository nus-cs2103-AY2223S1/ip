import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }

    private void run() throws DukeException {
        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();
            String[] strings = input.split(" ", 2);
            if (strings.length <= 1) {
                singleCommand(strings);
            } else {
                doubleCommand(strings);
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            try {
                duke.run();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private void singleCommand(String[] arg) throws DukeException {
        switch (arg[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                return;
            default:
                throw new InputException();
        }
    }

    private void doubleCommand(String[] arg) throws DukeException {
        switch (arg[0]) {
            case "mark":
                if (arg.length == 1) {
                    throw new MarkException();
                } else {
                    try {
                        int num = Integer.parseInt(arg[1]);
                        if (num > tasks.size()) {
                            throw new MarkException();
                        } else {
                            tasks.get(num - 1).setDone(true);
                            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(num - 1));
                        }
                    } catch (NumberFormatException e) {
                        throw new MarkException();
                    }
                }
                break;

            case "unmark":
                if (arg.length == 1) {
                    throw new MarkException();
                } else {
                    try {
                        int num = Integer.parseInt(arg[1]);
                        if (num > tasks.size()) {
                            throw new MarkException();
                        } else {
                            tasks.get(num - 1).setDone(false);
                            System.out.println("Ok, I've marked this task as not done yet:\n" + tasks.get(num - 1));
                        }
                    } catch (NumberFormatException e) {
                        throw new MarkException();
                    }
                }
                break;

            case "deadline":
                if (arg.length == 1 || arg[1].isEmpty()) {
                    throw new TaskException();
                } else {
                    String[] split = arg[1].split("/");
                    if (split.length < 2) {
                        throw new TimeException();
                    } else {
                        Task task = new Deadline(split[0], split[1]);
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n" + task);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    }
                }
                break;

            case "todo":
                if (arg.length == 1) {
                    throw new TaskException();
                } else {
                    Task task = new ToDo(arg[1]);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:\n" + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                }
                break;

            case "event":
                if (arg.length == 1) {
                    throw new TaskException();
                } else {
                    String[] split = arg[1].split("/");
                    if (split.length < 2) {
                        throw new TimeException();
                    } else {
                        Task task = new Event(split[0], split[1]);
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n" + task);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    }
                }
                break;

            case "delete":
                if (arg.length == 1) {
                    throw new MarkException();
                } else {
                    try {
                        int num = Integer.parseInt(arg[1]);
                        if (num > tasks.size()) {
                            throw new MarkException();
                        } else {
                            System.out.println("Noted, I've removed this task:\n" + tasks.get(num - 1));
                            tasks.remove(num - 1);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        }
                    } catch (NumberFormatException e) {
                        throw new MarkException();
                    }
                }
                break;

            default:
                throw new InputException();
        }
    }
}


