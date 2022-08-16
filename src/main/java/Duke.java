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

        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoText = sc.next();
            if (echoText.equals("bye")) {
                System.out.println(horizontalLine + "K finally, good riddance! \n" + horizontalLine);
                break;
            } else if (echoText.equals("list")) {
                System.out.println(horizontalLine);
                if (list.size() == 0) {
                    System.out.println("pff there is nothing in your list");
                } else {
                    System.out.println("Here are your dumb tasks in your list:");
                    for (int i = 1; i < list.size() + 1; i++) {
                        Task item = list.get(i - 1);
                        String itemStr = i + ". [" + item.getStatusIcon() + "] " + item.getDescription();
                        System.out.println(itemStr);
                    }
                }
                System.out.println(horizontalLine);
            } else if (echoText.equals("mark")) {
                int markValue = sc.nextInt();
                Task item = list.get(markValue - 1);
                item.markAs(true);
                System.out.println(horizontalLine + "Took you long enough to complete this task:");
                System.out.println("[X] " + item.getDescription() + "\n" + horizontalLine);
            } else if (echoText.equals("unmark")) {
                int markValue = sc.nextInt();
                Task item = list.get(markValue - 1);
                item.markAs(false);
                System.out.println(horizontalLine + "Another task marked as not done?? Slow indeed");
                System.out.println("[ ] " + item.getDescription() + "\n" + horizontalLine);
            } else {
                Task t = new Task(echoText);
                list.add(t);
                System.out.println(horizontalLine + adddedStr + echoText + "\n" + horizontalLine);
            }

        }

    }
}
