import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Tasks {

    // class variables
    private ArrayList<Task> taskList;

    // constructor
    public Tasks(int n) {
        this.taskList = new ArrayList<>(n);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void markMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= this.taskList.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = this.taskList.get(index);
                    task.markAsDone();
                    DukeMessage.sendMessage(" Nice! I've marked this task as done:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void unmarkMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= this.taskList.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = this.taskList.get(index);
                    task.markAsUndone();
                    DukeMessage.sendMessage(" Nice! I've marked this task as undone:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void todoMessage(String userAction) throws DukeException {
        try {
            Task newTodo = new Todo(userAction);
            if (newTodo.description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else{
                taskList.add(newTodo);
                DukeMessage.sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                        + "\n Now you have " + this.taskList.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void eventMessage(String userAction) throws DukeException {
        try {
            String[] eventString = userAction.split("/at ");
            if (eventString[0].equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (eventString.length == 1) {
                throw new DukeException("The description or date/time of an event cannot be empty.");
            } else {
                Task newEvent = new Event(eventString[0], eventString[1]);
                taskList.add(newEvent);
                DukeMessage.sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                        + "\n Now you have " + this.taskList.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void deadlineMessage(String userAction) throws DukeException {
        try {
            String[] deadlineString = userAction.split("/by ");
            if (deadlineString[0].equals("")) {
                throw new DukeException("The description and date/time of a deadline cannot be empty.");
            } else if (deadlineString.length == 1) {
                throw new DukeException("The description or date/time of a deadline cannot be empty.");
            } else {
                System.out.println(deadlineString[1]);
                Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                taskList.add(newDeadline);
                DukeMessage.sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                            + "\n Now you have " + this.taskList.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void deleteMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= this.taskList.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = this.taskList.get(index);
                    this.taskList.remove(index);
                    DukeMessage.sendMessage(" Noted. I've removed this task:\n" + "   " + task.toString()
                            + "\n Now you have " + this.taskList.size() + " tasks in the list.");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadTasks() {
        try {
            FileReader file = new FileReader("tasks.txt");
            BufferedReader br = new BufferedReader(file);
            String nextLine;
            // break while loop is no more lines to read
            while ((nextLine = br.readLine()) != null) {
                // split string
                String[] nextTask = nextLine.split(",", 5);
                // case 1 : To-Do Task
                switch (nextTask[0]) {
                    case "T":
                        Task newTodo = new Todo(nextTask[2]);
                        if (nextTask[1].equals("1")) {
                            newTodo.markAsDone();
                        }
                        this.taskList.add(newTodo);
                        // case 2 : Event Task
                        break;
                    case "E":
                        Task newEvent = new Event(nextTask[2], nextTask[3]);
                        if (nextTask[1].equals("1")) {
                            newEvent.markAsDone();
                        }
                        this.taskList.add(newEvent);
                        // case 3 : Deadline Task
                        break;
                    case "D":
                        Task newDeadline = new Deadline(nextTask[2], nextTask[3]);
                        if (nextTask[1].equals("1")) {
                            newDeadline.markAsDone();
                        }
                        this.taskList.add(newDeadline);
                        break;
                }
            }
            br.close();
        // cannot open file, make a new file in project root directory
        } catch (FileNotFoundException e) {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
        try {
            System.out.println("creating file");
            File file = new File("tasks.txt");
            if (file.createNewFile()) {
                System.out.println("File successfully created !");
            } else {
                System.out.println("File is already present!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveFile() {
        try {
            FileWriter fw = new FileWriter("tasks.txt");
            // loop through ArrayList<Task>
            for (Task task : this.taskList) {
                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    if (todo.isDone) {
                        fw.write("T,1," + todo.description + "\n");
                    } else {
                        fw.write("T,0," + todo.description + "\n");
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.isDone) {
                        fw.write("E,1," + event.description + "," + event.event + "\n");
                    } else {
                        fw.write("E,0," + event.description + "," + event.event + "\n");
                    }
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.isDone) {
                        fw.write("D,1," + deadline.description + "," + deadline.deadline + "\n");
                    } else {
                        fw.write("D,0," + deadline.description + "," + deadline.deadline + "\n");
                    }
                }
            }
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getList() {
        String list = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                list = list + " " + (i + 1) + ": " + taskList.get(i).toString();
                break;
            }
            list = list + " " + (i + 1) + ": " + taskList.get(i).toString() + "\n";
        }
        String listMessage = " Here are the tasks in your list:\n" + list;
        DukeMessage.sendMessage(listMessage);
    }
}