# User Guide

This is Duke, a chat bot to help you track your tasks.

## Features

- Adding a Todo task: `todo`
- Adding an Event task: `event`
- Adding a Deadline task: `deadline`
- Listing all tasks : `list`
- Locating task by keyword: `find`
- Deleting a task : `delete`
- Marking a task as done: `mark`
- Marking a task as not done: `unmark`
- Tagging a task: `tag`
- Exiting the program : `bye`

### `todo DESCRIPTION` - Adds a todo task with the specified description.

Example of usage:

`todo TODO EXAMPLE`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189971908-5fe12440-c4c9-4027-a5ec-bc6216d13a30.png)

### `event DESCRIPTION /at DATE` - Adds an event task with the specified description and date.
- Ensure date is in format <YYYY-MM-DD>

Example of usage:

`event EVENT EXAMPLE /at 2022-04-20`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972627-567efaf2-2378-4f46-aef8-50f1a874867e.png)

### `deadline DESCRIPTION /by DATE` - Adds a deadline task with the specified description and date.
- Ensure date is in format <YYYY-MM-DD>

Example of usage:

`deadline DEADLINE EXAMPLE /by 2022-04-20`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972673-22841325-9d06-4e6b-ba64-48d730bb101c.png)

### `list` - Lists all tasks currently saved in the list.

Example of usage:

`list`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972738-018e190b-c060-4e48-8a23-17df2dd878c4.png)

### `find KEYWORD` - Returns a list of tasks containing the keyword specified.

Example of usage:

`find Bob`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972765-78fb576e-732b-4d9f-a3fe-7911d0caf477.png)

### `delete INDEX` - Deletes the task in the list at that given index.

Example of usage:

`delete 4`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972818-00eee5f5-3f1f-4419-93aa-7381242142fd.png)

### `mark INDEX` - Marks the task as done in the list at that given index.

Example of usage:

`mark 2`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972845-c3b7b24a-cef3-4309-b8f3-a8fd2dbeb7b3.png)

### `unmark INDEX` - Marks the task as not done in the list at that given index.

Example of usage:

`unmark 2`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972880-2b1e5d72-a48b-4946-a80e-90d94a1f849a.png)

### `tag INDEX #HASHTAG` - Tags a task with a #hashtag at that given index.

Example of usage:

`tag 2 #TheFirstBob`

Expected outcome:

![image](https://user-images.githubusercontent.com/97376457/189972898-ca871fc5-9eb4-4e33-bfe8-8f496c6d783b.png)

### `bye` - Saves your current list and then closes the program.

Example of usage:

`bye`

Expected outcome:

```
The program terminates by itself.
```
