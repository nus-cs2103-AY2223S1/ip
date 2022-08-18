import java.util.Scanner;

public class Duke {
    private static String lastLine = "";
    private static Scanner sc = new Scanner(System.in);
    private static Tasklist taskList= new Tasklist();
    public static void main(String[] args) {
        System.out.println("My name is [insert name here], how can I help?");
        while (true) {
            if (!sc.hasNextLine()) break;
            lastLine = sc.nextLine();
            if (lastLine.equals("bye")) {
                System.out.println("See you next time...");
                break;
            } else {
                Scanner words = new Scanner(lastLine);
                if (lastLine.equals("list")) {
                    taskList.show();
                } else {
                    String cmd = words.next();
                    switch (cmd) {
                        case "mark":
                            taskList.mark(words);
                            break;
                        case "unmark":
                            taskList.unmark(words);
                            break;
                        case "todo":
                            taskList.add(new ToDo(words.nextLine().substring(1)));
                            break;
                        case "deadline":
                            words.useDelimiter(" /by ");
                            taskList.add(new Deadline(words.next().substring(1), words.next()));
                            break;
                        case "event": 
                            words.useDelimiter(" /at ");
                            taskList.add(new Event(words.next().substring(1), words.next()));
                            break;
                        default: // Junk input.
                            System.out.println("Sorry, I don't know what that means!");
                    }
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