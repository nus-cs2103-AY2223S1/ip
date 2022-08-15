package Duke;

import javax.swing.text.html.parser.Parser;

public class EventHandler {
    private TaskList taskList;
    private UserInterface userInterface;

    public EventHandler(TaskList taskList, UserInterface userInterface) {
        this.taskList = taskList;
        this.userInterface = userInterface;
    }

    public String handleMark(String echo){
//        System.out.println("marking");
        int index = Integer.parseInt(String.valueOf(echo.charAt(5))) -1;
        //System.out.println(index);
        TaskList.taskList.get(index).markAsDone();
        return userInterface.printMark(index);
    }

    public String handleUnmark(String echo) {
        //System.out.println("unmarking");
        int index = Integer.parseInt(String.valueOf(echo.charAt(7))) -1;
       // System.out.println(index);
        TaskList.taskList.get(index).UnmarkAsDone();
        return userInterface.printUnmark(index);
    }

    public String addTask(Task task){
        taskList.add(task);
        return userInterface.printTask(task);
    }
}
