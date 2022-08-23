import java.util.Scanner;

public class Duke {
    private static FileContainer fileContainer;
    private static TaskList taskList;
    public static void main(String[] args) {
    //    String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // the first time use should create a new file in the default location
        if ( Duke.fileContainer == null){
            Duke.fileContainer=new FileContainer();
            fileContainer.createAFile();
        }

        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");

        Scanner scanner=new Scanner(System.in);

        taskList = fileContainer.loadTasks();

        while(scanner.hasNextLine()){
            String ans=scanner.nextLine();
            try {
                if (ans.equals("bye")) {
                    System.out.println("Bye. Hope to see you soon.");
                    break;
                } else if (ans.equals("list")) {
                    System.out.println(taskList.listAllTask());
                } else if (ans.split(" ")[0].equals("unmark")) {
                    taskList.markUndone(Integer.valueOf(ans.split(" ")[1]) - 1, fileContainer);
                } else if (ans.split(" ")[0].equals("mark")) {

                    taskList.markAsDone(Integer.valueOf(ans.split(" ")[1]) - 1, fileContainer);
                } else if (ans.split(" ")[0].equals("delete")){
                    taskList.delete(Integer.valueOf(ans.split(" ")[1])
                            , fileContainer);
                } else if (ans.split(" ")[0].equals("todo")
                        || ans.split(" ")[0].equals("deadline")
                        || ans.split(" ")[0].equals("event")){
                    taskList.addTask(Task.createATask(ans));
                    fileContainer.updateFile(taskList.getTaskList());
                } else if (ans.split(" ")[0].equals("Get")) {
                    System.out.println(taskList.getASpecificDay(ans));
                } else{
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }catch (DukeException de){
                System.out.println(de.getMessage());
            }
        }
    }
}
