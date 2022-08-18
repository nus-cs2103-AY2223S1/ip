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
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("Eh what you want?");

        while (true) {
            String command = sc.nextLine();

            switch (command) {
                case "bye":
                    System.out.println("Bye you annoying sia");
                    return;
                case "list":
                    System.out.println("Oi don't laze la");
                    System.out.println("You need to:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i).getDescription());
                    }
                    break;
                default:
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
