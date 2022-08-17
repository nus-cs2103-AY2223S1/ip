import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
    //    String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");
        Scanner scanner=new Scanner(System.in);
        TaskList taskList=new TaskList();
        while(scanner.hasNextLine()){
            String ans=scanner.nextLine();
            if(ans.equals("bye")){
                System.out.println("Bye. Hope to see you soon.");
                break;
            }else if(ans.equals("list")){
                System.out.println(taskList.listAllTask());
            }else {
                taskList.addTask(ans);
            }
        }
    }
}
