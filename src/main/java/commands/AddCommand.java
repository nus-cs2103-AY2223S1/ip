package commands;

import exception.*;
import java.util.ArrayList;
import main.*;

public class AddCommand extends Command {

  private String description;

  public AddCommand(String description) {
    this.description = description;
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    tasklist.add(new Task(this.description));
    System.out.println("added: " + this.description);
  }
}
