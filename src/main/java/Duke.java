<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
=======
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
>>>>>>> Level-7
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        int currentAction = 0;
        int end = 0;

        //Create new file
        File file = Path.of("data/duke.txt").toFile();
        ArrayList<Task> listOfActions = new ArrayList<Task>(100);
        if (file.length() == 0 || !file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Hi new user, Lets get productive");
            } catch (IOException e) {
                System.out.println("Hmmm There seems to be a problem");
            }
        } else {
            try {
                currentAction = addBack(listOfActions, file, currentAction);
                System.out.println("Ooo looks like you already have a todo list");
            } catch (FileNotFoundException e) {
                System.out.println("Hmmm There seems to be a problem ...");
            }
        }

        Scanner sc= new Scanner(System.in);

        System.out.println("----------------------\nHello! I'm HelperBot\nWhat can I do for you?\n----------------------\n");
        String userInput = sc.nextLine();

        while (end != 1) {
            //System.out.println();
            //If user wants to check the list
            String output = list(listOfActions, currentAction);

            if (userInput.equals("bye")) {
                break;
            }

            else if (userInput.equals("list")) {
                System.out.println("----------------------\n" + output + "----------------------\n");
            }

            else if (userInput.length() >= 4) {

                //Td
                if (userInput.substring(0,4).equals("todo")) {
                    try {
                        toDo(userInput, listOfActions, currentAction);
                        currentAction++;
                    } catch (DukeException e) {
                        System.out.println("----------------------\nSorry you can't do nothing :(\n" +
                                "----------------------\n");
                    }
                }

                else if (userInput.length() == 4 && !userInput.equals("todo")) {
                    System.out.println("----------------------\nI am sorry pls input again\n" +
                            "----------------------\n");
                }

                else if (userInput.length() > 4) {

                    //event
                    if (userInput.substring(0, 5).equals("event")) {
                        try {
                            event(userInput, listOfActions, currentAction);
                            currentAction++;
                        } catch (DukeException e) {
                            System.out.println("----------------------\nNo event no good :(\n" +
                                    "----------------------\n");
                        }
                    }

                    else if (userInput.length() > 5) {

                        //Marking done
                        if (userInput.length() == 6) {
                            if (userInput.substring(0, 4).equals("mark")) {
                                try {
                                    mark(userInput, listOfActions);
                                } catch (DukeException e) {
                                    System.out.println("----------------------\nI am sorry pls input again\n" +
                                            "----------------------\n");
                                }
                            }

                            //Delete

                            else if (userInput.substring(0, 6).equals("delete")) {
                                    System.out.println("----------------------\nI am sorry pls input again\n" +
                                            "----------------------\n");
                            }


                            else {
                                System.out.println("----------------------\nI am sorry pls input again\n" +
                                        "----------------------\n");
                            }
                        }

                        else if (userInput.length() > 7) {


                            if (userInput.substring(0, 6).equals("delete")) {
                                try {
                                    removeTask(userInput, listOfActions, currentAction);
                                    currentAction--;
                                } catch (DukeException e) {
                                    System.out.println("----------------------\nI am sorry pls input again\n" +
                                            "----------------------\n");
                                }
                            }

                            //deadline
                            else if (userInput.substring(0, 8).equals("deadline")) {
                                try {
                                    deadLine(userInput, listOfActions, currentAction);
                                    currentAction++;
                                } catch (DukeException e) {
                                    System.out.println("----------------------\nNo deadline? Impossible !!!\n" +
                                            "----------------------\n");
                                }

                            }

                            //Unmarking
                            else if (userInput.length() == 8) {
                                if (userInput.substring(0, 6).equals("unmark")) {
                                    try {
                                        unMark(userInput, listOfActions);
                                    } catch (DukeException e) {
                                        System.out.println("----------------------\nI am sorry pls input again\n" +
                                                "----------------------\n");
                                    }
                                } else {
                                    System.out.println("----------------------\nI am sorry pls input again\n" +
                                            "----------------------\n");
                                }
                            }

                            else {
                                System.out.println("----------------------\nI am sorry pls input again\n" +
                                        "----------------------\n");
                            }
                        }
                        else {
                            System.out.println("----------------------\nI am sorry pls input again\n" +
                                    "----------------------\n");
                        }
                    }

                    else {
                        System.out.println("----------------------\nI am sorry pls input again\n" +
                                "----------------------\n");
                    }

                }



                //If random words longer than 4
                else {
                    System.out.println("----------------------\nI am sorry pls input again\n" +
                            "----------------------\n");
                }
            }

            //If random words <4
            else {
                System.out.println("----------------------\nI am sorry pls input again\n" +
                        "----------------------\n");
            }
            userInput = sc.nextLine();
        }
        bye(listOfActions, file);
        end = 1;
    }

    public static void bye(ArrayList<Task> listOfActions, File file) {
        System.out.println("Thank you and ATB :)");
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }

    public static void mark(String userInput, ArrayList<Task> listOfActions) throws DukeException{
        try {
            int position = Character.getNumericValue(userInput.charAt(5)) - 1;
            listOfActions.get(position).mark();
            System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                    listOfActions.get(position) + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //Unmark method
    public static void unMark(String userInput, ArrayList<Task> listOfActions) throws DukeException{
        try {
            int position = Character.getNumericValue(userInput.charAt(7)) - 1;
            listOfActions.get(position).unMark();
            System.out.println("----------------------\n" + "One more mission ;)\n" +
                    listOfActions.get(position) + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //td method
    public static void toDo(String userInput, ArrayList<Task> listOfActions, int currentAction) throws DukeException{
        try {
            //action
            if (userInput.length() < 5) {
                throw new StringIndexOutOfBoundsException("");
            }
            String action = userInput.substring(4);
            Todo td = new Todo(action.strip());
            listOfActions.add(td);
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions.get(currentAction) + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //dedline method
    public static void deadLine(String userInput, ArrayList<Task> listOfActions, int currentAction) throws DukeException{
        try {
            //get action
            String[] split_Description = userInput.split(" ");
            String string_Date = split_Description[4];
            String string_Time = split_Description[5];
            String action = split_Description[1];
            //get deadline
            String dedline = string_Date + " " + string_Time;
            Deadline ded = new Deadline(action, dedline);
            listOfActions.add(ded);
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions.get(currentAction) + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }


    //event method
    public static void event(String userInput, ArrayList<Task> listOfActions, int currentAction) throws DukeException {
        try {
            //get action
            int index = userInput.indexOf("/");
            String action = userInput.substring(6, index);
            //get date
            String dedline = userInput.substring(index + 3);
            Event e = new Event(action.strip(), dedline.strip());
            listOfActions.add(e);
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions.get(currentAction) + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch(StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //delete method
    public static void removeTask(String userInput, ArrayList<Task> listOfActions, int currentAction)
            throws DukeException {
        try {
            int index = Character.getNumericValue(userInput.charAt(7)) - 1;
            String removed = listOfActions.get(index).toString();
            listOfActions.remove(index);
            System.out.println("----------------------\n" + "Noted, The following task has been removed\n" +
                    removed + String.format("\nYou have a total of %d work to do still", currentAction - 1) +
                    "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }


    public static int addBack(ArrayList<Task> arr, File f, int currentAction) throws FileNotFoundException {
        Scanner myReader = new Scanner(f);
        while (myReader.hasNextLine()) {
            currentAction = currentAction + 1;
            String data = myReader.nextLine();
            checkTask(data, arr);
        }
        return currentAction;
    }

    public static void checkTask(String str, ArrayList<Task> arr) {
        //check what task
        String t = Character.toString(str.charAt(1));
        String done = Character.toString(str.charAt(4));
        Boolean d = !done.equals(" ");
        //If td

        if (t.equals("T")) {
            //check done
            if (d) {
                Todo add = new Todo(str.substring(7));
                add.mark();
                arr.add(add);
            } else {
                Todo add = new Todo(str.substring(7));
                arr.add(add);
            }
        }
        //If event
        else if (t.equals("E")) {
            int pos = str.indexOf("(") - 1;
            if (d) {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                add.mark();
                arr.add(add);
            } else {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                arr.add(add);
            }
        }
        //if Deadline
        else if (t.equals("D")) {
            int pos = str.indexOf("(") - 1;
            if (d) {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                add.mark();
                arr.add(add);
            } else {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                arr.add(add);
            }
        }
    }


    public static String list(ArrayList<Task> listOfActions, int currentAction) {
        String output = "";
        for (int i = 0; i < currentAction; i++) {
            output = output + String.format("%d", i + 1) + "." + listOfActions.get(i) + "\n";
        }
        return output;
    }



}


