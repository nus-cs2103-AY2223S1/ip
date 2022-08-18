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
            this.inputDecode(input);
        }
    }

    public void inputDecode(String input) {
        String arr[] = input.split(" ", 2);
        String command = arr[0];

        if(command.equals("list")) {
            this.list();
            return;
        }

        String argument = arr[1];
        switch(command) {
            case "todo":
                todo(argument);
                break;
            case "deadline":
                deadline(argument);
                break;
            case "event":
                event(argument);
                break;
            case "mark":
                this.mark(argument);
                break;
            case "unmark":
                this.unmark(argument);
                break;
        }
    }

    public void todo(String argument) {
        Task item = Task.ToDo(argument);
        listOfTasks.add(item);
        IOhelper.print("added: " + item);
        IOhelper.print("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void deadline(String argument) {
        Task item = Task.Deadline(argument);
        listOfTasks.add(item);
        IOhelper.print("added: " + item);
        IOhelper.print("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void event(String argument) {
        Task item = Task.Event(argument);
        listOfTasks.add(item);
        IOhelper.print("added: " + item);
        IOhelper.print("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void list() {
        for(int i = 0; i < listOfTasks.size(); i++) {
            IOhelper.print((i + 1) + ". " + listOfTasks.get(i));
        }
    }

    public void mark(String argument) {
        int id = Integer.parseInt(argument);
        listOfTasks.get(id - 1).changeMark(true);
        IOhelper.print("Nice! I've marked this task as done:\n" +
                listOfTasks.get(id - 1));
    }

    public void unmark(String argument) {
        int id = Integer.parseInt(argument);
        listOfTasks.get(id - 1).changeMark(false);
        IOhelper.print("OK, I've marked this task as not done yet:\n" +
                listOfTasks.get(id - 1));
    }
}
