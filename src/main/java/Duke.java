import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String INTRO = DIVIDER + "\n" + "Hello! I'm Duke!\n" + "What can I do for you?\n" + DIVIDER;
    private static final String OUTRO = DIVIDER + "\n" + "Bye. Hope to see you again soon!\n" + DIVIDER;

    private static final String EXIT_KEYWORD = "bye";
    private static final String LIST_KEYWORD = "list";
    private static final String MARK_KEYWORD = "mark";
    private static final String UNMARK_KEYWORD = "unmark";
    private static final String TODO_KEYWORD = "todo";
    private static final String DEADLINE_KEYWORD = "deadline";
    private static final String EVENT_KEYWORD = "event";

    public static void main(String[] args) {
        System.out.println(INTRO);

        Scanner scanner = new Scanner(System.in);
        DukeList list = new DukeList();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(EXIT_KEYWORD)) {
                // Exit Duke
                scanner.close();
                break;
            } else {
                System.out.println(DIVIDER);
                if (input.equals(LIST_KEYWORD)) {
                    // Print list
                    System.out.println(list);
                } else if (input.startsWith(MARK_KEYWORD)) {
                    // Mark task as done
                    System.out.println(list
                            .done(Integer.parseInt(input.substring(4).trim())));
                } else if (input.startsWith(UNMARK_KEYWORD)) {
                    // Mark task as undone
                    System.out.println(list
                            .undone(Integer.parseInt(input.substring(6).trim())));
                } else {
                    // Add some task to list
                    if (input.startsWith(TODO_KEYWORD)) {
                        // Add task as to do
                        Todo t = new Todo(input.substring(5));
                        System.out.println(list.add(t));
                    } else if (input.startsWith(DEADLINE_KEYWORD)) {
                        // Add task as deadline
                        String data = input.substring(8);

                        int splitIndex = data.indexOf("/");
                        if (splitIndex == -1) {
                            System.out.println("Please enter valid deadline task.");
                        } else {
                            String description = data.substring(0, splitIndex).trim();
                            String dateTime = data.substring(splitIndex + 3).trim();

                            Deadline d = new Deadline(description, dateTime);
                            System.out.println(list.add(d));
                        }
                    } else if (input.startsWith(EVENT_KEYWORD)) {
                        // Add task as event
                        String data = input.substring(5);

                        int splitIndex = data.indexOf("/");
                        if (splitIndex == -1) {
                            System.out.println("Please enter valid event task.");
                        } else {
                            String description = data.substring(0, splitIndex).trim();
                            String dateTime = data.substring(splitIndex + 3).trim();

                            Event e = new Event(description, dateTime);
                            System.out.println(list.add(e));
                        }
                    } else {
                        // Add item to list
                        Task t = new Task(input);
                        System.out.println(list.add(t));
                    }
                }
                System.out.println(DIVIDER);
            }
        }

        System.out.println(OUTRO);
    }
}