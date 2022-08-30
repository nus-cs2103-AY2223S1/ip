# JennyBot Application
> "insert cliché programming related quote here" - Deon ([Browse](https://betterprogramming.pub/101-funny-programmer-quotes-76c7f335b92d))

JennyBot befriends you and helps you remember your tasks when no one else does.

## Features of JennyBot :hot_face::sweat_drops::sweat_drops:
- Remembers **three** different types of tasks:
  1. Todo task (you know, just a task)
  2. Deadline task (due by a certain date)
  3. Event task (occurring at a certain date)
- **Backup** your task list locally (user/.jenny/storage/tasks)
- Mark and unmark your tasks as **completed** or **uncompleted**
- **Finds** which tasks you are looking for

### List supported commands for JennyBot :wink:

1. Add a _Todo_ task `todo <description>`
2. Add a _Deadline_ task `deadline <description> /by <yyyy-MM-dd>`
3. Add a _Event_ task `event <description> /at <yyyy-MM-dd>`
4. List all available tasks `list`
5. Mark a task as complete `mark <index>`
6. Unmark a task as complete `unmark <index>`
7. Delete a task from the list `delete <index>`
8. Find a task by description `find <description>`
9. Exit the application `bye`

### Upcoming features from developers of JennyBot:

- [x] Using checkstyle to detect coding violations
```groovy
checkstyle {
    toolVersion = '10.2'
}
```
- [ ] Fresh slick Graphical User Interface

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class JennyBot extends Application {

  // ...

  @Override
  public void start(Stage stage) {
    Label helloWorld = new Label("Hello World!"); // Creating a new Label control
    Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

    stage.setScene(scene); // Setting the stage to show our screen
    stage.show(); // Render the stage.
  }
}
```

- [ ] Usage of variable arguments in JennyBot

```java
public String formatWithVarArgs(String... values) {
    // ...
}

formatWithVarArgs();
formatWithVarArgs("a", "b", "c", "d");
```

```
   Hello from  |
               |
               |
               |  the other side
```
