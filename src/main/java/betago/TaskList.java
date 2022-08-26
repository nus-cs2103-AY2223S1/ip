package betago;

import betago.exceptions.EmptyListException;
import betago.exceptions.InvalidCommandException;
import betago.exceptions.InvalidDataFileException;
import betago.tasks.Deadline;
import betago.tasks.Event;
import betago.tasks.Task;
import betago.tasks.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.FileWriter;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void listItems() throws EmptyListException {
        if (list.size() == 0) {
            throw new EmptyListException("There are no items in the list.");
        }
        System.out.print("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.print(i+1);
            System.out.println(". " + this.list.get(i).toString());
        }
        System.out.print("\n");
    }

    public void markUnmarkItems(String str) throws InvalidCommandException {
        String[] inputs = str.split(" ", 2);
        if (inputs.length != 2) {
            throw new InvalidCommandException("No task number indicated.");
        } else {
            try{
                int marker = Integer.valueOf(inputs[1]);
                if (marker < 1 || marker > this.list.size()) {
                    Ui.printInvalidMarkerError();
                } else if (inputs[0].equalsIgnoreCase("mark")){
                    this.list.get(marker - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(this.list.get(marker-1).toString() + "\n");
                } else {
                    this.list.get(marker - 1).markAsNotDone();
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println(this.list.get(marker-1).toString() + "\n");
                }
            }
            catch (NumberFormatException ex){
                throw new InvalidCommandException("Invalid item to be marked.");
            }
        }
    }

    public void addTodo(String str) throws InvalidCommandException{
        String[] inputs = str.split(" ", 2);
        if (inputs.length != 2) {
            throw new InvalidCommandException("No description stated.");
        } else {
            Todo temp = new Todo(inputs[1]);
            this.list.add(temp);
            System.out.println("Got it. I've added this BetaGo.Tasks.Todo task:\n" + temp.toString());
            System.out.println("Now you have " + this.list.size() + " tasks in the list.\n");
        }
    }

    public void addDeadline(String str) throws InvalidCommandException {
        String[] inputs = str.split(" ", 2);
        if (inputs.length != 2) {
            throw new InvalidCommandException("No description stated.");
        } else {
            String[] when = inputs[1].split(" /by ", 2);
            if (when.length != 2) {
                throw new InvalidCommandException("No deadline stated.");
            } else {
                try {
                    Deadline temp = new Deadline(when[0], when[1]);
                    this.list.add(temp);
                    System.out.println("Got it. I've added this BetaGo.Tasks.Deadline task:\n" + temp.toString());
                    System.out.println("Now you have " + this.list.size() + " tasks in the list.\n");
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void addEvent(String str) throws InvalidCommandException {
        String[] inputs = str.split(" ", 2);
        if (inputs.length != 2) {
            throw new InvalidCommandException("No description stated.");
        } else {
            String[] where = inputs[1].split(" /at ", 2);
            if (where.length != 2) {
                throw new InvalidCommandException("No location of event stated.");
            } else {
                Event temp = new Event(where[0], where[1]);
                this.list.add(temp);
                System.out.println("Got it. I've added this BetaGo.Tasks.Event task:\n" + temp.toString());
                System.out.println("Now you have " + this.list.size() + " tasks in the list.\n");
            }
        }
    }

    public void deleteItems(String str) throws InvalidCommandException {
        String[] inputs = str.split(" ", 2);
        if (inputs.length != 2) {
            throw new InvalidCommandException("No task number indicated.");
        } else {
            try{
                int marker = Integer.valueOf(inputs[1]);
                if (marker < 1 || marker > this.list.size()) {
                    System.out.println("Please indicate a valid task number!\n");
                }  else {
                    System.out.println("Noted. I have removed this task:\n" + this.list.get(marker - 1).toString());
                    this.list.remove(marker - 1);
                    System.out.println("Now you have " + this.list.size() + " tasks in the list.\n");
                }
            }
            catch (NumberFormatException ex){
                throw new InvalidCommandException("Invalid item to be marked.");
            }
        }
    }

    public void loadTodo(String str) throws InvalidDataFileException {
        String[] inputs = str.split(" , ", 3);
        if (inputs.length != 3) {
            System.out.println("Input length incorrect");
            throw new InvalidDataFileException("Invalid Input from Data File: Insufficient details");
        } else {
            Todo temp = new Todo(inputs[2]);
            if (inputs[1].equalsIgnoreCase("1")) {
                temp.markAsDone();
            } else if (inputs[1].equalsIgnoreCase("0")) {
                temp.markAsNotDone();
            } else {
                throw new InvalidDataFileException("Invalid Input from Data File: Incorrect marker");
            }
            this.list.add(temp);
        }
    }

    public void loadDeadline(String str) throws InvalidDataFileException{
        String[] inputs = str.split(" , ", 4);
        if (inputs.length != 4) {
            throw new InvalidDataFileException("Invalid Input from Data File: Insufficient details");
        } else {
            try {
                String dateTime[] = inputs[3].split(",", 2);
                try {
                    Deadline temp;
                    if (dateTime.length == 2) {
                        LocalDate d = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                        String deadlineDateTime = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        deadlineDateTime = deadlineDateTime + dateTime[1];
                        temp = new Deadline(inputs[2], deadlineDateTime);
                    } else {
                        temp = new Deadline(inputs[2], dateTime[0]);
                    }
                    if (inputs[1].equalsIgnoreCase("1")) {
                        temp.markAsDone();
                    } else if (inputs[1].equalsIgnoreCase("0")) {
                        temp.markAsNotDone();
                    } else {
                        throw new InvalidDataFileException("Invalid Input from Data File: Incorrect marker");
                    }
                    this.list.add(temp);
                } catch (DateTimeParseException e) {}
            } catch (InvalidCommandException e) {
                throw new InvalidDataFileException("Invalid Input from Data File: Invalid BetaGo.Tasks.Deadline BetaGo.Tasks.Task");
            }

        }
    }

    public void loadEvent(String str) throws InvalidDataFileException{
        String[] inputs = str.split(" , ", 4);
        if (inputs.length != 4) {
            throw new InvalidDataFileException("Invalid Input from Data File: Insufficient details");
        } else {
            Event temp = new Event(inputs[2], inputs[3]);
            if (inputs[1].equalsIgnoreCase("1")) {
                temp.markAsDone();
            } else if (inputs[1].equalsIgnoreCase("0")) {
                temp.markAsNotDone();
            } else {
                throw new InvalidDataFileException("Invalid Input from Data File: Incorrect marker");
            }
            this.list.add(temp);
        }
    }

    public void saveItems() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            for(int i = 0; i < this.list.size(); i++) {
                Task temp = this.list.get(i);
                fw.write(temp.saveTask());
            }
            fw.close();
        } catch (IOException e) {
            Ui.printSaveError();
        }
    }
}
