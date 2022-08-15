import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        wrapPrint(greeting);
        repeat();
    }

    private static void wrapPrint(String toPrint) {
        System.out.println("    ____________________________________________________________");
        System.out.println(toPrint);
        System.out.println("    ____________________________________________________________");
    }

    private static String leftPad(String toPrint) {
        return "     " + toPrint;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static void repeat() {
        Task[] taskList = new Task[100];
        int i = 0;
        String input = "";
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            String[] inputSplit = input.split(" ");
            switch(inputSplit[0]) {
                case "bye":
                    wrapPrint(leftPad("Bye. Hope to see you again soon!"));
                    return;
                case "list":
                    StringBuilder listString = new StringBuilder(leftPad("Here are the tasks in your list:\n"));
                    for (int j = 0; j < i; j++) {
                        listString.append(leftPad(String.format("%d.", j + 1)));
                        listString.append(taskList[j].toString());
                        listString.append("\n");
                    }
                    if (listString.length() > 0) {
                        listString.setLength(listString.length() - 1);
                    }
                    wrapPrint(listString.toString());
                    break;
                case "mark":
                    if (inputSplit.length < 2 || !isNumeric(inputSplit[1])) return;
                    int index = Integer.parseInt(inputSplit[1]) - 1;
                    taskList[index].markDone();
                    wrapPrint(leftPad("Nice! I've marked this task as done:\n  ") + leftPad(taskList[index].toString()));
                    break;
                case "unmark":
                    if (inputSplit.length < 2 || !isNumeric(inputSplit[1])) return;
                    index = Integer.parseInt(inputSplit[1]) - 1;
                    taskList[index].unmarkDone();
                    wrapPrint(leftPad("OK, I've marked this task as not done yet:\n  ") + leftPad(taskList[index].toString()));
                    break;
                default:
                    taskList[i] = new Task(input);
                    i++;
                    wrapPrint(leftPad("added: " + input));
            }
        }
    }
}
