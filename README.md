# User Guide
Ploopy is a personal chatbot who can manage your tasks so you don't have to worry about
constantly keeping track of them!

![image](https://user-images.githubusercontent.com/97420952/189854100-433189ce-8b31-40df-a333-1e578ebb96ec.png)


## Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ploopy.jar` from here.

3. Copy the file to the folder you want to use as the home folder for your Ploopy.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. e.g. 

Some example commands you can try:

`list` : Lists all tasks.

`todo clean room `: Adds a ToDo task called clean room.

## Features
- [x] Add a ToDo task.
- [x] Add a Deadline task.
- [x] Add an Event task.
- [x] List all tasks.
- [x] Mark/ unmark a task as done/ undone.
- [x] Delete a task.
- [x] Find a task by name.
- [x] Set the priority of a task.

## Adding a ToDo task: `todo` 
Adds a ToDo task with the provided name

Format: `todo <task name>`

Example:
`todo clean room`

## Adding a Deadline task: `deadline`
Adds a Deadline task with the provided name and deadline

Format: `deadline <task name> /by <task deadline>`

Example:
`deadline assignment 1 /by 15/09/2022 1800`
`deadline project /by 12/08/2022 0600`

- name and deadline must be seperated by `/`
- deadline must be in the `dd/mm/yyyy hhmm` format
- time is require for deadline and must be in 24-hour format
- if time is single digits then 0 must be inserted. e.g. `630` must be `0630`.

## Adding an Event task: `event`
Adds an Event task with the provided name and date

Format: `event <task name> /at <task date>`

Example:
`event graduation /at 15/04/2025 1200`
`event breakfast /at 12/10/2022 0800`

- name and date must be seperated by `/`
- date must be in the `dd/mm/yyyy hhmm` format
- time is require for date and must be in 24-hour format
- if time is single digits then 0 must be inserted. e.g. `630` must be `0630`.

## Listing all tasks: `list`
Displays all tasks

Format: `list`

### Example:

![image](https://user-images.githubusercontent.com/97420952/189849325-b19b61d4-4c9d-459e-9b12-2e6479616a04.png)

## Mark a task: `mark`
Marks the specified task as done.

Format: `mark <task number>`

- number is according to `list`
 
### Example: 
`mark 2`

![image](https://user-images.githubusercontent.com/97420952/189850777-4972008f-3653-4eab-bff4-375644e72302.png)

## Unmark a task: `unmark`
Marks the specified task as done.

Format: `unmark <task number>`
- number is according to `list`
### Example: 
`unmark 2`

![image](https://user-images.githubusercontent.com/97420952/189850850-a728e63c-975a-454f-84e8-b5a0c4d42a5d.png)

## Delete a task: `delete`
Deletes the specified task.

Format: `delete <task number>`
- number is according to `list`
### Example: 
`delete 4`

![image](https://user-images.githubusercontent.com/97420952/189851203-08c0c032-3d52-41d2-8a03-dbe57c9f0fd0.png)

## Find tasks `find`
Finds tasks matching the given description.
Format: `find <description>`
### Example:
`find assignment`

![image](https://user-images.githubusercontent.com/97420952/189851614-1d4f2622-4320-42f2-85a0-01afc5359771.png)

## Set task priority: `priority`
Sets the specified task's priority to high or none.

Format: `priority <task number> <priority to set to>
### Example:
`priority 2 high`
`priority 2 none`

![image](https://user-images.githubusercontent.com/97420952/189853616-8c6eae6c-dc2f-4f8b-ae7f-47811b92f315.png)

- use "high" for high priority and "none" to remove priority
- only high and no priority is supported

## Exit program: `bye`
Exits the program

Format: `bye`






