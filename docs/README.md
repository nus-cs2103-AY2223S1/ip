Duke is a desktop application used to keep track of tasks with an interactive Graphical User Interface.

# User Guide
1. Ensure that you have `Java 11` or above installed on your local machine
2. Download the latest `Duke.jar` from [here](https://github.com/MinHeinA/ip/releases)
3. Copy the file to the folder you want to use as the home folder for Duke. This folder should preferably be empty.
4. Double-click the file to start Duke.

## Features 
* Add tasks with multiple parameters such as due date and priority easily
* Mark each task as done/not done
* Delete individual task easily
* Priority levels tagged for each task 
* Filter tasks by keywords
* Saves all details into text file for easily retrieval, migration and archiving 

## Usage

### Adding a task: `Todo`, `Event`, `Deadline`

Adds a task into Duke.

The format for the command is as per follows:
1. `Todo`: `todo DESCRIPTION [/priority PRIORITY LEVEL]`
2. `Event`: `event DESCRIPTION /at DATE TIME [/priority PRIORITY LEVEL]`
3. `Deadline`: `deadline DESCRIPTION /by DATE TIME [/priority PRIORITY LEVEL]`

**NOTE** the following formats for the `DATE TIME` descriptor mentioned above
* `DATE TIME`: `dd-MM-yyyy HHmm`

**NOTE** the following formats for the `PRIORITY LEVEL` optional descriptor mentioned above (case-insensitive)
* `PRIORITY LEVEL`: `High`, `Medium` or `Low (Default)` 

Example of usage:
* `todo Feed cat`
* `event Celebrate cat's birthday /at 15-09-2022 2200 /priority High`
* `deadline Train cat for competition /by 17-10-2022 0000`

Expected outcome: 
* After running `todo Feed cat`
  ```
  Got it. I've added this task.
  [T][][Low] Feed cat
  Now you have 1 tasks in the list.
  ```
* After running `event Celebrate cat's birthday /at 15-09-2022 2200 /priority High`
  ```
  Got it. I've added this task.
  [E][][High] Celebrate cat's birthday (at: Sep 15 2022)
  Now you have 2 tasks in the list.
  ```
* After running `deadline Train cat for competition /by 17-10-2022 0000`
  ```
  Got it. I've added this task.
  [D][][Low] Train cat for competition (by: Oct 17 2022)
  Now you have 3 tasks in the list.
  ```
  
**NOTE:** If the date time is given with an incorrect format, Duke will reply with a:
* After running `event Celebrate cat's birthday /at 15-09-2022 2200 /priority High`
    ```
    OOPS!!! Invalid priority level
    ```
  
**NOTE:** If the priority level given is incorrect, Duke will reply with a:
* After running `event Celebrate cat's birthday /at 15-09-2022 2200 /priority High`
    ```
    OOPS!!! Invalid priority level
    ```
  
### Listing the current tasks - `list`

Shows the list of tasks in Duke

Example of usage:
* `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][][Low] Feed cat
2. [E][][High] Celebrate cat's birthday (at: Sep 15 2022)
3. [D][][Low] Train cat for competition (by: Oct 17 2022)
```

### Deleting a Task - `delete`

Delete a specific task from Duke

Example of usage:
* `delete INDEX`

**NOTE:** `INDEX` must be an `integer` representing the index of the task in list

Expected outcome:
* After running `delete 3`
    ```
    Noted. I've removed this task:
    [D][][Low] Train cat for competition (by: Oct 17 2022)
    Now you have 2 tasks in the list
    ```

**NOTE:** If the task index does not exist, Duke will reply with a:
* After running `delete 100`
    ```
    OOPS!!! The task index is out of range
    ```

### Marking/Un-marking a Task as complete - `mark`, `unmark`

Marks/Un-marks a particular task from Duke as done/not-done

Example of usage:
* `mark INDEX`
* `unmark INDEX`

**NOTE:** `INDEX` must be an `integer` representing the index of the task in list

Expected outcome:
* After running `mark 2`
    ```
    Nice! I've marked this task as done:
    [E][X][High] Celebrate cat's birthday (at: Sep 15 2022)
    ```
* After running `unmark 2` 
    ```
    OK. I've marked this task as not done yet:
    [E][][High] Celebrate cat's birthday (at: Sep 15 2022)
    ```
	
**NOTE:** If the task index does not exist, Duke will reply with a:
* After running `delete 100`
    ```
    OOPS!!! The task index is out of range
    ```

### Setting priority level of a particular task - `priority`

Sets the priority level of a particular task with a keyword given by the user

Example of usage:
* `priority INDEX PRIORITY LEVEL`

**NOTE:** `INDEX` must be an `integer` representing the index of the task in list

**NOTE** the following formats for the `PRIORITY LEVEL` descriptor mentioned above (case-insensitive)
* `PRIORITY LEVEL`: `High`, `Medium` or `Low (Default)` 

Expected outcome:
* After running `priority 2 High`
    ```
    OK, I've set the priority of the task to High:
    [E][][High] Celebrate cat's birthday (at: Sep 15 2022)
    ```

**NOTE:** If the task index does not exist, Duke will reply with a:
* After running `priority 100 Low`
    ```
    OOPS!!! The task index is out of range
    ```
	
**NOTE:** If the priority level given is incorrect, Duke will reply with a:
* After running `priority 2 cat`
    ```
    OOPS!!! Invalid priority level
    ```

### Finding a specific task - `find`

Finds tasks that contains the keywords given by the user

Example of usage:
* `find KEYWORD`

Expected outcome:
* After running `find cat`

    ```
    Here are the matching tasks in your list:
    1. [T][][Low] Feed cat
	2. [E][][High] Celebrate cat's birthday (at: Sep 15 2022)
    ```

**NOTE:** If there are no matching tasks, Duke will reply with a empty list of tasks:
* After running `find dog`
    ```
    Here are the matching tasks in your list:
    ```
  
### Exiting the program - `bye`

Exits the program

Example of usage:
* `bye`

### Saving the data

Tasks stored in Duke are saved in the hard disk automatically after the `bye` command is ran. There is no need to save manually. 