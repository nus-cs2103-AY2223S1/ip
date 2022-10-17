package duke.note;

/**
 * Stores a note which has title and content.
 */
public class Note {
    private String title;
    private String content;

    /**
     * Constructs a note.
     *
     * @param title Title of note.
     * @param content Content of note.
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Gets the title of the note.
     *
     * @return Title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the content of the note.
     *
     * @return Content of the note.
     */
    public String getContent() {
        return content;
    }

    /**
     * Checks if the note title matches the query.
     *
     * @param query Query for the note to check.
     * @return True if the note description matches the query.
     */
    public boolean isMatchingQuery(String query) {
        return title.contains(query);
    }

    /**
     * Gets a String array format of the note to save to file.
     *
     * @return String array.
     */
    public String[] getAsStringArray() {
        return new String[]{ title, content };
    }

    /**
     * Get String for printing to the screen.
     *
     * @return String for printing.
     */
    @Override
    public String toString() {
        return title;
    }
}
