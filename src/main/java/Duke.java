import java.util.*;

public class Duke {
    public static void main(String[] args) throws MismatchInputException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        SkeletonDuke duke = new SkeletonDuke();
        duke.greet();
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strarr = str.split(" ");
            String command = strarr[0];
            if(command.equals("bye")) {
                duke.exit();
                break;
            } else if(command.equals("list")) {
                duke.getList();
            } else if(command.equals("mark")){
                int taskNo = Integer.parseInt(strarr[1]);
                duke.mark(taskNo);
            } else if(command.equals("unmark")){
                int taskNo = Integer.parseInt(strarr[1]);
                duke.unmark(taskNo);
            } else if(command.equals("todo") || command.equals("deadline") || command.equals("event")){
                duke.add(str);
            } else {
                throw new MismatchInputException(":( OOPS!!! I'm sorry, but I don't know what that means" );
            }
        }

    }
}
