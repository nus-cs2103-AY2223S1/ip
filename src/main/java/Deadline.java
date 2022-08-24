public class Deadline extends Task {

  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, String by, boolean isDone) {
    super(description, isDone);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by: " + by + ")";
  }

  // Deadline strings look like: [D][ ] return book (by: June 6th)
  public static Deadline stringToDeadline(String s) throws DukeException {
    if (!s.startsWith("[D][")) {
      throw new DukeException("This string is not a Deadline string!");
    }

    char isDoneString = s.charAt(4); //[T][X] checks if X is present
    char X = 'X';
    boolean isDone = isDoneString == X;

    int idxOfBy = s.indexOf("(by:");

    String description = s.substring(7, idxOfBy);
    String byString = s.substring(idxOfBy + 5, s.length() - 1); // to avoid the brackets
    return new Deadline(description, byString, isDone);
  }

  public static void main(String[] args) throws DukeException {
    String testString = "[D][ ] return book (by: June 6th)";
    System.out.println(stringToDeadline(testString));
  }
}