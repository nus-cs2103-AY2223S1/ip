#https://github.com/24Donovan24/ip/blob/master/docs/README.md
#@@24Donovan24 

#Adapted @@24Donovan24 features portion

#Adapted @@24Donovan24 usage portion -Rewrite and reused some

#Used the above user guide for reference in helping me craft a better one

#i will try to rewrite this if i have time later

#for i will have to thank @@24Donovan24 for helping me craft a rough one


# User Guide
Here is Duke, a friendly task manager robot!

## Features 
Manage tasks
Duke helps you to keep track of 3 types of tasks: Todos, Events and Deadlines. 
You can add a new task or delete an old task. 
You can also mark a task as completed or unmark it as uncompleted.

Find tasks
Duke can help you search for tasks stored in your task list based on the keyword you enter!

Auto-Save
Duke automatically saves your tasks in local storage, so you can always retrieve the data again even 
after closing the application!

## Usage

### `Todo` - Creates a todo task

Adds a new "todo" into your task list.

Example of usage: todo go to gym

`Okay! I've added this task:
[T][] go to gym
Now you have 1 task(s) in the list.`

### `event <description> /at <date>` - Creates an event

Adds a new event into your task list.

Example of usage: event meeting /at 2023-12-12

```
expected output
```
`Okay! I've added this task:
[E][] meeting (at: Dec 12 2023)
Now you have 2 task(s) in the list.`

### `deadline <description> /by <date>` - Creates a deadline

Adds a new "todo" into your task list.

Example of usage: deadline watch lecture /by 2023-12-12

`Okay! I've added this task:
[D][] watch lecture (by: Dec 12 2023)
Now you have 3 task(s) in the list.`


### `List` - Show all current objects in the list

List all objects in your task list

Example of usage: list

`Here are the task(s) in your list so far!
1. [T][] go to gym
2. [E][] meeting (at: Dec 12 2023)
3. [D][] watch lecture (by: Dec 12 2023)`

### `mark` - mark a task

Shows the task is done

Example of usage: mark 1

`Okay! I've mark this task as done:
[T][/] go to gym`

delete <index> - Deletes object from tasklist

Removed an object that is already done

Example of usage: delete 2

`Okay! I've remove this task:
[T][] go to gym
Now you have 1 task(s) in the list.`

find <keyword> - Searches for tasks

Search for a task that match the keyword

Example:Find book

Here are the task(s) with your keyword!
1. [T][] return book

bye - Quits the application
Exits the application



