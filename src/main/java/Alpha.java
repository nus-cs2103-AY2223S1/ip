import java.util.*;
public class Alpha {
    public static List<Task> todo = new ArrayList<>();
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        welcomeMessage();
        enterMessage();
    }

    private static void welcomeMessage() {
        System.out.println("\n-----------------\n" + ANSI_BLUE + "Hello, I'm ALPHA!" + ANSI_RESET + "\n-----------------");
    }

    private static void enterMessage() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputTokens = input.split(" ");
        switch (inputTokens[0]) {
            case "mark": {
                todo.get(Integer.parseInt(inputTokens[1]) - 1).changeStatus(true);
                System.out.println(ANSI_YELLOW + ">> " + "marked: " + inputTokens[1] + ANSI_RESET);
                enterMessage();
                break;
            }
            case "unmark": {
                todo.get(Integer.parseInt(inputTokens[1]) - 1).changeStatus(false);
                System.out.println(ANSI_YELLOW + ">> " + "unmarked: " + inputTokens[1] + ANSI_RESET);
                enterMessage();
                break;
            }
            case "list": {
                System.out.println(ANSI_YELLOW + ">> " + "Your task list is as follows:" + ANSI_RESET);
                int count = 1;
                for (Task task : todo) {
                    System.out.println(ANSI_YELLOW + count + ") " +  "["
                            + task.getStatus() + "] " + task.getDescription() + ANSI_RESET);
                    count++;
                }
                enterMessage();
                break;
            }
            case "bye": {
                System.out.println(ANSI_YELLOW + ">> " + "Bye, See you soon!" + ANSI_RESET);
                System.exit(0);
                break;
            }
            default: {
                Task t = new Task(input);
                todo.add(t);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                enterMessage();
            }
        }
    }
}
