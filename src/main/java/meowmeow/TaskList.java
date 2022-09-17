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
    private String lastCmdType = "undo";
    private int lastCmdIndex;
    private String deletedTask;

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
            if (taskNum >= numOfInputs || taskNum < 0) {
                throw new MeowmeowException("Meowmeow can't throw away a task that doesn't exist =owo=");
            } else {
                String task = taskList.get(taskNum).toString();
                deletedTask = taskList.get(taskNum).getSaveData();
                lastCmdType = "delete";
                taskList.remove(taskNum);
                numOfInputs -= 1;

                return "Meowmeow has thrown this task into the void!! (=^>w<^=) \n"
                        + task + "\n"
                        + "You have " + numOfInputs + " tasks left now Owo";
            }
        } catch (MeowmeowException e) {
            return e.toString();
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
                lastCmdType = "add";

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
                        + "deadline taskName /by YYYY-MM-DDTHH:MM:SS");
            } else {
                LocalDateTime deadline = LocalDateTime.parse(splitB[1]);
                Task d = new Deadline(splitB[0], deadline);
                taskList.add(d);
                numOfInputs += 1;
                lastCmdType = "add";

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
                lastCmdType = "add";

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
            if (taskNum <= numOfInputs && taskNum > 0) {
                task = taskList.get(taskNum - 1);
                task.markAsDone();
                lastCmdType = "mark";
                lastCmdIndex = taskNum - 1;

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
            if (taskNum <= numOfInputs && taskNum > 0) {
                task = taskList.get(taskNum - 1);
                task.markAsNotDone();
                lastCmdType = "unmark";
                lastCmdIndex = taskNum - 1;

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

    public String undo() {
        switch(lastCmdType) {
        case "delete":
            restoreDeletedTask(deletedTask);
            lastCmdType = "undo";
            return "Meowmeow has restored the task you deleted! (=^>w<^=)";

        case "add":
            deleteTask(numOfInputs - 1);
            lastCmdType = "undo";
            return "Meowmeow has removed the task you added! (=^Ow<^=)";

        case "mark":
            taskList.get(lastCmdIndex).markAsNotDone();
            lastCmdType = "undo";
            return "Meowmeow has marked the task you finished as not done! uwu";

        case "unmark":
            taskList.get(lastCmdIndex).markAsDone();
            lastCmdType = "undo";
            return "Meowmeow has marked the task you unfinished as done! (=^OwO^=)";

        default:
            return "There isn't anything for Meowmeow to undo! (=^0w0^=)";
        }
    }

    public void restoreDeletedTask(String deletedSaveData) {
        String[] split = deletedSaveData.split(" \\| ");
        String firstChar = split[0];

        switch (firstChar) {
        case "T":
            String taskName = split[2];
            addTodo(taskName);

            boolean isDone = Boolean.parseBoolean(split[1]);
            if (isDone) {
                Task task = taskList.get(numOfInputs - 1);
                task.markAsDone();
            }
            break;

        case "D":
            taskName = split[2];

            LocalDateTime date = LocalDateTime.parse(split[3]);

            addDeadline(taskName + " /by " + date);

            isDone = Boolean.parseBoolean(split[1]);
            if (isDone) {
                Task task = taskList.get(numOfInputs - 1);
                task.markAsDone();
            }
            break;

        case "E":
            taskName = split[2];

            String time = split[3];

            addEvent(taskName + " /at " + time);

            isDone = Boolean.parseBoolean(split[1]);
            if (isDone) {
                Task task = taskList.get(numOfInputs - 1);
                task.markAsDone();
            }
            break;

        default:
            System.out.println("Something went wrong restoring the deleted task");
            break;
        }
    }
}
