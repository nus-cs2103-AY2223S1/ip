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
            if (input.equals("bye")) {
                IOhelper.print("Bye. Hope to see you again soon!");
                break;
            }
            try {
                this.inputDecode(input);
            } catch (DukeException e){
                IOhelper.print(e.getMessage());
            }
        }
    }

    public void inputDecode(String input) throws DukeException{
        String arr[] = input.split(" ", 2);
        String command = arr[0];

        if(command.equals("list")) {
            this.list();
            return;
        }
        if(arr.length < 2) {
            throw DukeException.invalidCommand;
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
            case "delete":
                this.delete(argument);
                break;
            default:
                throw DukeException.invalidCommand;
        }
    }

    public void todo(String argument) {
        Task item = Task.ToDo(argument);
        listOfTasks.add(item);
        IOhelper.print("added: " + item);
        IOhelper.print("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void deadline(String argument) throws DukeException {
        Task item = Task.Deadline(argument);
        listOfTasks.add(item);
        IOhelper.print("added: " + item);
        IOhelper.print("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void event(String argument) throws DukeException {
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

    public void mark(String argument) throws DukeException{
        try {
            int id = Integer.parseInt(argument);
            if(listOfTasks.size() < id) {
              throw DukeException.idTooBig;
            }
            listOfTasks.get(id - 1).changeMark(true);
            IOhelper.print("Nice! I've marked this task as done:\n" +
                    listOfTasks.get(id - 1));
        } catch (NumberFormatException e){
            throw DukeException.invalidArgument;
        }
    }

    public void unmark(String argument) throws DukeException{
        try {
            int id = Integer.parseInt(argument);
            if(listOfTasks.size() < id) {
                throw DukeException.idTooBig;
            }
            listOfTasks.get(id - 1).changeMark(false);
            IOhelper.print("OK, I've marked this task as not done yet:\n" +
                    listOfTasks.get(id - 1));
        } catch (NumberFormatException e){
            throw DukeException.invalidArgument;
        }
    }

    public void delete(String argument) throws DukeException{
        try {
            int id = Integer.parseInt(argument);
            if(listOfTasks.size() < id) {
                throw DukeException.idTooBig;
            }
            Task deletedTask = listOfTasks.get(id - 1);
            listOfTasks.remove(id - 1);
            IOhelper.print("Noted. I've removed this task:\n" +
                    deletedTask);
            IOhelper.print("Now you have " + listOfTasks.size() + " tasks in the list.");
        } catch (NumberFormatException e){
            throw DukeException.invalidArgument;
        }
    }
}
