# User Guide
Here is Duke, a friendly task manager robot!

## Features 
Manage tasks
Duke helps you to keep track of 3 types of tasks: Todos, Events and Deadlines. 
You can add a new task or delete an old task. 
You can also mark a task as completed.

Delete task
Once you are done with task you can remove it from the list by deleting it.

Find tasks
Duke can search for a specific task stored in the task list based on the keyword that the user enters.

Auto-Save
Duke automatically saves all tasks in your local storage, you can be assured your task list will be saved when you close the application.
It will automatically be loaded up when you open the application again!

Dates 
Duke is able to read and understand dates but specific commands must be used which is not written here.
It is in the code base for now.

## Usages

### `Todo` - Creates a todo task

Adds a new "todo" into your task list.

Sample command: todo workout

```
expected output
```
`Got it. I've added this task:
[T][X] workout
Now you have 1 tasks in the list.`

### `event <description> /at String` - Creates an event task

Adds a new event into your task list.

Sample command: event meeting john /at 8-8-2023

```
expected output
```
`Got it. I've added this task:
[E][X] meeting john (at:8-8-2023)
Now you have 2 task(s) in the list.`

### `deadline <description> /by String` - Creates a deadline task

Adds a new deadline into your task list.

Sample command: deadline math homework /by 6-6-2023

```
expected output
```
`Okay! I've added this task:
[D][X] math homework (by:6-6-2023)
Now you have 3 task(s) in the list.`


### `List` - Show all current objects in the list

List all objects in the task list.

Sample command: list

```
expected output
```
`1.[T][X] workout
 2.[E][X] meeting john (at:8-8-2023)
 3.[D][X] math homework (by:6-6-2023)`

### `mark` - mark a task

Marks a task as done by index.

Sample command: mark 1

```
expected output
```
`Nice! I've mark this task as done:
[/] workout`

###  `delete` <index> - Delete an object of specific index from the list

Deletes a task by index from the list.

Sample command: delete 2

```
expected output
```
`Noted. I've remove this task:
[E][] meeting john (at:8-8-2023)
Now you have 2 task(s) in the list.`

## find <keyword> - Searches for tasks

Search for a task that match the keyword

Sample command: `find math`

```
expected output
```
Here are the task(s) with your keyword!
`1.[D][X] math homework (by:6-6-2023)`

### `bye` - Quits the application

Exits the application



