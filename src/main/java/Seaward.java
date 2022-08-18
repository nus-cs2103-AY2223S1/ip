public class Seaward {

    private final static String welcome = "Hello! I'm Seaward,\n" +
            "your friendly neighbourhood chatbot. \n" +
            "Type something and I will reply!";
    private static TaskList taskList;

//    private String inputString;

    public Seaward() {
        taskList = new TaskList();
    }

    public String getWelcome() {
        return welcome;
    }

    public String readInputString(String s) {
        if (s.equals("bye")) {
            return "Seaward out!";
        } else if (s.equals("list")){
            int numOfTasks = taskList.getNumOfTasks();
            String list = "";
            for (int i = 0; i < numOfTasks; i++) {
                int index = i + 1;
                String taskDescription = taskList.readTask(i);
                String isDone = taskList.readStatus(i);
                if (index == numOfTasks) {
                    list = list + index + "." + taskDescription;
                } else {
                    list = list + index + "." + taskDescription + "\n";
                }
            }
            return list;
        } else if (s.startsWith("mark")) {
            int index = Integer.parseInt(s.substring(5)) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                return "This task doesn't exist!";
            }
            taskList.setCompleted(index);
            return "I have marked this task as done:\n" +
                    taskList.readTask(index);
        } else if (s.startsWith("unmark")) {
            int index = Integer.parseInt(s.substring(7)) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                return "This task doesn't exist!";
            }
            taskList.setNotCompleted(index);
            return "I have marked this task as undone:\n" +
                    taskList.readTask(index);
        } else if (s.startsWith("todo")) {
            taskList.addTodo(s.substring(5));
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        } else if (s.startsWith("deadline")) {
            taskList.addDeadline(s.substring(9));
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        } else if (s.startsWith("event")) {
            taskList.addEvent(s.substring(6));
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        } else {
            taskList.addTask(s);
            return "I have added: " + s + " into the task list.";
        }
    }
}
