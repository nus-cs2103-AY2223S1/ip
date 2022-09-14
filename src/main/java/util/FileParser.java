package util;

import monkeExceptions.MonkeException;
import monkeExceptions.FileCorruptException;
import keyword.KeywordPair;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse the save file.
 */
public class FileParser {
    /**
     * Parses the save file into a list of tasks
     *
     * @param data The data from the save file.
     * @return The list of tasks.
     * @throws MonkeException The exception in case of failure.
     */
    public List<Task> parseFile(String data) throws MonkeException {
        List<Task> result = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] d = line.split("\\|", 6);
            if (line.length() > 0) {
                switch (d[0]) {
                    case "D":
                        result.add(new Deadline(
                                new ParsedData(d[1], d[2], d[3], d[4])));
                        break;
                    case "E":
                        result.add(new Event(
                                new ParsedData(d[1], d[2], d[3], d[4])));
                        break;
                    case "T":
                        result.add(new Todo(
                                new ParsedData(d[1], d[2], d[3], d[4])));
                        break;
                    default:
                        throw new FileCorruptException();
                }
            }
        }
        return result;
    }

    public List<KeywordPair> parseKeywords(String data) throws MonkeException {
        List<KeywordPair> keywordPairs = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] d = line.split("\\|", 3);
            if (line.length() > 0) {
                System.out.println(d[0]);
                System.out.println(d[1]);
                keywordPairs.add(new KeywordPair(d[0], d[1]));
            }
        }
        return keywordPairs;
    }

}
