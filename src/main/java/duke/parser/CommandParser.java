package duke.parser;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Parses user input commands and returns response.
 */
public class CommandParser {

    enum Command {
        LIST,
        DELETE,
        DONE,
        UNDONE,
        BEFORE,
        FIND,
        TODO,
        EVENT,
        DEADLINE,
        JOKE
    }

    private static final Pattern COMMAND_PATTERN =
            Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: (.*))?$");
    private final TaskList taskList;

    /**
     * Constructor specifying tasklist to parse commands into.
     *
     * @param tasks tasklist to store tasks in
     */
    public CommandParser(TaskList tasks) {
        this.taskList = tasks;
    }

    private Command generator(String action) throws DukeException {
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("You are incomprehensible. Apply yourself.");
        }
    }

    /**
     * Parses user commands.
     *
     * @param command command input by user
     */
    public String handle(String command) {
        String response;

        try {
            // Identify groups based on command pattern
            Matcher matcher = COMMAND_PATTERN.matcher(command);
            matcher.find();
            String action = matcher.group(1);
            String desc = matcher.group(2);
            String time = matcher.group(4);
            Command cmd = generator(action);

            // Call methods according to command
            switch (cmd) {
            case LIST:
                response = taskList.printList();
                break;
            case DONE:
                response = taskList.markDone(desc);
                break;
            case UNDONE:
                response = taskList.markUndone(desc);
                break;
            case BEFORE:
                response = taskList.printDeadline(time);
                break;
            case FIND:
                response = taskList.find(desc);
                break;
            case DELETE:
                response = taskList.delete(desc);
                break;
            case TODO:
                response = taskList.addToDo(desc);
                break;
            case EVENT:
                response = taskList.addEvent(desc, time);
                break;
            case DEADLINE:
                response = taskList.addDeadline(desc, time);
                break;
            case JOKE:
                response = joke();
                break;
            default:
                throw new DukeException("You are incomprehensible. Apply yourself.");
            }
        } catch (DukeException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return "Please enter the correct due date format d/mm/yyyy [HHmm]";
        }
        return response;
    }

    private String joke() {
        String result = "Just do your work...";
        try {
            Random rand = new Random();
            BufferedReader in = new BufferedReader(new FileReader("joke.txt"));
            String line = in.readLine();
            while (line != null) {
                if (rand.nextInt(10) == 0) {
                    result = line;
                }
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Joke file not found...");
            createJokeFile();
        } catch (IllegalStateException e) {
            System.out.println("Joke is invalid...");
        } catch (IOException e) {
            System.out.println("Joke cannot be read...");
        }
        return result;
    }

    private void createJokeFile() {
        try {
            FileWriter myWriter = new FileWriter("joke.txt");
            myWriter.write("They laughed at my crayon drawing. I laughed at their chalk outline.\n"
                    + "I have many jokes about unemployed people, sadly none of them work.\n"
                    + "I made a website for orphans. It does not have a home page.\n"
                    + "“I’m sorry” and “I apologize” mean the same thing. Except at a funeral.\n"
                    + "What’s the difference between a Lamborghini and a dead body? I don’t have a Lamborghini in my garage.\n"
                    + "The cemetery is so crowded. People are just dying to get in.\n"
                    + "The guy who stole my diary just died. My thoughts are with his family.\n"
                    + "Why do they actually prefer non-swimmers in the Navy? They defend their ship with a lot more enthusiasm.\n"
                    + "I know a fish that can break-dance! Only for 20 seconds though, and only once.\n"
                    + "People with Covid have no taste!\n"
                    + "My boss told me to have a good day. So I went home.\n"
                    + "If doctors were like software engineers, they would say things"
                    + "like “Have you tried killing yourself and being reborn?”\n"
                    + "You.\n"
                    + "What did the router say to the doctor? “It hurts when IP”\n"
                    + "Why do programmers always mix up Christmas and Halloween? Because Dec 25 is Oct 31.\n"
                    + "//be nice to the CPU Thread_sleep(1);\n"
                    + "The best thing about a Boolean is that even if you are wrong, you are only off by a bit.");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


