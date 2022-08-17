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
                Scanner words = new Scanner(lastLine);
                if (lastLine.equals("list")) {
                    taskList.show();
                } else {
                    String cmd = words.next();
                    String desc = "";
                    String prev = "";
                    switch (cmd) {
                        case "mark":
                            taskList.mark(Integer.parseInt(words.next()));
                            break;
                        case "unmark":
                            taskList.unmark(Integer.parseInt(words.next()));
                            break;
                        case "todo":
                            desc = words.nextLine().substring(1);
                            taskList.add(new ToDo(desc));
                            break;
                        case "deadline":
                            prev = words.next();
                            while (!prev.equals("/by")) {
                                desc += prev;
                                prev = words.next();
                            }
                            String deadline = words.nextLine().substring(1);
                            taskList.add(new Deadline(desc, deadline));
                            break;
                        case "event":
                            prev = words.next();
                            while (!prev.equals("/at")) {
                                desc += prev;
                                prev = words.next();
                            }
                            String period = words.nextLine().substring(1);
                            taskList.add(new Event(desc, period));
                            break;
                        default:
                            taskList.add(new Task(lastLine));
                            System.out.println("Added: " + "\"" + lastLine + "\"");
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