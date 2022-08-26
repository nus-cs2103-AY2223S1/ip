/**
 * The DukeException class holds methods that will handle
 * checked exceptions that may arise in the Duke class
 *
 * @author lauralee
 */
public class DukeException {

    /**
     * todo Exception handler
     */
    public static void todoException() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * task Exception handler
     */
    public static void taskException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * non-existent file Exception handler which may occur when
     * file has not been created before and thus does not exist yet
     */
    public static void nonexistentFileException() {
        System.out.println("☹ OOPS!!! The file does not exist!");
    }
}
