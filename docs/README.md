# User Guide


Duke Aemon Of Old is a free, simple-to-use organization tool that can help you find order in your day. Aemon helps you add, delete and modify tasks, their deadlines and events. Your task list is automatically updated and saved by Aemon so that you can revisit the list anytime.

![This is an image](https://github.com/Aishwarya-Hariharan-Iyer/ip/blob/master/docs/Ui.png)

## Set Up

1. Make sure your computer has Java 11 installed. 
2. Navigate to the most recent release of the app on GitHub and download the .jar file available there.
3. Run the file and start interacting with Aemon on the window that opens up!

## Features 

### Feature-Greet Duke

Greet Duke Aemon and learn his backstory.

### Feature-Display All Commands

View all commands, their corresponding arguments and functions.

### Feature-Add Todo

Add a todo-task not associated with a date or time as a todo to your list. Upon addition, all todos are marked as incomplete. 

### Feature-Add Event 

Add an event along with its date, start and end time to your list. Upon addition, all events are marked as unattended. 

### Feature-Add Deadline

Add a deadline-task associated with a date and time for its completion to your list. Upon addition, all tasks are marked as incomplete. 

### Feature-Delete Task

Delete a task from your list permanently.

### Feature-Mark A Task As Complete

Mark a task as complete and see it relfected in your list.

### Feature-Mark A Task As Incomplete

Unmark a task (or, mark it incomplete) and see it reflected in your list.

### Feature-Sort List

Sort your list based on tasks' deadline or time of occurence (if any) and description (in that order).

### Feature-Find Tasks With Matching Descriptions

Search for tasks in your list that contain the search keyword in their description and view a list with only such tasks in it.

### Feature-Display List

See your list, along with number of items, task description, status and time (if any) displayed.

### Feature-End Conversation

End your current session with Duke (all changes to list are automatically saved). 

## Usage

### `hello` - Greet Duke

Introduces Duke Aemon and his story.

Command Syntax: `hello`

Example of usage:

`hello`

Expected outcome:

Displays Duke Aemon's greeting.

### `help` - Display All Commands

Helps the user refer to all commands he can use.

Command Syntax: `help`

Example of usage:

`help`

Expected outcome:

Displays a list of all commands and their usage.

### `todo` - Add Todo

Adds a todo task with given task description to the stored list of tasks.

Command Syntax: `todo TASK_DESCRIPTION`

Example of usage:

`todo study`

Expected outcome:

Confirms addition of item to list and displays number of items on list.

### `event` - Add Event 

Adds an event with given description, day and duration to the stored list of tasks.

Command Syntax: `event TASK_DESCRIPTION/yyyy-MM-dd HH:mm HH:mm`

Example of usage:

`event tutorial/2022-10-10 17:00 18:00`
`event FinTech meeting/2022-12-10 11:00 18:00`

Expected outcome:

Confirms addition of item to list and displays number of items on list.

### `deadline` - Add Deadline

Adds a task with given description, day and time of deadline to the stored list of tasks.

Command Syntax: `deadline TASK_DESCRIPTION/yyyy-MM-dd HH:mm`

Example of usage:

`deadline iP project/2022-09-16 18:00`
`deadline lab assignment/2022-05-16 09:00`

Expected outcome:

Confirms addition of item to list and displays number of items on list.

### `delete` - Delete task

Deletes the list object with given position in list.

Command Syntax: `delete TASK_NUMBER`

Example of usage:

`delete 1`
`delete 2`

Expected outcome:

Confirms deletion of chosen task and displays new number of tasks in list.

### `mark` - Mark task as complete

Marks task with given position on list as complete.

Command Syntax: `mark TASK_NUMBER`

Example of usage:

`mark 3`

Expected outcome:

Confirms marking the list as complete and displays the altered status of task.

### `unmark` - Mark task as incomplete

Marks task with given position on list as incomplete.

Command Syntax: `unmark TASK_NUMBER`

Example of usage:

`unmark 4`

Expected outcome:

Confirms marking the list as incomplete and displays the altered status of task.

### `sort` - Sort List

Sorts the tasks in the list based on dates, time, and description in that order. 

Command Syntax: `sort`

Example of usage:

`sort`

Expected outcome:

Displays the sorted list.

### `find` - Find items matching keyword

Finds and filters tasks in the list whose description contains the given keyword.

Command Syntax: `find KEYWORD`

Example of usage:

`find study`
`find CS2103`

Expected outcome:

Displays a list of all the items which have descriptions containing the keyword.

### `list` - View List

Lists all items in the list along with their status and type.

Command Syntax: `list`

Example of usage:

`list`

Expected outcome:

Displays the list stored.

### `bye` - End Conversation

Ends the current session with Duke Aemon.

Command Syntax: `bye`

Example of usage:

`bye`

Expected outcome:

Aemon confirms end of session and user exits the conversation.
