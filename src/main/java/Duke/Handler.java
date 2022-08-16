package Duke;

public class Handler {

    private TaskList taskList;
    private UserInterface userInterface;

    public Handler(TaskList taskList, UserInterface userInterface) {
        this.taskList = taskList;
        this.userInterface = userInterface;
    }

    public String handleMark(String echo) {
        int index = Integer.parseInt(String.valueOf(echo.charAt(5))) -1;
        TaskList.taskList.get(index).markAsDone();
        return userInterface.printMark(index);
    }

    public String handleUnmark(String echo) {
        int index = Integer.parseInt(String.valueOf(echo.charAt(7))) -1;
        TaskList.taskList.get(index).UnmarkAsDone();
        return userInterface.printUnmark(index);
    }

    public String handleToDo(String echo) {
        String task = echo.substring(5);
        ToDo todo = new ToDo(task);
        TaskList.taskList.add(todo);
        return userInterface.printTask(todo);
    }

    public String handleDeadline(String echo) {
        String time = echo.substring(echo.indexOf("/") + 4);
        String task = echo.substring(9,echo.indexOf("/"));
        Deadline deadline = new Deadline(task,time);
        TaskList.taskList.add(deadline);
        return userInterface.printTask(deadline);
    }

    public String handleEvent(String echo) {
        String time = echo.substring(echo.indexOf("/") + 4);
        String task = echo.substring(6,echo.indexOf("/"));
        Event event = new Event(task,time);
        TaskList.taskList.add(event);
        return  userInterface.printTask(event);
    }

    public String addTask(Task task){
        taskList.add(task);
        return userInterface.printTask(task);
    }
}
