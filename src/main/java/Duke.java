import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    public final static String initMsg = "Hello! I'm Duke \n What can I do for you?";
    public final static String byeMsg = "Bye. Hope to see you soon again!";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskList lst = new TaskList();
        printMessage(initMsg);
        while (true) {
            String input = scan.nextLine();
            String[] parse = input.split(" ", 2);
            if (parse[0].equals("bye")) {
                break;
            }
            switch (parse[0]) {
                case "list":
                    lst.printList();
                    break;
                case "mark":
                    lst.markTask(parseInt(parse[1]));
                    break;
                case "unmark":
                    lst.unmarkTask(parseInt(parse[1]));
                    break;
                case "todo":
                    lst.addTask(new Task(parse[1], Task.taskType.T));
                    break;
                case "deadline":
                    String[] parse2 = parse[1].split("/by");
                    lst.addTask(new Task(parse2[0], Task.taskType.D, parse2[1]));
                    break;
                case "event":
                    String[] parse3 = parse[1].split("/at");
                    lst.addTask(new Task(parse3[0], Task.taskType.E, parse3[1]));
                    break;
            }
        }
        scan.close();
        printMessage(byeMsg);
    }

    public static void printMessage(String s) {
        System.out.println("--------------------------------------");
        System.out.println(s);
        System.out.println("--------------------------------------");
    }
}
