import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alan {
    private Scanner input;
    private Formatter formatter;
    private List<Task> taskList;

    public Alan() {
        this.input = new Scanner(System.in);
        this.formatter = new Formatter();
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Create new instance of Alan and run him
        Alan alan = new Alan();
        alan.start();
    }

    private void start() {
        greet();
        run();
    }

    private void run() {
        System.out.println("How may i be of service?");

        label:
        while (true) {
            String userInput = input.nextLine();
            String[] inputSplit = userInput.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "bye":
                    break label;
                case "list":
                    System.out.println(formatter.list(taskList));
                    break;
                case "mark": {
                    Task selectedTask = taskList.get(Integer.parseInt(inputSplit[1]) - 1);
                    selectedTask.markDone();
                    System.out.println(formatter.markDone(selectedTask));
                    break;
                }
                case "unmark": {
                    Task selectedTask = taskList.get(Integer.parseInt(inputSplit[1]) - 1);
                    selectedTask.markUndone();
                    System.out.println(formatter.markUndone(selectedTask));
                    break;
                }
                default:
                    taskList.add(new Task(command));
                    System.out.println(formatter.added(command));
                    break;
            }
        }

        System.out.println(formatter.basic("Goodbye! See you soon!") );
    }

    // Prints a greeting
    private void greet() {
        final String logo = " $$$$$$\\  $$\\        $$$$$$\\  $$\\   $$\\\n"
                + "$$  __$$\\ $$ |      $$  __$$\\ $$$\\  $$ |\n"
                + "$$ /  $$ |$$ |      $$ /  $$ |$$$$\\ $$ |\n"
                + "$$$$$$$$ |$$ |      $$$$$$$$ |$$ $$\\$$ |\n"
                + "$$  __$$ |$$ |      $$  __$$ |$$ \\$$$$ |\n"
                + "$$ |  $$ |$$ |      $$ |  $$ |$$ |\\$$$ |\n"
                + "$$ |  $$ |$$$$$$$$\\ $$ |  $$ |$$ | \\$$ |\n"
                + "\\__|  \\__|\\________|\\__|  \\__|\\__|  \\__|\n";

        System.out.println(getTimeGreeting() +
                "!\nMy name is\n\n" + logo);
    }

    // Checks hour of day and returns appropriate greeting
    private String getTimeGreeting() {
        int hour = java.time.LocalTime.now().getHour();
        String greeting = hour < 12
                ? "Morning"
                : hour < 18
                ? "Afternoon"
                : "Evening";
        return "\nGood " + greeting;
    }
}




