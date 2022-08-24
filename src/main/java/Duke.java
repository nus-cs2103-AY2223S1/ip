package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;


/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Totsuka Tomofumi
 */
public class Duke {
    /**
     * Duke initialises with his personal logo and a welcome message before prompting for standard input
     * from the command line. 3 types of tasks can be remembered or deleted by Duke and a list can be retrieved
     * if the user types "list". Tasks can be marked or unmarked done. If the user types "bye",
     * Duke terminates with a goodbye message.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //create needed files and directories
        //will not create if already exist
        //depends where code is run, so for now just dump in project working directory
        //code built and run from intellij will dump the file in project root anyways
        File dataDir = new File("./data");
        dataDir.mkdir();
        File history = new File(dataDir, "history.txt");
        try {
            history.createNewFile();
        } catch (IOException e) {
            //made directory and/or made file could be deleted by user during runtime of this program
            throw new RuntimeException(e);
        }

        ArrayList<Task> tasks = new ArrayList<>();

        //save state retrieval
        Scanner retriever;
        try {
            retriever = new Scanner(history);
        } catch (FileNotFoundException e) {
            //made directory and/or made file could be deleted by user during runtime of this program
            throw new RuntimeException(e);
        }
        while (retriever.hasNextLine()) {
            String line = retriever.nextLine();
            String strLength = "";
            int index = 2;
            for (; line.charAt(index) != '_'; ++index) {
                strLength += line.charAt(index);
            }
            int length = Integer.parseInt(strLength);
            index++;    //now first index of desc
            Task toAdd = null;
            if (line.charAt(0) == 'T') {
                toAdd = new ToDo(line.substring(index));
            }
            if (line.charAt(0) == 'D') {
                toAdd = new Deadline(line.substring(index, index + length), line.substring(index + length));
            }
            if (line.charAt(0) == 'E') {
                toAdd = new Event(line.substring(index, index + length), line.substring(index + length));
            }
            if (toAdd != null) {
                if (line.charAt(1) == '1') {
                    toAdd.mark();
                }
                tasks.add(toAdd);
            }
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            //strip() to allow for any (unintentional) whitespaces before or after
            //also, whitespaces after the last valid char in a description/time input will be truncated.
            String response = scanner.nextLine().strip();
            //bye command
            if (response.equals("bye")) {
                break;
            //list command
            } else if (response.equals("list")) {
                int order = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.println(order++ + "." + task.toString());
                }
                continue;   //no change
            //mark command
            } else if (response.equals("mark") || response.startsWith("mark ")) {
                try {
                    if (response.length() < 6) {
                        throw new MissingTaskNumberException();
                    }
                    //will not allow if format "mark<space>int" is not followed
                    int query = Integer.parseInt(response.substring(5)) - 1;
                    if (query > tasks.size() - 1 || query < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    tasks.get(query).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(query).toString());
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                    continue;
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                    continue;
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist" +
                            " or is invalid.");
                    continue;
                }
            //unmark command
            } else if (response.equals("unmark") || response.startsWith("unmark ")) {
                try {
                    if (response.length() < 8) {
                        throw new MissingTaskNumberException();
                    }
                    //will not allow if format "mark<space>int" is not followed
                    int query = Integer.parseInt(response.substring(7)) - 1;
                    if (query > tasks.size() - 1 || query < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    tasks.get(query).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(query).toString());
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                    continue;
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                    continue;
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist" +
                            " or is invalid.");
                    continue;
                }
            //delete command
            } else if (response.equals("delete") || response.startsWith("delete ")) {
                try {
                    if (response.length() < 8) {
                        throw new MissingTaskNumberException();
                    }
                    //will not allow if format "mark<space>int" is not followed
                    int query = Integer.parseInt(response.substring(7)) - 1;
                    if (query > tasks.size() - 1 || query < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(query).toString());
                    tasks.remove(query);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                    continue;
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                    continue;
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist" +
                            " or is invalid.");
                    continue;
                }
            //add task commands
            } else {
                //successfully added a task?
                boolean success = false;
                //todo command
                if (response.equals("todo") || response.startsWith("todo ")) {
                    try {
                        //because of strip() called on response earlier,
                        //todo command with whitespaces followed are not valid descriptions.
                        if (response.length() < 6) {
                            throw new MissingDescriptionException();
                        }
                        String description = response.substring(5);
                        tasks.add(new ToDo(description));
                        success = true;
                    } catch (MissingDescriptionException e) {
                        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                //deadline command
                } else if (response.equals("deadline") || response.startsWith("deadline ")) {
                    try {
                        if (response.length() < 10) {
                            throw new MissingTokenException();
                        }
                        String subresponse = response.substring(9);
                        if (subresponse.equals("/by") || subresponse.startsWith("/by ")) {
                            throw new MissingDescriptionException();
                        }
                        if (subresponse.endsWith(" /by")) {
                            throw new MissingTimeException();
                        }
                        int tokenpos = subresponse.indexOf(" /by ");
                        if (tokenpos < 0) {
                            throw new MissingTokenException();
                        }
                        String description = subresponse.substring(0, tokenpos);
                        String time = subresponse.substring(tokenpos + 5);
                        //to ensure whitespaces alone as a description or time is not allowed
                        if (description.strip().length() == 0) {
                            throw new MissingDescriptionException();
                        }
                        if (time.length() == 0) {
                            throw new MissingTimeException();
                        }
                        tasks.add(new Deadline(description, time));
                        success = true;
                    } catch (MissingTokenException e) {
                        System.out.println(" ☹ OOPS!!! You are mising the \"/by\" token.");
                        continue;
                    } catch (MissingDescriptionException e) {
                        System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                        continue;
                    } catch (MissingTimeException e) {
                        System.out.println(" ☹ OOPS!!! You have not indicated the time for your deadline.");
                        continue;
                    }
                //event command
                } else if (response.equals("event") || response.startsWith("event ")) {
                    try {
                        if (response.length() < 7) {
                            throw new MissingTokenException();
                        }
                        String subresponse = response.substring(6);
                        if (subresponse.equals("/at") || subresponse.startsWith("/at ")) {
                            throw new MissingDescriptionException();
                        }
                        if (subresponse.endsWith(" /at")) {
                            throw new MissingTimeException();
                        }
                        int tokenpos = subresponse.indexOf(" /at ");
                        if (tokenpos < 0) {
                            throw new MissingTokenException();
                        }
                        String description = subresponse.substring(0, tokenpos);
                        String time = subresponse.substring(tokenpos + 5);
                        //to ensure whitespaces alone as a description or time is not allowed
                        if (description.strip().length() == 0) {
                            throw new MissingDescriptionException();
                        }
                        if (time.length() == 0) {
                            throw new MissingTimeException();
                        }
                        tasks.add(new Event(description, time));
                        success = true;
                    } catch (MissingTokenException e) {
                        System.out.println(" ☹ OOPS!!! You are mising the \"/at\" token.");
                        continue;
                    } catch (MissingDescriptionException e) {
                        System.out.println(" ☹ OOPS!!! The description of a event cannot be empty.");
                        continue;
                    } catch (MissingTimeException e) {
                        System.out.println(" ☹ OOPS!!! You have not indicated the time for your event.");
                        continue;
                    }
                }
                if (success) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } else {
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means. :-(");
                    continue;
                }
            }
            //only initialize fileWriter if changes are made
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(history);
            } catch (IOException e) {
                //made directory and/or made file could be deleted by user during runtime of this program
                throw new RuntimeException(e);
            }
            //prepare what to overwrite
            //TODO StringBuilder
            String overwrite = "";
            for (Task task : tasks) {
                overwrite += task.toData() + "\n";
            }
            try {
                fileWriter.write(overwrite);
                fileWriter.close();
            } catch (IOException e) {
                //made directory and/or made file could be deleted by user during runtime of this program
                throw new RuntimeException(e);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
