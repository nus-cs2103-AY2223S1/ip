public class Seaward {

    private final static String welcome = "Hello! I'm Seaward,\n" +
            "your friendly neighbourhood chatbot.\n" +
            "Type something and I will reply!";
    private static TaskList taskList;

//    private String inputString;

    public Seaward() {
        taskList = new TaskList();
    }

    public String getWelcome() {
        return welcome;
    }

    public String readInputString(String s) throws InvalidCommandException, InvalidDescriptionException {
        String[] splitCommand = s.split(" ", 2);
        String command = splitCommand[0];
        if (command.equals("bye")) {
            return "Seaward out!";
        } else if (command.equals("list")){
            int numOfTasks = taskList.getNumOfTasks();
            String list = "";
            for (int i = 0; i < numOfTasks; i++) {
                int index = i + 1;
                String taskDescription = taskList.readTask(i);
                if (index == numOfTasks) {
                    list = list + index + "." + taskDescription;
                } else {
                    list = list + index + "." + taskDescription + "\n";
                }
            }
            return list;
        } else if (command.equals("mark")) {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            taskList.setCompleted(index);
            return "I have marked this task as done:\n" +
                    taskList.readTask(index);
        } else if (command.equals("unmark")) {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            taskList.setNotCompleted(index);
            return "I have marked this task as undone:\n" +
                    taskList.readTask(index);
        } else if (command.equals("delete")) {
            int index = Integer.parseInt(splitCommand[1]) - 1;
            int numOfTasks = taskList.getNumOfTasks();
            int newNumOfTasks = numOfTasks - 1;
            if (index + 1 > numOfTasks) {
                throw new InvalidCommandException("Task does not exist.");
            }
            String result = "Noted. I have removed this task:\n" + taskList.readTask(newNumOfTasks)
                    + "\n" + "Now you have "
                    + newNumOfTasks + " task(s) in your list.";
            taskList.deleteTask(index);
            return result;
        } else if (command.equals("todo")) {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description.");
            }
            taskList.addTodo(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        } else if (command.equals("deadline")) {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and deadline.");
            }
            taskList.addDeadline(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        } else if (command.equals("event")) {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and time/date.");
            }
            taskList.addEvent(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        } else {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
