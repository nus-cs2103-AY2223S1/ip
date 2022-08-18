import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alan {
    private Scanner input;
    private Formatter formatter;
    private List<String> list;

    public Alan() {
        this.input = new Scanner(System.in);
        this.formatter = new Formatter();
        this.list = new ArrayList<>();
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

        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(formatter.list(list));
            } else {
                list.add(command);
                System.out.println(formatter.added(command));
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




