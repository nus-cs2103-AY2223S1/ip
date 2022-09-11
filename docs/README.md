Skylark is a desktop application used for you to keep track of your tasks easily. It provides an easy-to-use Graphical User Interface for users to input their commands quickly.

# User Guide
1. Ensure that you have `Java 11` or above installed on your local machine
2. Download the latest `Skylark.jar` from here
3. Copy the file to the folder you want to use as the home folder for Skylark. This folder should preferably be empty.
4. Double-click the file to start Skylark.

## Features 
* Keep track of your tasks easily
* Mark each task as done/not done
* Delete each task when not needed anymore
* Tag each task with an easy-to-remember tag if necessary
* Filter tasks by keywords
* Saves all details into text file for easily retrieval

## Usage

### Adding a task: `Todo`, `Event`, `Deadline`

Adds a task into Skylark.

The format for the command is as per follows:
1. `Todo`: `todo DESCRIPTION`
2. `Event`: `event DESCRIPTION /at DATE TIME`
3. `Deadline`: `deadline DESCRIPTION /by DATE TIME`

**NOTE** the following formats for the `DATE` and `TIME` descriptor mentioned above
* `TIME`: `HHmm`
* `DATE`: `yyyy-MM-dd`

Example of usage:
* `todo return book`
* `event Formal Hall Dinner /at 2022-09-14 1800`
* `deadline CS2103 IP /by 2022-09-18 1800`

Expected outcome: 
* After running `todo return book`
  ```
  Got it. I've added this task.
  [T][ ] return book
  Now you have 1 tasks in the list.
  ```
* After running `event Formal Hall Dinner /at 2022-09-14 1800`
  ```
  Got it. I've added this task.
  [E][ ] Formal Hall Dinner (at: Sep 14 2022)
  Now you have 2 tasks in the list.
  ```
* After running `deadline CS2103 IP /by 2022-09-18 1800`
  ```
  Got it. I've added this task.
  [D][ ] CS2103 IP (by: Sep 18 2022)
  Now you have 3 tasks in the list.
  ```
  
### Lists the current tasks in Skylark - `list`

Shows the list of tasks saved in Skylark

Example of usage:
* `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][X] return book
2. [E][ ] Formal Hall Dinner (at: Sep 14 2022)
3. [D][ ] CS2103 IP (by: Sep 18 2022)
```

### Delete a Task - `delete`

Delete a particular task from Skylark

Example of usage:
* `delete INDEX`

**NOTE:** The index must be an `integer` representing the index of the task

Expected outcome:
* After running `delete 2`
    ```
    Noted. I've removed this task:
    [E][ ] Formal Hall Dinner (at: Sep 14 2022)
    Now you have 2 tasks in the list
    ```

**NOTE:** If the index does not exist in Skylark, Skylark will simply reply with a:
* After running `delete 99`
    ```
    Sorry, index does not exist!
    ```

### Marking/Un-marking a Task as complete - `unmark`, `mark`

Marks/Un-marks a particular task from Skylark as done/not-done

Example of usage:
* `mark INDEX`
* `unmark INDEX`

**NOTE:** The index must be an `integer` representing the index of the task

Expected outcome:
* After running `mark 2`
    ```
    Noted. I've marked this task as done:
    [D][X] CS2103 IP (by: Sep 18 2022)
    ```
* Running `unmark 2` again
    ```
    Noted. I've marked this task as not done yet:
    [D][ ] CS2103 IP (by: Sep 18 2022)
    ```
**NOTE:** If the index does not exist in Skylark, Skylark will simply reply with a:
* After running `mark 99`
    ```
    Sorry, index does not exist!
    ```

### Tagging a particular task - `tag`

Tags a particular task with a keyword given by the user

Example of usage:
* `tag INDEX TAG`

**NOTE:** The index must be an `integer` representing the index of the task

Expected outcome:
* After running `tag 1 URGENT`
    ```
    Noted. I've tagged this task:
    [T][ ] return book TAG: URGENT
    ```

**NOTE:** If the index does not exist in Skylark, Skylark will simply reply with a:
* After running `tag 99 URGENT`
    ```
    Sorry, index does not exist!
    ```

### Finding a particular task - `find`

Finds any task from Skylark that contains the keywords given by the user

Example of usage:
* `find KEYWORD`

Expected outcome:
* After running `find CS2103`
    ```
    Here are the matching tasks in your list:
    1. [D][ ] CS2103 IP (by: Sep 18 2022)
    ```

**NOTE:** If there are no matching tasks, Skylark will simply reply with a:
* After running `find asdasdsada`
    ```
    Here are the matching tasks in your list:
    ```
  
### Exiting the program - `bye`

Exits the program

Example of usage:
* `bye`

### Saving data to and from Skylark

Tasks stored in Skylark are saved in the hard disk automatically after any command that changes the data. 