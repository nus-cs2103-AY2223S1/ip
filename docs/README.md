# User Guide
Duke is a task manager in the form of a chatbot which will keep track of all the tasks that you need to do. Currently, it supports tasks which you need to do some day without a deadline, tasks that need to be completed before a deadline, and tasks that you need to do at a certain time.

## Features

Duke can keep track of the following types of tasks:

- Todo: Tasks that you want to do someday
- Event: Tasks that you want to do at a certain time
- Deadline: Tasks that you want to do before a certain time

### Feature - Add tasks

You can easily ask Duke to add a new task to the list of task he is already keeping track of.

### Feature - Delete tasks

Made a mistake? Worry not, Duke can forget tasks which you decide are not important anymore.

### Feature - View tasks

You can ask Duke to show you the entire list of tasks at any time.

### Feature - Mark or Unmark tasks

Duke can help you to keep track of whether you have done your task or not.

### Feature - Find tasks

Have a long list of tasks? Duke can search through it to show you the tasks you want to do.

### Feature - Local save

Duke keeps a local copy of all the tasks, so the next time you load him up, he'll know what you still need to do! But there is no need because you will never exit the app right?

## Usage

### `todo` - Add a new Todo task

Adds a new todo task to the list to be kept track by Duke.

Example of usage: 

`todo (description)`

### `deadline` - Add a new Deadline task

Adds a new deadline task to the list to be kept track by Duke.

Example of usage:

`deadline (description) /by (date)`

### `event` - Add a new Event task

Adds a new event task to the list to be kept track by Duke.

Example of usage:

`event (description) /at (date)`

### `list` - List all the tasks kept track by Duke

Asks Duke to show the whole list of tasks that he is keeping track of.

Example of usage:

`list`

### `find` - Find all the tasks containing a certain substring

Asks Duke to show the list of tasks that contains a certain substring.

Example of usage:

`find (substring)`

### `mark` - Mark a task as completed

Marks a task being kept by Duke as completed.

Example of usage:

`mark (index)`

The index used is the one shown from the list command.

### `unmark` - Mark a task as not yet completed

Marks a task being kept by Duke as not yet completed.

Example of usage:

`unmark (index)`

The index used is the one shown from the list command.

### `delete` - Remove a task from the task list

Deletes a task from Duke's task list.

Example of usage:

`delete (index)`

The index used is the one shown from the list command.

### `bye` - Say goodbye to Duke and exit the program

Tells Duke you are done with using the program and exits. However, Duke would highly appreciate it if you do not use this command. In fact, Duke would prefer if you do not kill him...

Example of usage:

`bye`

Duke would not like it if you use this command or close the program.
