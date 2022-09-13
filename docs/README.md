# User Guide
Welcome to Duke's user guide.

### Installation Guide
1. Ensure you have Java 11 and above installed
2. Download the latest version of the jar file in the Releases tab on the right
3. Open a command window in the folder containing the jar file (On Windows, Shift + Right click to open Powershell/Command Prompt in that directory)
4. Copy and paste the command `java -jar -ea Duke.java` into the command window
5. Your Duke application should start now!

## Features

### List all Tasks:
Simply type `list` to display all the tasks you currently have.

### Create a Task:
Tasks are your things to do. They are categorised into 3 types: Todo, Event and Deadline.

Create a new Todo task by simply typing `todo` followed by a spacing and your task name.
Example: `todo homework`

For tasks that will happen at a particular date, use the Event task type instead. To create an event, type `event` followed by your task name and then  `/at` and a date specified in the YYYY-MMM-D format.
Example: `event visit friend /at 2022-Mar-12`

For tasks that must be done before a particular date, use the Deadline task type instead. To create a deadline, type `deadline` followed by your task name and then  `/by` and a date specified in the YYYY-MMM-D format.
Example: `deadline Assignment 1 /by 2022-Mar-12`

### Mark and Unmark Tasks:
Mark some tasks as complete by using the `mark` command followed by the task number(s). For example, marking tasks 1, 2 and 4 as complete can be done by typing:
`mark 1 2 4`

Unmark some tasks as incomplete by using the `unmark` command followed by the task number(s). For example, unmarking tasks 1, 2 and 4 as incomplete can be done by typing:
`unmark 1 2 4`

### Delete Tasks:
Delete some tasks from the list by using the `delete` command followed by the task number(s). For example, deleting tasks 1, 2 and 4 from your list can be done by typing:
`delete 1 2 4`

### Find Tasks:
Find all tasks from the list by using the `find` command followed by your search term (only 1 word long). For example, finding all tasks with the word "homework" in them can be done by typing:
`find homework`

### Exit Duke
To exit Duke, simply type `bye`. Duke remembers your tasks every time you run it. Your tasks are autosaved whenever any changes to the list are applied. Convenient, isn't it?