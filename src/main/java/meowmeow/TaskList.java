package meowmeow;

import java.time.LocalDateTime;
import java.util.ArrayList;

import meowmeow.events.Deadline;
import meowmeow.events.Event;
import meowmeow.events.Task;
import meowmeow.events.ToDo;

public class TaskList {
    //Initialising task array for list cmd
    private static int numOfInputs = 0;
    private final ArrayList<Task> taskList;
    private final Storage storage;

    TaskList(ArrayList<Task> loadSave, Storage storage) {
        this.taskList = loadSave;
        this.storage = storage;
        numOfInputs = loadSave.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String printTaskList() {
        String output = "Here are your tasks =0w0= \n";
        Task task;

        for (int i = 0; i < numOfInputs; i++) {
            task = taskList.get(i);

            output += (i + 1) + ". " + task + "\n";
        }
        return output;
    }

    public String deleteTask(int taskNum) {
        try {
            if (taskNum >= numOfInputs) {
                throw new MeowmeowException("Meowmeow can't throw away a task that doesn't exist =owo=");
            } else {
                String task = taskList.get(taskNum).toString();
                taskList.remove(taskNum);
                numOfInputs -= 1;
                return "Meowmeow has thrown this task into the void!! (=^>w<^=) \n"
                        + task + "\n"
                        + "You have " + numOfInputs + " tasks left now Owo";
            }
        } catch (MeowmeowException e) {
            return e.getMessage();
        }
    }

    public String addTodo(String taskName) {
        try {
            if (taskName == null || taskName.equals("")) {
                throw new MeowmeowException("Meowmeow needs a name for the task you want to add (=^0w0^=)");
            } else {
                Task t = new ToDo(taskName);
                taskList.add(t);
                numOfInputs += 1;

                return "(=^-w-^=) " + t + " has been added to your task list!\n"
                    + "You now have " + numOfInputs + " tasks >w<";
            }
        } catch (MeowmeowException e) {
            return e.toString();
        }
    }

    public String addDeadline(String userInput) {
        try {
            String[] splitB = userInput.split("/by ");

            if (splitB.length <= 1) {
                throw new MeowmeowException("=0w0= To add a deadline type it in in this format: "
                        + "deadline taskName /by YYYY-DD-MMTHH:MM:SS");
            } else {
                System.out.println(splitB[1]);
                LocalDateTime deadline = LocalDateTime.parse(splitB[1]);
                Task d = new Deadline(splitB[0], deadline);
                taskList.add(d);
                numOfInputs += 1;
                return "(=^-w-^=) " + d + " has been added to your task list!\n"
                        + "You now have " + numOfInputs + " tasks >w<";
            }
        } catch (MeowmeowException e) {
            return e.toString();
        }
    }

    public String addEvent(String userInput) {
        try {
            String[] splitA = userInput.split("/at");

            if (splitA.length <= 1) {
                throw new MeowmeowException("=0w0= To add an event type it in in this format: event taskName /at time");
            } else {
                String time = splitA[1];
                Task e = new Event(splitA[0], time);
                taskList.add(e);
                numOfInputs += 1;

                return "(=^-w-^=) " + e + " has been added to your task list!\n"
                        + "You now have " + numOfInputs + " tasks >w<";
            }
        } catch (MeowmeowException e) {
            return e.toString();
        }
    }

    public String finishTask(int taskNum) {
        Task task;
        try {
            if (taskNum <= numOfInputs) {
                task = taskList.get(taskNum - 1);
                task.markAsDone();

                return "Good job (=OwO=) You finished this task! \n" + task;
            } else {
                throw new MeowmeowException("Meowmeow there isn't a task with that number uwu");
            }
        } catch (MeowmeowException e) {
            return e.toString();
        }
    }

    public String unfinishTask(int taskNum) {
        Task task;
        try {
            if (taskNum <= numOfInputs) {
                task = taskList.get(taskNum - 1);
                task.markAsNotDone();

                return "uwu this task has been marked as not done... \n" + task;
            } else {
                throw new MeowmeowException("Meowmeow there isn't a task with that number uwu");
            }

        } catch (MeowmeowException e) {
            return e.toString();
        }
    }

    public String findTask(String userInput) {
        try {
            int numMatchingTasks = 0;
            String similarTasks = "";
            for (int i = 0; i < numOfInputs; i++) {
                Task task = taskList.get(i);
                String taskName = task.getName();
                if (taskName.contains(userInput)) {
                    similarTasks += task + "\n";
                    numMatchingTasks += 1;
                }
            }
            if (numMatchingTasks == 0) {
                throw new MeowmeowException("Meowmeow can't find any tasks that contain these words (=^0w0^=)");
            }
            return similarTasks;
        } catch (MeowmeowException e) {
            return e.toString();
        }
    }
}
