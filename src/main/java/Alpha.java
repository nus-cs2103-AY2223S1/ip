import java.util.*;

public class Alpha {
    public static List<Task> todo = new ArrayList<>();
    public static final String ANSI_YELLOW = "\u001B[33m";

    static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        welcomeMessage();
        Scanner in = new Scanner(System.in);

        enterMessage(in);
    }

    private static void welcomeMessage() {
        System.out.println("\n-----------------\n" + ANSI_BLUE + "Hello, I'm ALPHA!" + ANSI_RESET + "\n-----------------");
    }

    private static void enterMessage(Scanner in) {
        String input = in.nextLine();
        String[] inputTokens = input.split(" ");
        switch (inputTokens[0]) {
            case "todo": {
                Task t = new Todo(input, "T");
                todo.add(t);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                enterMessage(in);
                break;
            }
            case "event": {
                int separator = input.indexOf('/');
                Task t = new Event(input.substring(6, separator - 1), input.substring(separator + 1), "E");
                todo.add(t);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                enterMessage(in);
                break;
            }
            case "deadline": {
                int separator = input.indexOf('/');
                Task t = new Deadline(input.substring(9, separator - 1), input.substring(separator + 1), "D");
                todo.add(t);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                enterMessage(in);
                break;
            }
            case "mark": {
                todo.get(Integer.parseInt(inputTokens[1]) - 1).changeStatus(true);
                System.out.println(ANSI_YELLOW + ">> " + "marked: Task " + inputTokens[1] + ANSI_RESET);
                enterMessage(in);
                break;
            }
            case "unmark": {
                todo.get(Integer.parseInt(inputTokens[1]) - 1).changeStatus(false);
                System.out.println(ANSI_YELLOW + ">> " + "unmarked: Task " + inputTokens[1] + ANSI_RESET);
                enterMessage(in);
                break;
            }
            case "list": {
                System.out.println(ANSI_YELLOW + ">> " + "Your task list is as follows:" + ANSI_RESET);
                int count = 1;
                for (Task task : todo) {
                    System.out.print(ANSI_YELLOW + count + ") " + "[" + task.getTaskType() + "] ["
                            + task.getStatus() + "] " + task.getDescription() + ANSI_RESET);
                    if (task.getTaskType().equals("E")) {
                        Event e = (Event) task;
                        System.out.println(ANSI_RED + " on " + e.getDate() + ANSI_RESET);
                    } else if (task.getTaskType().equals("D")) {
                        Deadline d = (Deadline) task;
                        System.out.println(ANSI_RED + " by " + d.getDeadline() + ANSI_RESET);
                    } else {
                        System.out.println();
                    }
                    count++;
                }
                enterMessage(in);
                break;
            }
            case "bye": {
                System.out.println(ANSI_YELLOW + ">> " + "Bye, See you soon!" + ANSI_RESET);
                System.exit(0);
                break;
            }
            default: {
                System.out.println(ANSI_RED + ">> " + "Invalid Input " + ANSI_RESET);
                enterMessage(in);
            }
        }
    }
}
