import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void echoUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            } else {
                System.out.println("\t-----------------------------------------------");
                System.out.println("\t" + line);
                System.out.println("\t-----------------------------------------------");
            }
        }
    }

    public static void echoBackList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        String[] lst = new String[100];
        int counter = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            } else if (line.equals("list")) {
              if (counter > 0) {
                  System.out.println("\t-----------------------------------------------");
                  for (int i = 0; i < counter; i++) {
                      System.out.println("\t"+ (i+1) + ". " + lst[i] + "\n");
                  }
                  System.out.println("\t-----------------------------------------------");
              } else { // lst is not initalized
                  System.out.println("\t-----------------------------------------------");
                  System.out.println("\tList is empty!");
                  System.out.println("\t-----------------------------------------------");
              }
            } else {
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tadded: " + line);
                System.out.println("\t-----------------------------------------------");
                if (counter == 0) {
                    lst = new String[100];
                }
                lst[counter] = line;
                counter++;
            }
        }

    }

    public static void listWithStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        ArrayList<Task> lst = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            } else if (line.equals("list")) {
                if (!lst.isEmpty()) {
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tHere are the tasks in your list:");

                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println("\t"+ (i+1) + ". " + lst.get(i) + "\n");
                    }
                    System.out.println("\t-----------------------------------------------");
                } else { // lst is not initalized
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tList is empty!");
                    System.out.println("\t-----------------------------------------------");
                }
            } else if (line.startsWith("unmark") && line.length() > 6) {
                int index = Integer.parseInt(line.replaceAll("[^0-9]", "")); // regex gotten from https://stackoverflow.com/a/14974126
                lst.get(index - 1).markAsUndone();
              System.out.println("\tNice! I've marked this task as not done yet:");
              System.out.println("\t" + lst.get(index - 1));
            }
            else if (line.startsWith("mark") && line.length() > 4) {
                int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                lst.get(index - 1).markAsDone();
              System.out.println("\tNice! I've marked this task as done:");
              System.out.println("\t" + lst.get(index - 1));
            }
            else { // add task into lst
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tadded: " + line);
                System.out.println("\t-----------------------------------------------");
                lst.add(new Task(line));
            }
        }

    }

    public static void level4() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        ArrayList<Task> lst = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            }
            else if (line.equals("list")) {
                if (!lst.isEmpty()) {
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println("\t"+ (i+1) + ". " + lst.get(i));
                    }
                    System.out.println("\t-----------------------------------------------");
                } else { // lst is not initalized
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tList is empty!");
                    System.out.println("\t-----------------------------------------------");
                }
            }
            else if (line.startsWith("unmark") && line.length() > 6) {
                int index = Integer.parseInt(line.replaceAll("[^0-9]", "")); // regex gotten from https://stackoverflow.com/a/14974126
                lst.get(index - 1).markAsUndone();
                System.out.println("\tNice! I've marked this task as not done yet:");
                System.out.println("\t" + lst.get(index - 1));
            }
            else if (line.startsWith("mark") && line.length() > 4) {
                int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                lst.get(index - 1).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + lst.get(index - 1));
            }
            else if (line.startsWith("todo")) {
                Todo todo = new Todo(line.replace("todo ", ""));
                lst.add(todo);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + todo);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else if (line.startsWith("deadline")) {
                int idxOfBy = line.indexOf("/by");
                Deadline deadline = new Deadline(line.substring(9, idxOfBy), line.substring(idxOfBy + 4));
                lst.add(deadline);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + deadline);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else if (line.startsWith("event")) {
                int idxOfAt = line.indexOf("/at");
                Event event = new Event(line.substring(6, idxOfAt), line.substring(idxOfAt + 4));
                lst.add(event);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + event);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else { // add task into lst
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tadded: " + line);
                System.out.println("\t-----------------------------------------------");
                lst.add(new Task(line));
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
