# User Guide
Henry is a **desktop application for keeping track of tasks, deadlines,
events and supports keeping track of loans given/taken, optimized for
use via a Command Line Interface (CLI)**,
while still having the benefits of a Graphical User Interface (GUI). If you
can type fast, Duke can help you get your goals achieved faster than
traditional GUI applications.

## Features
- [Echo user input: echo](#echo)
- [Showing all tasks: list](#list)
- [Creating a ToDo: todo](#todo)
- [Creating an Event: event](#event)
- [Creating a Deadline: deadline](#deadline)
- [Marking a Task: mark](#mark)
- [Unmarking a Task: unmark](#unmark)
- [Find a Task: find](#find)
- [Deletes a Task: delete](#delete)
- [Add a Tentative Date to an Event: tentative](#tentative)
- [Teach: teach Henry a new word](#teach)


## Usage

### <span style="color: orange;" id="echo">Repeating user input - `echo`</span>
Copies the user’s input and prints it

**Example of usage:**

`echo hello`

**Expected outcome:**

![echo expected outcome](https://user-images.githubusercontent.com/89738860/190916529-a5d70530-7b15-416a-a0c8-bf2affccb55c.png)


### <span style="color: orange;" id="list">Showing all tasks - `list`</span>
Lists all tasks that the user has

**Example of usage:**

`list`

**Expected outcome:**

![list expected outcome](https://user-images.githubusercontent.com/89738860/190916554-25bda231-db9f-49a0-ac38-fc680c433fe3.png)


All results shown. In this case, user has 4 tasks (2 todo, 1 event, 1 deadline)

### <span style="color: orange;" id="todo">Creating a ToDo - `todo`</span>
Creates a Todo task 

**Format:**

`todo taskname`
- `taskname` can be of any length, and works for multiple words

**Example of usage:**

`todo bake cake`

**Expected outcome:**

![todo expected outcome](https://user-images.githubusercontent.com/89738860/190916663-19c2f0ff-659e-4b6f-a31b-756ea0699dfc.png)


### <span style="color: orange;" id="event">Creating an Event - `event`</span>
Creates an Event

**Format:**

`event description /at date time`
- `description` can be of any length, and works for multiple words
- `date` can be in the form 'dd-MM-yyyy', 'dd/MM/yyyy', 'dd-MM-yyyy' or 'dd MMM yyyy'. Note: For formats in the form '12 Jun 2023', the name of the month must be capitalized.
- `time` must be in 24 hours format, with a colon (e.g. 23:59)
- Henry will not accept dates that are in the past

**Example of usage:**

`event meet Henry /at 12-12-2022 18:00`

**Expected outcome:**

![event expected outcome](https://user-images.githubusercontent.com/89738860/190916732-eebdee0f-d410-47f2-9990-67fbd89fefd9.png)


### <span style="color: orange;" id="deadline">Creating a Deadline  - `deadline`</span>
Creates a Deadline

**Format:**

`deadline description /by date time`
- `description` can be of any length, and works for multiple words
- `date` can be in the form 'dd-MM-yyyy', 'dd/MM/yyyy', 'dd-MM-yyyy' or 'dd MMM yyyy'. Note: For formats in the form '12 Jun 2023', the name of the month must be capitalized.
- `time` must be in 24 hours format, with a colon (e.g. 23:59)
- Henry will not accept dates that are in the past

**Example of usage:**

`deadline Written Assignment /by 12-12-2022 18:00`

**Expected outcome:**

![deadline expected outcome](https://user-images.githubusercontent.com/89738860/190916746-e9d04765-3982-4b28-8938-a49169008abc.png)


### <span style="color: orange;" id="mark">Marking a Task - `mark`</span>
Marks a task as complete

**Format:**

`mark index`
- `index` must be an integer, and is the index of the task in list
- `index` starts from 0, so accessing the first task in list would be accessing index 0

**Example of usage:**

`mark 1`

**Expected output:**

![mark expected outcome](https://user-images.githubusercontent.com/89738860/190916764-56a6f678-be68-4607-bf36-b0892fb84813.png)


### <span style="color: orange;" id="unmark">Unmarking a Task - `unmark`</span>

Marks a task as incomplete

**Format:**

`unmark index`
- `index` must be an integer, and is the index of the task in list
- `index` starts from 0, so accessing the first task in list would be accessing index 0

**Example of usage:**

`unmark 1`

**Expected output:**

![unmark expected outcome](https://user-images.githubusercontent.com/89738860/190916791-1ff9931d-eb1c-424a-8a23-a9e9a33d209f.png)


### <span style="color: orange" id="find">Find a Task - `find`</span>
Finds all tasks that match the search input pattern

**Format:**

`find searchInput`
- `searchInput` is the keyword that is matched to the list of tasks
- `searchInput` can be of any length, and supports multiple words with the usage of `--`, e.g. `find --read --book`

**Example of usage:**

`find --book --read`

**Expected output:**

Only if a task with a description containing "book" and "read" exists as a task in your list!

![find expected outcome](https://user-images.githubusercontent.com/89738860/190916813-aec6c580-5225-4856-8500-c3dfdd3a0ffc.png)


### <span style="color: orange" id="delete">Deletes a Task - `delete`</span>
Deletes a task from the list

**Format:**

`delete index`
- `index` must be an integer, and is the index of the task in list
- `index` starts from 0, so accessing the first task in list would be accessing index 0

**Example of usage:**

`delete 0`

**Expected outcome:**

![delete expected output](https://user-images.githubusercontent.com/89738860/190916827-4ef34106-699a-47ba-9177-dd3eb38ce368.png)


### <span style="color: orange;" id="tentative">Adding Tentative Dates to an Event - `tentative`</span>
Can either add a tentative date or confirm a tentative date for an Event type task

**Format:**

**Usage 1:**

`tentative index date time`
- `index` must be an integer, and is the index of the task in list
- `index` starts from 0, so accessing the first task in list would be accessing index 0
- `date` can be in the form 'dd-MM-yyyy', 'dd/MM/yyyy', 'dd-MM-yyyy' or 'dd MMM yyyy'. Note: For formats in the form '12 Jun 2023', the name of the month must be capitalized.
- `time` must be in 24 hours format, with a colon (e.g. 23:59)
- Henry will not accept dates that are in the past

**Example of usage:**

`tentative 1 12 Jun 2024 14:00`

**Expected outcome:**

![tentative expected outcome](https://user-images.githubusercontent.com/89738860/190916853-5fcce2c1-870f-446f-bfe9-aa66b6bb54bf.png)



**Usage 2:**

`tentative index --confirm index2`
- `index` must be an integer, and is the index of the task in list
- `index` starts from 0, so accessing the first task in list would be accessing index 0
- `index2` must be an integer, and is the index of the tentative date to be chosen
- `index2` starts from 0, so accessing the first date in the date list would be accessing index 0

**Example of usage:**

`tentative 1 --confirm 1`

**Expected outcome:**

![tentative expected outcome](https://user-images.githubusercontent.com/89738860/190916863-e25a47f1-5e42-4e1d-896c-37494e7952ea.png)


### <span style="color: orange" id="teach">Teach Henry a new word - `teach`</span>

Teach Henry new words.

**Format:**

`interact X is a Y`
- `X` is the name of the word to teach Henry
- `Y` is what you want Henry to remember X as

**Example of usage:**

`interact a potato is a plant`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/89738860/190916883-ec6450d5-8fd0-478c-9cbd-87d6d1bd8432.png)


### <span style="color: orange" id="bye">Exiting Program - `bye`</span>

Saves and closes the program

**Example of usage:**

`bye`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/89738860/190916898-f0db7989-e79c-4baa-b4e0-1da249d14bcf.png)
