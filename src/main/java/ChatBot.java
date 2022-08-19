public class ChatBot {
    public static final String NAME = "Duke";
    private TaskList taskList;
    public ChatBot(){
        String greetingMessage = "Hello! I'm " + NAME + "\n"
                + "What can I do for you?\n";
        System.out.println(greetingMessage);
        this.taskList = new TaskList();
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
                String[] arguments = input.split(" ");
                if (arguments.length > 1) {
                    String toDo = arguments[1];
                    this.taskList.addTask(new ToDos(toDo));
                } else {
                    throw new EmptyDescriptionException();
                }
            } else if (input.toLowerCase().contains("deadline")) {
                String[] arguments = input.split("/");
                String description = arguments[0];
                String date = arguments[1];
                this.taskList.addTask(new Deadlines(description, date));
            } else if (input.toLowerCase().contains("event")) {
                String[] arguments = input.split("/");
                String description = arguments[0];
                String date = arguments[1];
                this.taskList.addTask(new Events(description, date));
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

}
