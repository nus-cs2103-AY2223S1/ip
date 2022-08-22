package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

	private final String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public void load() throws IOException {
		String dir = this.filePath.substring(0, this.filePath.lastIndexOf("/"));
		File dataDir = new File(dir);
		dataDir.mkdir();
		File newDuke = new File(filePath);
		newDuke.createNewFile();
	}

	public void writeToFile(String textToAdd) throws IOException {
		FileWriter fw = new FileWriter(this.filePath);
		fw.write(textToAdd);
		fw.close();
	}

	public String fileFormatString(Task task) {
		boolean temp = task.getStatusIcon().equals("X") ? true : false;
		if (task instanceof DeadlineTask) {
			DeadlineTask deadlineTask = (DeadlineTask) task;
			return "D | " + temp + " | "
					+ deadlineTask.getDescription() + " | " + deadlineTask.dateTimeString();
		} else if (task instanceof EventTask) {
			EventTask eventTask = (EventTask) task;
			return "E | " + temp + " | "
					+ eventTask.getDescription() + " | " + eventTask.dateTimeString();
		} else {
			TodoTask todoTask = (TodoTask) task;
			return "T | " + temp + " | " + todoTask.getDescription();
		}
	}

	public void addDukeToList(TaskList tasks) throws FileNotFoundException, DukeException {
		File file = new File(this.filePath); // create a File for the given file path
		Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] keywords = line.split(" \\| ", 4);
			switch (keywords[0]) {
			case "D":
				if (keywords.length != 4) {
					throw new DukeException("Incorrect information for deadline from input or file");
				}
				DeadlineTask deadlineTask = new DeadlineTask(keywords[2], keywords[3]);
				if(keywords[1].equals("X")) {
					deadlineTask.mark();
				} else {
					deadlineTask.unMark();
				}
				tasks.addStorageToList(deadlineTask);
				break;
			case "E":
				if (keywords.length != 4) {
					throw new DukeException("Incorrect information for event from input or file");
				}
				EventTask eventTask = new EventTask(keywords[2], keywords[3]);
				if(Boolean.parseBoolean(keywords[1])) {
					eventTask.mark();
				} else {
					eventTask.unMark();
				}
				tasks.addStorageToList(eventTask);
				break;
			case "T":
				if (keywords.length != 3) {
					throw new DukeException("Incorrect information for todo class from. input or file");
				}
				TodoTask todoTask = new TodoTask(keywords[2]);
				if(Boolean.parseBoolean(keywords[1])) {
					todoTask.mark();
				} else {
					todoTask.unMark();
				}
				tasks.addStorageToList(todoTask);
			}
		}
	}
}
