import java.time.LocalDate;

public class ChatBot {
    public static final String NAME = "Duke";
    private TaskList taskList;
    public ChatBot(){
        String greetingMessage = "Hello! I'm " + NAME + "\n"
                + "What can I do for you?\n";
        System.out.println(greetingMessage);
//        this.taskList = new TaskList();
        try {
            this.taskList = FileDataHandler.load();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void echo (String input) {
        try {
            if ("bye".equalsIgnoreCase(input)) {
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(0);
            } else if ("list".equalsIgnoreCase(input)) {
                this.taskList.printList();
            } else if (input.toLowerCase().contains("unmark")) {
                String[] arguments = input.split(" ");
                int taskNo = Integer.parseInt(arguments[1]);
                this.taskList.unMarkTask(taskNo);
            } else if (input.toLowerCase().contains("mark")) {
                String[] arguments = input.split(" ");
                int taskNo = Integer.parseInt(arguments[1]);
                this.taskList.markTask(taskNo);
            } else if (input.toLowerCase().contains("todo")) {
                //Split the string into maximum two parts only
                String[] arguments = input.split(" ", 2);
                if (arguments.length > 1) {
                    String toDo = arguments[1];
                    this.taskList.addTask(new ToDos(toDo));
                } else {
                    throw new EmptyDescriptionException();
                }
            } else if (input.toLowerCase().contains("deadline")) {
                //Remove the first 9 string which is deadline + space
                String deadlineInput = input.substring(9);
                String[] arguments = deadlineInput.split("/");
                if (arguments.length > 1) {
                    String description = arguments[0];
                    String date = extractDateByKeyword("by",arguments[1]);
                    this.taskList.addTask(new Deadlines(description, date));
                } else {
                    throw new EmptyDescriptionException();
                }
            } else if (input.toLowerCase().contains("event")) {
                String eventInput = input.substring(6);
                String[] arguments = eventInput.split("/");
                if (arguments.length > 1) {
                    String description = arguments[0];
                    String date = extractDateByKeyword("at",arguments[1]);
                    this.taskList.addTask(new Events(description, date));
                } else  {
                    throw new EmptyDescriptionException();
                }
            } else if (input.toLowerCase().contains("date")) {
                String[] arguments = input.split(" ");
                LocalDate date = LocalDate.parse(arguments[1]);
                this.taskList.printTasksOnSpecificDate(date);

            } else if (input.toLowerCase().contains("delete")) {
                String[] arguments = input.split(" ");
                int taskNo = Integer.parseInt(arguments[1]);
                this.taskList.deleteTask(taskNo);
            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String extractDateByKeyword (String keyword, String text) {
        String[] args = text.split(keyword);
        String date = args[1].trim();
        return date;
    }

}
