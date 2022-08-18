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
                if (index == numOfTasks) {
                    list = list + index + ". " + taskList.readTask(i);
                } else {
                    list = list + index + ". " + taskList.readTask(i) + "\n";
                }
            }
            return list;
        } else {
            taskList.addTask(s);
            return "I have added: " + s + " into the task list.";
        }
    }
}
