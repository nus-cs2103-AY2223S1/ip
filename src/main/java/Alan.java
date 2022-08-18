import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alan {
    private Scanner input;
    private Parser parser;
    private Formatter formatter;
    private List<Task> taskList;

    public Alan() {
        this.input = new Scanner(System.in);
        this.parser = new Parser();
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
            String command = userInput.split(" ", 2)[0];
            switch (command) {
                case "bye":
                    break label;
                case "list":
                    System.out.println(formatter.list(taskList));
                    break;
                case "event":
                    ParsedInput eventInput = parser.parse(InputType.event, userInput);
                    Task eventTask = new Event(eventInput);
                    taskList.add(eventTask);
                    System.out.println(formatter.addTask(eventTask, taskList.size()));
                    break;
                case "deadline":
                    ParsedInput deadlineInput = parser.parse(InputType.deadline, userInput);
                    Task deadlineTask = new Deadline(deadlineInput);
                    taskList.add(deadlineTask);
                    System.out.println(formatter.addTask(deadlineTask, taskList.size()));
                    break;
                case "todo":
                    ParsedInput todoInput = parser.parse(InputType.todo, userInput);
                    Task todoTask = new Todo(todoInput);
                    taskList.add(todoTask);
                    System.out.println(formatter.addTask(todoTask, taskList.size()));
                    break;
                case "mark": {
                    ParsedInput markInput = parser.parse(InputType.mark, userInput);
                    Task selectedTask = taskList.get(markInput.getListIndex());
                    selectedTask.markDone();
                    System.out.println(formatter.markDone(selectedTask));
                    break;
                }
                case "unmark": {
                    ParsedInput unmarkInput = parser.parse(InputType.unmark, userInput);
                    Task selectedTask = taskList.get(unmarkInput.getListIndex());
                    selectedTask.markUndone();
                    System.out.println(formatter.markUndone(selectedTask));
                    break;
                }
                case "help":
                    // TODO: 18/8/22
                    break;
                default:
                    System.out.println(formatter.invalid());
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




