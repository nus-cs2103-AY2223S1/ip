import java.util.Scanner;

public class Duke {
    private static String lastLine = "";
    private static Scanner sc = new Scanner(System.in);
    private static Tasklist taskList= new Tasklist();
    public static void main(String[] args) {
        System.out.println("My name is [insert name here], how can I help?");
        while (true) {
            listen();
            if (lastLine.equals("bye")) {
                System.out.println("See you next time...");
                break;
            } else {
                String[] cmds = lastLine.split(" ");
                if (lastLine.equals("list")) {
                    taskList.show();
                } else if (cmds[0].equals("mark")) {
                    taskList.mark(Integer.parseInt(cmds[1]));
                } else if (cmds[0].equals("unmark")) {
                    taskList.unmark(Integer.parseInt(cmds[1]));
                } else {
                    taskList.add(lastLine);
                    System.out.println("Added: " + "\"" + lastLine + "\"");
                }
            }
        }
    }

    private static void listen() {
        lastLine = sc.nextLine();
    }

    private static void echoLast() {
        System.out.println(lastLine);
    }
}