import java.util.Scanner;

public class Alan {
    private Scanner input;

    Alan() {
        this.input = new Scanner(System.in);
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
            } else {
                System.out.println(getFormattedReply(command));
            }
        }

        System.out.println(getFormattedReply("Goodbye! See you soon!") );
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

    private String getFormattedReply(String reply) {
        String separator = "_______________________________________";
        return separator + "\n" + reply + "\n" + separator;
    }
}




