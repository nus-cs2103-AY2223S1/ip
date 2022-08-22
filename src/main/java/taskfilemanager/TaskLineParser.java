package taskfilemanager;

import exception.InvalidInputException;
import exception.InvalidTaskFileException;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;

public class TaskLineParser {
    public static Task parseLine(String line) throws InvalidInputException {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(",,,");

        String category = sc.next();
        Task parsedTask;

        if (!sc.hasNextInt()) {
            throw new InvalidTaskFileException();
        }

        boolean isDone = sc.nextInt() == 1;

        if (!sc.hasNext()) {
            throw new InvalidTaskFileException();
        }

        String description = sc.next();

        if (category.equals("D") && sc.hasNext()) {
            parsedTask = new Deadline(description, sc.next(), isDone);
        } else if (category.equals("E") && sc.hasNext()) {
            parsedTask = new Event(description, sc.next(), isDone);
        } else if (category.equals("T")) {
            parsedTask = new ToDo(description, isDone);
        } else {
            throw new InvalidTaskFileException();
        }

        if (sc.hasNext()) {
            throw new InvalidTaskFileException();
        } else {
            return parsedTask;
        }
    }
}
