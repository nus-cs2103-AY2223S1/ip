import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        start_prompt();
        main_task();
    }

    private static final ArrayList<String> task = new ArrayList<>();

    private static void main_task() {
        final String terminating_text = "bye";
        final String list_text = "list";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case terminating_text:
                    end_prompt();
                    break;
                case list_text:
                    list_tasks();
                    break;
                default:
                    default_case(input);
            }
        }
    }

    static void default_case(String input) {
        task.add(input);
        String text = "               added: ";
        System.out.println(text + input);
        line_divider();
    }

    private static void list_tasks() {
        for (int i = 1; i <= task.size(); i++) {
            System.out.println(i + ") " + task.get(i - 1));
        }
        line_divider();
    }

    private static void start_prompt() {
        String logo = "____    ____  __     ___   ___  __       ___       __   __\n"
                + "\\   \\  /   / |  |    \\  \\ /  / |  |     /   \\     |  \\ |  |\n"
                + " \\   \\/   /  |  |     \\  V  /  |  |    /  ^  \\    |   \\|  |\n"
                + "  \\_    _/   |  |      >   <   |  |   /  /_\\  \\   |  . `  |\n"
                + "    |  |     |  |     /  .  \\  |  |  /  _____  \\  |  |\\   |\n"
                + "    |__|     |__|    /__/ \\__\\ |__| /__/     \\__\\ |__| \\__|\n";
        System.out.println("Hi from\n" + logo);
        System.out.println("What can I do for you?");
        line_divider();
    }

    public static void end_prompt() {
        String goodbye_text = "  _______   ______     ______    _______      ______   ____    ____  _______\n"
                + " /  _____| /  __  \\   /  __  \\  |       \\    |   _  \\  \\   \\  /   / |   ____|\n"
                + "|  |  __  |  |  |  | |  |  |  | |  .--.  |   |  |_)  |  \\   \\/   /  |  |__\n"
                + "|  | |_ | |  |  |  | |  |  |  | |  |  |  |   |   _  <    \\_    _/   |   __|\n"
                + "|  |__| | |  `--'  | |  `--'  | |  '--'  |   |  |_)  |     |  |     |  |____\n"
                + " \\______|  \\______/   \\______/  |_______/    |______/      |__|     |_______|\n";
        System.out.println(goodbye_text);
    }

    private static void line_divider() {
        System.out.println("════════════════════════════ ⋆★⋆ ════════════════════════════");
    }
}
