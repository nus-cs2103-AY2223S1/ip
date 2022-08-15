import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Henry {

    private final List<Task> tasks;
    private final Scanner sc;
    private boolean activated;

    public Henry() {
        System.out.println(
            " .----------------.  .----------------.  .-----------------. .----------------.  .----------------. \n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |  ____  ____  | || |  _________   | || | ____  _____  | || |  _______     | || |  ____  ____  | |\n" +
            "| | |_   ||   _| | || | |_   ___  |  | || ||_   \\|_   _| | || | |_   __ \\    | || | |_  _||_  _| | |\n" +
            "| |   | |__| |   | || |   | |_  \\_|  | || |  |   \\ | |   | || |   | |__) |   | || |   \\ \\  / /   | |\n" +
            "| |   |  __  |   | || |   |  _|  _   | || |  | |\\ \\| |   | || |   |  __ /    | || |    \\ \\/ /    | |\n" +
            "| |  _| |  | |_  | || |  _| |___/ |  | || | _| |_\\   |_  | || |  _| |  \\ \\_  | || |    _|  |_    | |\n" +
            "| | |____||____| | || | |_________|  | || ||_____|\\____| | || | |____| |___| | || |   |______|   | |\n" +
            "| |              | || |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
        tasks = new ArrayList<>();
        sc = new Scanner(System.in);
        activated = true;
        System.out.println(
            formatResponse("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?"));
    }

    // Command handling
    public void parseCommand(String command) {
        if (command.equalsIgnoreCase("list")) {
            getList();
        } else if (command.equalsIgnoreCase("bye")) {
            close();
        } else if (command.matches("mark\\s\\d")) {
            int taskMarked = Integer.parseInt(command.split(" ")[1]);
            markTask(taskMarked);
        } else if (command.matches("unmark\\s\\d")) {
            int taskUnmarked = Integer.parseInt(command.split(" ")[1]);
            unmarkTask(taskUnmarked);
        } else {
            addToList(command);
        }
    }

    public boolean isActivated() {
        return activated;
    }

    public void close() {
        System.out.println(formatResponse("GOODBYE!"));
        activated = false;
    }

    public void echo(String input) {
        System.out.println(formatResponse(input));
    }

    // Use try-catch to avoid out of bounds exception
    public void markTask(int index) {
        try {
            tasks.get(index).setComplete(true);
            System.out.println(formatResponse(
                "I'VE MARKED THIS TASK AS DONE: \n" + tasks.get(index)));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(formatResponse("TASK NOT FOUND"));
        }
    }

    // Use try-catch to avoid out of bounds exception
    public void unmarkTask(int index) {
        try {
            tasks.get(index).setComplete(false);
            System.out.println(formatResponse(
                "I'VE MARKED THIS TASK AS NOT DONE: \n" + tasks.get(index)));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(formatResponse("TASK NOT FOUND"));
        }
    }

    public void addToList(String input) {
        tasks.add(new Task(input));
        System.out.println(
            formatResponse("OK. I ADDED \"" + input + "\" TO MY LIST."));
    }

    public void getList() {
        System.out.println(formatList());
    }

    public String getInput() {
        return sc.nextLine();
    }

    private String formatList() {
        StringBuilder sb = new StringBuilder();
        sb.append(
            "____________________________________________________________");
        sb.append("\n \uD83E\uDD16: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1))
              .append("\n");
        }
        sb.append(
            "____________________________________________________________");
        return sb.toString();
    }

    private String formatResponse(String input) {
        return "____________________________________________________________" +
               "\n \uD83E\uDD16: " + input + "\n" +
               "____________________________________________________________";
    }
}
