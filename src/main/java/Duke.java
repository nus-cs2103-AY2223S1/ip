import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;


/**
 *  Duke Class
 *  The class which stores inputs and interacts with user
 *
 * @author Kang Qiao
 */

public class Duke {
    /**
     * Stores all the tasks given by the user
     */
    private static ArrayList<Task> inputs = new ArrayList<Task>();

    /**
     *Reply with the input of the user
     *
     * @return A string of the copied reply.
     */
    private static String answer(String msg) {
        return "_______________________________________________________" +
                "\n" + msg + "\n" +
                "_______________________________________________________";
    }

    /**
     *Sends a message indicating the input task that has been added to a list.
     *
     * @return A string indicating the task has been added.
     */
    private static String addition(String msg) {
        return "_______________________________________________________" +
                "\n" + "Nice, I have added this task to your list:\n " + msg + "\n" +
                "Great, now you have " + inputs.size() + " tasks in the list.\n" +
                "_______________________________________________________";
    }

    /**
     *Sends a message indicating the input task that has been removed to a list.
     *
     * @return A string indicating that the task has been removed.
     */
    private static String remove(String msg) {
        return "_______________________________________________________" +
                "\n" + "OK, I have deleted this task from your list:\n " + msg + "\n" +
                "Great, now you have " + inputs.size() + " tasks in the list.\n" +
                "_______________________________________________________";
    }

    /**
     *Combines all the input by the user into a list of inputs
     *
     * @return A string of the list of inputs
     */
    private static String all() {
        String userInputs = "";
        for (int i = 0; i < inputs.size(); i++)
        {
            int index = i + 1;
            Task tempTask = inputs.get(i);
            userInputs += "\n" + index + "."
                    + tempTask.toString() + "\n";

        }
        return userInputs;
    }

    /**
     *Mark the specified task as done
     *
     * @return A string of the marked task
     */
    private static String markDone(String str) {
        return "_______________________________________________________" + "\n" +
                "Nice! I've marked this task as done:" +
                "\n" + str + "\n" +
                "\n" + "_______________________________________________________";
    }

    /**
     *Mark the specified task as not done
     *
     * @return A string of the unmarked task
     */
    private static String markUndone(String str) {
        return "_______________________________________________________" + "\n" +
                "Wow! I've marked this task as not done yet:" +
                "\n" + str + "\n" +
                "\n" + "_______________________________________________________";
    }



    /**
     *Save updates to the file
     *
     * @param file
     * @throws IOException
     */
    private static void saveFile(String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < inputs.size(); i++) {
            fileWriter.write(inputs.get(i).toString() + "\n");
        }
        fileWriter.close();
     }

    /**
     * Reads the file and refills inputs arraylist with the specific tasks
     * @param file
     * @throws FileNotFoundException
     */

     private static void parseFile(File file) throws FileNotFoundException {
         Scanner scanner = new Scanner(file);
         //System.out.println("parsing activated");
         //fix the whitespacing problem or it is gonna be even more painful later!


         while (scanner.hasNext()) {
             //this is for formating
             String[] task = scanner.nextLine().split("[\\[||\\]]");
             String taskContentHelper = task[4].split(" ", 2)[1];//the task and time without the brackets
             String taskContent = taskContentHelper.split("\\(")[0].trim();//just the task
             //System.out.println("task main content" + taskContentHelper);
             System.out.println("task1 " + taskContent);
             String taskMark = task[3];
             String taskType = task[1];
             Task newTask;
            // System.out.println("the mark:" + taskMark);

             if (taskType.equals("T")) {
                 //System.out.println("todo parsed");
                 newTask = new ToDo(taskContent);
                 if (taskMark.equals("X")) {
                     newTask.setDone();
                 }
                 inputs.add(newTask);
             } else if (taskType.equals("D")) {
                 //System.out.println("event parsed");
                 String[] timingContent = taskContentHelper.split("[\\(||\\)]");//this is to split up and obtain time segment
                 System.out.println("timingContent: " + timingContent[1]);
                 //small problem where the user may not specify porperly
                 String time[] = timingContent[1].split(" ");//array of the elements in the time
                 String dateHelper = "";// the specific date
                 for (int i = 1; i < time.length; i++) {
                     dateHelper += time[i] + " ";
                 }
                 String date = dateHelper.trim();
                 //System.out.println(date + "no");
                 newTask = new Deadline(taskContent, date);
                 if (taskMark.equals("X")) {
                     newTask.setDone();
                 }
                 inputs.add(newTask);
             } else {
                 String[] timingContent = taskContentHelper.split("[\\(||\\)]");//this is to split up and obtain time segment
                 //System.out.println("timingContent: " + timingContent[1]);
                 //small problem where the user may not specify porperly
                 String time[] = timingContent[1].split(" ");//array of the elements in the time
                 String dateHelper = "";// the specific date
                 for (int i = 1; i < time.length; i++) {
                     dateHelper += time[i] + " ";
                 }
                 String date = dateHelper.trim();
                 //System.out.println(date + "no");
                 newTask = new Event(taskContent, date);
                 if (taskMark.equals("X")) {
                     newTask.setDone();
                 }
                 inputs.add(newTask);
             }
          }
     }



    /**
     *The Main function for interaction between the user and DUKE
     */
    public static void main(String[] args) throws DukeException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "What can I do for you?\n");
        try{
            File dukeFile = new File("data/duke.txt");
            dukeFile.getParentFile().mkdirs();//create the directory
            if(dukeFile.createNewFile()){
                System.out.println("new file created!");
            } else {
                parseFile(dukeFile);
                System.out.println("updated file");
            }
        }catch (IOException e) {
            System.out.println("Error in creating file");
        }


        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while(!str.equals("bye"))
        {
            //for listing
            if (str.equals("list"))
            {
                System.out.println("Here are all the tasks in your list\n");
                System.out.println("_______________________________________________________" +
                        "\n" + all() + "\n" +
                        "_______________________________________________________");
            }
            else
            {
                String instruction = str.split(" ")[0];
                if (instruction.equals("mark"))
                {
                    //this is as index is greater than array index by 1
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    inputs.get(index).setDone();
                    System.out.println(markDone(inputs.get(index).toString()));
                }
                else if ((instruction.equals("unmark")))
                {
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    inputs.get(index).setUndone();
                    System.out.println(markUndone(inputs.get(index).toString()));
                }
                else if (instruction.equals("todo"))
                {
                  try {
                      str = str.split(" ", 2)[1].trim();
                      ToDo tasktoDo = new ToDo(str);
                      inputs.add(tasktoDo);
                      System.out.println(addition(tasktoDo.toString()));

                  } catch (ArrayIndexOutOfBoundsException e) {
                          throw new DukeException(("OOPS!!! The description of a ToDo cannot be empty."));
                }

                }
                else if(instruction.equals("deadline"))
                {
                  try {
                      //splits away deadline
                      str = str.split(" ", 2)[1];
                      String desc = str.split("/by")[0].trim();
                      String by = str.split("/by")[1].trim();
                      Deadline taskDeadline = new Deadline(desc, by);
                      inputs.add(taskDeadline);
                      System.out.println(addition(taskDeadline.toString()));
                  } catch (ArrayIndexOutOfBoundsException e) {
                      throw new DukeException(("OOPS!!! The description of a Deadline cannot be empty/incomplete."));
                  }

                }
                else if(instruction.equals("event"))
                {
                  try {
                      //splits away event
                      str = str.split(" ", 2)[1];
                      String desc = str.split("/at")[0].trim();
                      String at = str.split("/at")[1].trim();
                      Event taskEvent = new Event(desc, at);
                      inputs.add(taskEvent);
                      System.out.println(addition(taskEvent.toString()));
                      System.out.println("l" + at + "l");
                  } catch (ArrayIndexOutOfBoundsException e) {
                      throw new DukeException(("OOPS!!! The description of an Event cannot be empty/incomplete."));
                  }

                }
                else if(instruction.equals("delete"))
                {
                    str = str.split(" ", 2)[1].trim();
                    int index = Integer.valueOf(str) - 1;
                    Task content = inputs.get(index);
                    inputs.remove(content);
                    System.out.println(remove(content.toString()));

                }
                else
                {
                    throw new DukeException("I have no idea what you are saying, this is not a task >_<");
                }
            }


            try {
                saveFile("data/duke.txt");//updates dukeFile after each input
            } catch (IOException e) {
                System.out.println("Error in saving the file");
            }




            str = sc.nextLine();
        }
        System.out.println("_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!");
    }
}