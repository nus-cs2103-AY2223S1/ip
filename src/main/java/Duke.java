package main.java;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


import static java.lang.Integer.parseInt;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
    }

   // private static ArrayList<Task> tasks = new ArrayList<>();

    public void run() throws DukeException, IOException {
        ui.showGreetings();
        tasks = new TaskList(storage.loadFile());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws DukeException, IOException, FileNotFoundException {
        new Duke("data/duke.txt").run();
/*
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while (!Objects.equals(echo, "bye")) {
            if (Objects.equals(echo, "list")) {
                if (tasks.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! No tasks available!");
                } else {
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                }
                // checks if it starts with mark, has a space after mark, and are all numbers after the space
            } else if (echo.length() >= 5 && (echo.startsWith("mark") &&
                    (Character.isWhitespace(echo.charAt(4))) &&
                    echo.substring(5).chars().allMatch(Character::isDigit))) {
                int number = parseInt(echo.substring(5));
                if (number > tasks.size() || number <= 0) {
                    throw new DukeException("☹ OOPS!!! Task number does not exist.");
                } else {
                    tasks.get(number - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + tasks.get(number - 1).toString());
                }
                //int number = Character.getNumericValue(echo.charAt(5));
                //similar logic as above
            } else if (echo.length() >= 7 && (echo.startsWith("unmark") &&
                    (Character.isWhitespace(echo.charAt(6))) &&
                    echo.substring(7).chars().allMatch(Character::isDigit))) {
                int number = parseInt(echo.substring(7));
                if (number > tasks.size() || number <= 0) {
                    throw new DukeException("☹ OOPS!!! Task number does not exist.");
                } else {
                    tasks.get(number - 1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n"
                            + tasks.get(number - 1).toString());
                }
                //int number = Character.getNumericValue(echo.charAt(5));
            } else if (echo.length() >= 7 && (echo.startsWith("delete") &&
                    (Character.isWhitespace(echo.charAt(6))) &&
                    echo.substring(7).chars().allMatch(Character::isDigit))) {
                int number = parseInt(echo.substring(7));
                if (number > tasks.size() || number <= 0) {
                    throw new DukeException("☹ OOPS!!! Task number does not exist.");
                } else {
                    System.out.println("Noted. I've removed this task:\n"
                            + tasks.get(number - 1).toString());
                    tasks.remove(number - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (echo.equals("todo") || (echo.startsWith("todo") && echo.substring(5).isBlank())) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else if (echo.startsWith("todo") && Character.isWhitespace(echo.charAt(4))) {
                    tasks.add(new ToDo(echo));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() +
                            " tasks in the list.");
            } else if (echo.equals("deadline") || (echo.startsWith("deadline")
                    && echo.substring(9).isBlank())) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (echo.startsWith("deadline") && Character.isWhitespace(echo.charAt(8))) {
                if (!echo.contains("/by")) {
                    throw new DukeException("☹ OOPS!!! Please use the correct format!");
                } else {
                    try {
                        tasks.add(new Deadline(echo));
                    } catch (DateTimeParseException e) {
                        throw new DukeException("☹ OOPS!!! Please use the correct date format!");
                    }
                    System.out.println("Got it. I've added this task:");

                    System.out.println(tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() +
                            " tasks in the list.");
                }
            } else if (echo.equals("event") || (echo.startsWith("event") && echo.substring(6).isBlank())) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (echo.startsWith("event") && Character.isWhitespace(echo.charAt(5))) {
                if (!echo.contains("/at")) {
                    throw new DukeException("☹ OOPS!!! Please use the correct format!");
                } else {
                    try {
                        tasks.add(new Event(echo));
                    } catch (DateTimeParseException e) {
                        throw new DukeException("☹ OOPS!!! Please use the correct date format!");
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1) + "\nNow you have "
                            + tasks.size() +
                            " tasks in the list.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            echo = sc.nextLine();
        }
        storage.saveFile(tasks);

 */
    }
}
