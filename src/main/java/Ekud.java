import java.util.Scanner;

public class Ekud {
    private final Task[] taskList;
    private final String logo = "_____________             .___\n" + "\\_   _____/  | ____ __  __| _/\n" + 
    " |    __)_|  |/ /  |  \\/ __ |\n" + " |        \\    <|  |  / /_/ | \n" + "/_______  /__|_ \\____/\\____ | \n" + 
    "        \\/     \\/          \\/ \n";

    public Ekud() {
        this.taskList = new Task[100];
    }

    public void start() {
        this.sendMessage("Hello from\n" + this.logo + "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        boolean active = true;

        while (active) {
            String command = sc.nextLine();
            switch(command) {
                case "bye":
                    sendMessage("Bye. Hope to see you again soon!");
                    active = false;
                    break;
                default:
                    sendMessage(command);
            }
        }
        sc.close();
    }

    private String indentMessage(String message) {
        StringBuilder builder = new StringBuilder("    ");
        for (int i = 0; i < message.length(); i++) {
            builder.append(message.charAt(i));
            if (message.charAt(i) == '\n') {
                builder.append("    ");
            }
        }
        return builder.toString();
    }

    private void sendMessage(String message) {
        String divider = "___________________________________";
        System.out.println(indentMessage(divider + "\n" + message + "\n" + divider + "\n"));
    }
    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        ekud.start();

    }
}
