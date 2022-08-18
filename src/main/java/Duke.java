import java.util.Scanner;

public class Duke {
    
    public static void chat(){
        Scanner myScan = new Scanner(System.in);
        String s;
        Task[] list = new Task[100];
        int list_counter = 0;

        while (true) {

            try {
                s = myScan.nextLine();
                if (s.equals("bye")) {
                    System.out.println("----------------------");
                    System.out.println("Bye, hope to see you again!");
                    System.out.println("----------------------");
                    break;

                } else if (s.equals("list")) {
                    System.out.println("----------------------");
                    for (int i = 0; i < 100; i++) {
                        if (list[i] != null) {
                            String display = String.format("%d.%s", i + 1, list[i].toString());
                            System.out.println(display);
                        }
                    }
                    System.out.println("----------------------");

                } else if (s.indexOf("mark") == 0) {
                    String subString = s.substring(5, s.length());
                    int a = Integer.parseInt(subString) - 1;
                    list[a].markDone();

                    System.out.println("----------------------");
                    System.out.println("Ok! I've marked this task as done");
                    System.out.println(list[a].toString());
                    System.out.println("----------------------");

                } else if (s.indexOf("unmark") == 0) {
                    String subString = s.substring(7, s.length());
                    int a = Integer.parseInt(subString) - 1;
                    list[a].markUndone();

                    System.out.println("----------------------");
                    System.out.println("Ok! I've marked this task as undone");
                    System.out.println(list[a].toString());
                    System.out.println("----------------------");

                } else if (s.indexOf("todo") == 0) {
                    if (s.length() < 5) {
                        throw new EmptyDescriptionException("Empty description", "todo");
                    }
                    String subString = s.substring(5, s.length());
                    list[list_counter] = new Todo(subString);
                    list_counter += 1;
                    System.out.println("----------------------");
                    System.out.println("added: " + list[list_counter - 1].toString());
                    System.out.println(String.format("Now you have %d tasks in the list", list_counter));
                    System.out.println("----------------------");

                } else if (s.indexOf("deadline") == 0 ) {
                    if (s.length() < 9) {
                        throw new EmptyDescriptionException("Empty description", "deadline");
                    } else if (!s.contains("/by")) {
                        throw new InvalidFormatException("Invalid format");
                    } else if (s.indexOf("/by") + 3 == s.length()) {
                        throw new InvalidFormatException("Invalid format");
                    }

                    String descript = s.substring(9, s.indexOf("/") - 1);
                    String by = s.substring(s.indexOf("/") + 4);
                    list[list_counter] = new Deadline(descript, by);
                    list_counter += 1;
                    System.out.println("----------------------");
                    System.out.println("added: " + list[list_counter - 1].toString());
                    System.out.println(String.format("Now you have %d tasks in the list", list_counter));
                    System.out.println("----------------------");

                } else if (s.indexOf("event") == 0 ) {
                    if (s.length() < 6) {
                        throw new EmptyDescriptionException("Empty description", "event");
                    } else if (!s.contains("/at")) {
                        throw new InvalidFormatException("Invalid format");
                    } else if (s.indexOf("/at") + 3 == s.length()) {
                        throw new InvalidFormatException("Invalid format");
                    }

                    String descript = s.substring(6, s.indexOf("/") - 1);
                    String at = s.substring(s.indexOf("/") + 4);
                    list[list_counter] = new Event(descript, at);
                    list_counter += 1;
                    System.out.println("----------------------");
                    System.out.println("added: " + list[list_counter - 1].toString());
                    System.out.println(String.format("Now you have %d tasks in the list", list_counter));
                    System.out.println("----------------------");

                } else {
                    throw new InvalidTaskException("No valid task descriptor");
                }

            } catch (EmptyDescriptionException e) {
                System.out.println("----------------------");
                System.out.println(e.toString());
                System.out.println("----------------------");
            } catch (InvalidTaskException e) {
                System.out.println("----------------------");
                System.out.println(e.toString());
                System.out.println("----------------------");
            } catch (InvalidFormatException e) {
                System.out.println("----------------------");
                System.out.println(e.toString());
                System.out.println("----------------------");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("----------------------");

        chat();
    }
}
