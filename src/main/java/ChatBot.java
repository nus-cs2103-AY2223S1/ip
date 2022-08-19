public class ChatBot {
    public static final String NAME = "Duke";
    private ToDoList toDoList;
    public ChatBot(){
        String greetingMessage = "Hello! I'm " + NAME + "\n"
                + "What can I do for you?\n";
        System.out.println(greetingMessage);
        this.toDoList = new ToDoList();
    }

    public void echo (String input) {
        if ("bye".equalsIgnoreCase(input)) {
            System.out.println("Bye. Hope to see you again soon!\n");
            System.exit(0);
        } else if ("list".equalsIgnoreCase(input)) {
            this.toDoList.printList();
        } else if (input.toLowerCase().contains("unmark")) {
            String[] arguments = input.split(" ");
            int taskNo = Integer.parseInt(arguments[1]);
            this.toDoList.unMarkTask(taskNo);
        } else if (input.toLowerCase().contains("mark")) {
            String[] arguments = input.split(" ");
            int taskNo = Integer.parseInt(arguments[1]);
            this.toDoList.markTask(taskNo);
        } else {
            this.toDoList.addTask(new Task(input));
            System.out.println(NAME + ": Added: " + input + "\n");
        }
    }
}
