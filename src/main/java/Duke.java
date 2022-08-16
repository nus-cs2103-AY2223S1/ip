import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "______________________________________ \n";
        String adddedStr = "added: ";

        String firstText = horizontalLine
                + "hi... I'm Karen \n"
                + "What do you want this time? \n"
                + horizontalLine;
        System.out.println(firstText);

        ArrayList<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoText = sc.nextLine();
            if (echoText.equals("bye")) {
                System.out.println(horizontalLine + "K finally, good riddance! \n" + horizontalLine);
                break;
            } else if (echoText.equals("list")) {
                System.out.println(horizontalLine);
                if (list.size() == 0) {
                    System.out.println("pff there is nothing in your list");
                } else {
                    for (int i = 1; i < list.size() + 1; i++) {
                        System.out.println(i + ". " + list.get(i - 1));
                    }
                }
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine + adddedStr + echoText + "\n" + horizontalLine);
                list.add(echoText);
            }

        }

    }
}
