
<h2><span style="color:orange">Quick start</span></h2>

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/wweqg/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the *home folder* for your Duke.
4. Right-click and open your terminal, enter `java -jar duke.jar` to start the program. The GUI similar to the below should appear in a few seconds.

![Ui](https://wweqg.github.io/ip/Ui.png)

5. Type the command in the text field and press Enter to execute it.

> E.g: typing `help` and pressing Enter will display the help message



<h2><span style="color:orange">Features</span></h2>

> Words in UPPER_CASE `<user input>` bracket are parameters to be supplied by the user.
>
> `< >` brackets are used to signify user input, it should be omitted when using the program.  

### Viewing help: `help`

Shows a message listing the available commands in the program.

Format: `help`



### Listing all tasks: `list`

Shows a list of all tasks in the program.

Format: `list`



### Adding a Task: `todo/deadline/event`

There are three types of task that the program currently support, hence there are three types of command for these tasks.

#### Add a Todo task: `todo`

This command adds a todo task into the task list.

Format: `todo <DESCRIPTION>`

Example: `todo wash clothes`

#### Add a deadline task: `deadline`

This command adds a deadline task into the task list.

Format: `deadline <DESCRIPTION> /by <DATE AND TIME>`

Example: `deadline tutorial /by Oct 10 2022 10:30`

Acceptable formats for `<DATE AND TIME>`:

- “MMM dd yyyy HH:mm”, e.g. `Aug 10 2022 13:30`
- “dd/MM/yyyy HH:mm”, e.g. `10/08/2022 13:30`
- “yyyy/MM/dd HH:mm”, e.g. `2022/08/10 13:30`
- “yyyy/MM/dd’T’HH:mm”, e.g. `2022/08/10T13:30`
- “yyyy-MM-dd HH:mm”, e.g. `2022-08-10 13:30`
- “dd MMM yyyy HH:mm”, e.g. `10 Aug 2022 13:30`
- “MMM dd, yyyy HH:mm”, e.g. `Aug 10, 2022 13:30`

(you could omit the colon between HH and mm)

#### Add an event task: `event`

This command adds an event task into the task list.

Format: `event <DESCRIPTION> /at <DATE AND TIME>`

Example: `event dinner /at Oct 10 2022 10:30`

Acceptable formats for `<DATE AND TIME>`:

- “MMM dd yyyy HH:mm”, e.g. `Aug 10 2022 13:30`
- “dd/MM/yyyy HH:mm”, e.g. `10/08/2022 13:30`
- “yyyy/MM/dd HH:mm”, e.g. `2022/08/10 13:30`
- “yyyy/MM/dd’T’HH:mm”, e.g. `2022/08/10T13:30`
- “yyyy-MM-dd HH:mm”, e.g. `2022-08-10 13:30`
- “dd MMM yyyy HH:mm”, e.g. `10 Aug 2022 13:30`
- “MMM dd, yyyy HH:mm”, e.g. `Aug 10, 2022 13:30`

(you could omit the colon between HH and mm)



### Editing a task: `edit`

Edits an existing task in the task list.

Format: `edit <INDEX> <NEW TASK>`

+ Edits the task at the specified `INDEX`. The index refers to the index number in the task list. The index **must be a positive integer** 1, 2, 3, ...
+ `NEW TASK` should be entered with the same format as adding a task.
+ Existing task will be updated to the new input task.

Examples:

`edit 1 event lunch /at Oct 10 2022 1030` Edits the first task to a new event task.



### Locating task by keyword: `find`

Finds tasks that contain any of the given keywords.

Format: `find <KEYWORD>`

+ The search is case-insensitive

Example: `find wash`, `find tutorial`



#### Deleting a task: `delete`

Deletes the specified task from the program.

Format: `delete <INDEX>`

+ Deletes the task at the specified `INDEX`.
+ The index refers to the index number in the task list. 
+  The index **must be a positive integer** 1, 2, 3, ...

Example:

`delete 2` deletes the second task in the program.



#### Marking a task: `mark`

Marks the specified task in the program as done.

Format: `mark <INDEX>`

+ Marks the task at the specified `INDEX`.
+ The index refers to the index number in the task list. 
+  The index **must be a positive integer** 1, 2, 3, ...

Example:

`mark 3` marks the third task in the program.



#### Unmarking a task: `unmark`

Unmarks the specified task in the program.

Format: `unmark <INDEX>`

+ Unmarks the task at the specified `INDEX`.
+ The index refers to the index number in the task list. 
+  The index **must be a positive integer** 1, 2, 3, ...

Example:

`unmark 1` unmarks the first task in the program.



### Exiting the program: `bye`

Exits the program.

Format: `bye`



![:exclamation:](https://github.githubassets.com/images/icons/emoji/unicode/2757.png) **Caution:** Avoid from modifying the program data in the data file.



### Command summary

| Action   | Format                                     |
| -------- | ------------------------------------------ |
| Help     | help                                       |
| List     | list                                       |
| Todo     | todo \<DESCRIPTION\>                         |
| Deadline | deadline \<DESCRIPTION\> /by \<DATE AND TIME\> |
| Event    | event \<DESCRIPTION\> /at \<DATE AND TIME\>    |
| Edit     | edit \<INDEX\> \<NEW TASK\>                    |
| Find     | find \<KEYWORD\>                             |
| Delete   | delete \<INDEX\>                             |
| Mark     | mark \<INDEX\>                               |
| Unmark   | unmark \<INDEX\>                             |
| Exit     | bye                                        |

