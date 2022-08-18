import java.util.Scanner;
import java.util.ArrayList;

public class Doris {
    ArrayList<Task> list = new ArrayList<Task>();

    public void start (){
        String logo = "                                                      \n" +
                "                                                      \n" +
                "    ,---,                                             \n" +
                "  .'  .' `\\                       ,--,                \n" +
                ",---.'     \\    ,---.    __  ,-.,--.'|                \n" +
                "|   |  .`\\  |  '   ,'\\ ,' ,'/ /||  |,      .--.--.    \n" +
                ":   : |  '  | /   /   |'  | |' |`--'_     /  /    '   \n" +
                "|   ' '  ;  :.   ; ,. :|  |   ,',' ,'|   |  :  /`./   \n" +
                "'   | ;  .  |'   | |: :'  :  /  '  | |   |  :  ;_     \n" +
                "|   | :  |  ''   | .; :|  | '   |  | :    \\  \\    `.  \n" +
                "'   : | /  ; |   :    |;  : |   '  : |__   `----.   \\ \n" +
                "|   | '` ,/   \\   \\  / |  , ;   |  | '.'| /  /`--'  / \n" +
                ";   :  .'      `----'   ---'    ;  :    ;'--'.     /  \n" +
                "|   ,.'                         |  ,   /   `--'---'   \n" +
                "'---'                            ---`-'               \n" +
                "                                                      ";
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("Eh what you want?");

        while (true) {
            String command = sc.nextLine();

            if(command.equals("bye")) {
                System.out.println("Bye you annoying sia don't want talk to you");
                return;
            } else if (command.equals("list")) {
                System.out.println("Eh faster go:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
            } else if (command.startsWith("mark")) {
                int eventNum = Integer.parseInt(command.substring(command.length() - 1));
                list.get(eventNum - 1).mark();
                System.out.println("Huh you sure you do already or not?");
                System.out.println("Okay la I trust you I trust you");
                System.out.println("What else you want?");
            } else if (command.startsWith("unmark")) {
                int eventNum = Integer.parseInt(command.substring(command.length() - 1));
                list.get(eventNum - 1).unmark();
                System.out.println("Eh don't laze leh go do go do");
                System.out.println("What else you want?");
            } else if (command.startsWith("todo")) {
                Todo todo = new Todo(command.substring(5));
                list.add(todo);
                System.out.println("Eh must remember to do this ah:");
                System.out.println(todo);
                System.out.println("You have " + list.size() + " tasks leh better hurry up");
            } else if (command.startsWith("deadline")) {
                String[] commands = command.split(" /by ");
                Deadline deadline = new Deadline(commands[0], commands[1]);
                list.add(deadline);
                System.out.println("Eh this one due soon stop wasting time go do now:");
                System.out.println(deadline);
                System.out.println("You have " + list.size() + " tasks leh better hurry up");
            } else if (command.startsWith("event")) {
                String[] commands = command.split(" /at ");
                Event event = new Event(commands[0], commands[1]);
                list.add(event);
                System.out.println("Oi remember to attend this ah:");
                System.out.println(event);
                System.out.println("You have " + list.size() + " tasks leh better hurry up");
            } else {
                list.add(new Task(command));
                System.out.println("Orh I remind you later to " + command);
                System.out.println("What else you want?");
            }
        }
    }

    public static void main(String[] args) {
        new Doris().start();
    }
}
