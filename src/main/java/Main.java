import utils.IOUtils;

import java.util.Scanner;

public class Main {
    public static String logo = ".__ .  ..  ..___" + "\n" +
            "|  \\|  ||_/ [__ " + "\n" +
            "|__/|__||  \\[___" + "\n";

    public static String name = "Duke";

    public static void main(String[] args) {
        System.out.println(Main.logo);
        ConversationHandler handler = new ConversationHandler();

        Scanner in = new Scanner(System.in);

        while (handler.isOpen() && in.hasNext()) {
            String line = in.nextLine();
            String output = handler.handleCommand(line);
            IOUtils.printContentWithHR(output);
        }


    }


}
