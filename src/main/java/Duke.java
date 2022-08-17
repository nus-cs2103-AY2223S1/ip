import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        SkeletonDuke duke = new SkeletonDuke();
        duke.greet();
        while(sc.hasNext()) {
            String command = sc.next();
            if(command.equals("bye")) {
                duke.exit();
                break;
            } else {
                duke.echo(command);
            }
        }

    }
}
