package duke;
import duke.events.Deadline;
import duke.events.Event;
import duke.events.Task;
import duke.events.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    //Initialising task array for list cmd
    private ArrayList<Task> taskList;
    private Storage storage;
    private static int numOfInputs = 0;


    TaskList(ArrayList<Task> loadSave, Storage storage) {
        this.taskList = loadSave;
        this.storage = storage;
        numOfInputs = loadSave.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printTaskList() {
        System.out.println("Here are your tasks =0w0=");
        Task task;

        for (int i = 0; i < numOfInputs; i++) {
            task = taskList.get(i);

            if (task.isDone()) { //task is done
                System.out.println((i + 1) + ". " + task);

            } else { //task is not done
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public void deleteTask(int taskNum) {
        try {
            if (taskNum >= numOfInputs) {
                throw new DukeException("Meowmeow can't throw away a task that doesn't exist =owo=");
            } else {
                System.out.println("Meowmeow has thrown this task into the void!! (=^>w<^=) \n" + taskList.get(taskNum));
                taskList.remove(taskNum);
                numOfInputs -= 1;
                System.out.println("You have " + numOfInputs + " tasks left now Owo");
            }
        } catch (DukeException e) {
            System.out.println(e.message);
        }
    }

    public void addTodo(String taskName) {
        try {
            if (taskName == null || taskName.equals("")) {
                throw new DukeException("Meowmeow needs a name for the task you want to add (=^0w0^=)");
            } else {
                Task t = new ToDo(taskName);
                taskList.add(t);
                numOfInputs += 1;

                System.out.println("(=^-w-^=) " + t + " has been added to your task list!\n");
                System.out.println("You now have " + numOfInputs + " tasks >w<");
            }
        } catch (DukeException e) {
            System.out.println(e.message);
        }
    }

    public void addDeadline(String userInput) {
        try {
            String[] splitB = userInput.split("/by ");

            if (splitB.length <= 1) {
                throw new DukeException("=0w0= To add a deadline type it in in this format: deadline taskName /by YYYY-DD-MMTHH:MM:SS");
            } else {
                System.out.println(splitB[1]);
                LocalDateTime deadline = LocalDateTime.parse(splitB[1]);
                Task d = new Deadline(splitB[0], deadline);
                taskList.add(d);
                numOfInputs += 1;
                System.out.println("(=^-w-^=) " + d + " has been added to your task list!\n");
                System.out.println("You now have " + numOfInputs + " tasks >w<");
            }
        } catch (DukeException e) {
            System.out.println(e.message);
        }
    }

    public void addEvent(String userInput) {
        try {
            String[] splitA = userInput.split("/at");

            if (splitA.length <= 1) {
                throw new DukeException("=0w0= To add an event type it in in this format: event taskName /at time");
            } else {
                String time = splitA[1];
                Task e = new Event(splitA[0], time);
                taskList.add(e);
                numOfInputs += 1;

                System.out.println("(=^-w-^=) " + e + " has been added to your task list!\n");
                System.out.println("You now have " + numOfInputs + " tasks >w<");
            }
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    public void finishedTask(int taskNum) {
        Task task;
        try {
            if (taskNum <= numOfInputs) {
                task = taskList.get(taskNum - 1);
                task.markAsDone();

                System.out.println("Good job (=OwO=) You finished this task! \n" + task);
            } else {
                throw new DukeException("Meowmeow there isn't a task with that number uwu");
            }
        } catch (DukeException e) {
            System.out.println(e.message);
        }
    }

    public void unfinishedTask(int taskNum) {
        Task task;
        try {
            if (taskNum <= numOfInputs) {
                task = taskList.get(taskNum - 1);
                task.markAsNotDone();

                System.out.println("uwu this task has been marked as not done... \n" + task);
            } else {
                throw new DukeException("Meowmeow there isn't a task with that number uwu");
            }

        } catch (DukeException e) {
            System.out.println(e.message);
        }
    }
}
