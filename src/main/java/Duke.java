import java.util.NoSuchElementException;
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
                Scanner options = new Scanner(lastLine);
                if (lastLine.equals("list")) {
                    taskList.show();
                } else {
                    String cmd = options.next();
                    switch (cmd) {
                        case "mark":
                            try {
                                taskList.mark(options);
                            } catch (MissingOptions | InvalidIndex e) {
                                System.out.println(e);
                            }
                            break;
                        case "unmark":
                            try {
                                taskList.unmark(options);
                            } catch (MissingOptions | InvalidIndex e) {
                                System.out.println(e);
                            }
                            break;
                        case "todo":
                            try {
                                taskList.add(new ToDo(options));
                            } catch (MissingOptions e) {
                                System.out.println(e);
                            }
                            break;
                        case "deadline":
                            options.useDelimiter(" /by ");
                            try {
                                taskList.add(new Deadline(options));
                            } catch (MissingOptions e) {
                                System.out.println(e);
                            } catch (NoSuchElementException e) {
                                System.out.println("No event description specified!");
                            }
                            break;
                        case "event": 
                            options.useDelimiter(" /at ");
                            try {
                                taskList.add(new Event(options));
                            } catch (MissingOptions e) {
                                System.out.println(e);
                            } catch (NoSuchElementException e) {
                                System.out.println("No event description specified!");
                            }
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