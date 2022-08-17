import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introParagraph = TextFormatter.formatLine("Hello! I'm Duke") +
                TextFormatter.formatLine("What can I do for you?");
        String formattedIntro = TextFormatter.formatParagraph(introParagraph);

        System.out.println(formattedIntro);

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        TaskList dukelist = new TaskList();

        while(!terminate) {
            try {
                String nextLine = sc.nextLine();
                // Regex "//s+" matches one or more spaces
                String[] splitted = nextLine.split("\\s+", 2);
                String command = splitted[0];
                switch (command) {
                    case "bye":
                        System.out.println(
                                TextFormatter.formatLinesIntoParagraph("Bye. Hope to see you again soon!")
                        );
                        terminate = true;
                        sc.close();
                        break;
                    case "list":
                        System.out.println(TextFormatter.formatParagraph(
                                dukelist.toString()
                        ));
                        break;
                    case "mark":
                        int index = Integer.parseInt(splitted[1]);
                        String markedItem = "  " + dukelist.markItem(index);
                        String markedText = "Nice! I've marked this task as done:";
                        System.out.println(TextFormatter.formatLinesIntoParagraph(
                                markedText,
                                markedItem
                        ));
                        break;
                    case "unmark":
                        index = Integer.parseInt(splitted[1]);
                        String unmarkedItem = "  " + dukelist.unmarkItem(index);
                        String unmarkedText = "OK, I've marked this task as not done yet:";
                        System.out.println(TextFormatter.formatLinesIntoParagraph(
                                unmarkedText,
                                unmarkedItem
                        ));
                        break;
                    case "delete":
                        index = Integer.parseInt(splitted[1]);
                        String deletedItem = "  " + dukelist.deleteItem(index);
                        String deleteText = "Noted. I've removed this task:";
                        String endLine = String.format(
                                "Now you have %d tasks in the list.",
                                dukelist.getTaskCount());
                        System.out.println(TextFormatter.formatLinesIntoParagraph(
                                deleteText,
                                deletedItem,
                                endLine
                        ));
                        break;
                    case "todo":
                        Todo.validateInput(splitted);
                        String todo = splitted[1];
                        String taskItem = "  " + dukelist.addTodo(todo);
                        String startLine = "Got it. I've added this task:";
                        endLine = String.format(
                            "Now you have %d tasks in the list.",
                            dukelist.getTaskCount());
                        System.out.println(TextFormatter.formatLinesIntoParagraph(
                            startLine,
                            taskItem,
                            endLine
                        ));
                        break;
                    case "deadline":
                        // Regex "\\s+/" matches one or more space followed by a /
                        String[] taskArgs = splitted[1].split("\\s+/",2);
                        taskItem = "  " + dukelist.addDeadline(taskArgs[0], taskArgs[1]);
                        startLine = "Got it. I've added this task:";
                        endLine = String.format(
                                "Now you have %d tasks in the list.",
                                dukelist.getTaskCount());
                        System.out.println(TextFormatter.formatLinesIntoParagraph(
                                startLine,
                                taskItem,
                                endLine
                        ));
                        break;
                    case "event":
                        // Regex "\\s+/" matches one or more space followed by a /
                        taskArgs = splitted[1].split("\\s+/",2);
                        taskItem = "  " + dukelist.addEvent(taskArgs[0], taskArgs[1]);
                        startLine = "Got it. I've added this task:";
                        endLine = String.format(
                                "Now you have %d tasks in the list.",
                                dukelist.getTaskCount());
                        System.out.println(TextFormatter.formatLinesIntoParagraph(
                                startLine,
                                taskItem,
                                endLine
                        ));
                        break;
                    default:
                        throw new DukeException(DukeException.ErrorCode.UNKNOWN_CMD);
                }
            } catch (DukeException e) {
                System.out.println(TextFormatter.formatLinesIntoParagraph(e.errorMessage()));
            }
        }
    }
}
