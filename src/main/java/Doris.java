import java.util.Scanner;
import java.util.ArrayList;

public class Doris {
    ArrayList<Event> list = new ArrayList<Event>();

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
                System.out.println("Bye you annoying sia");
                return;
            } else if (command.equals("list")) {
                System.out.println("Eh faster go:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i).getDescription());
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
            } else {
                list.add(new Event(command));
                System.out.println("Orh I remind you later to " + command);
                System.out.println("What else you want?");
            }
        }
    }

    public static void main(String[] args) {
        new Doris().start();
    }
}
