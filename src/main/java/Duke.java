import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public void start(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        IOhelper.print("Hello from\n" + logo);

        while(true) {
            String input = IOhelper.read();
            if(input.equals("bye")) {
                IOhelper.print("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                int id = 0;
                for(Task task : listOfTasks) {
                    id++;
                    IOhelper.print(id + ": " + task);
                }
                continue;
            }
            listOfTasks.add(new Task(input));
            IOhelper.print("added: " + input);
        }
    }
}
