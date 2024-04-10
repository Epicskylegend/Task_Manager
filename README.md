
# Task Manager Application

## Code Structure:
### For our project our java classes are in inside the source directory. From there if you open the main directory you will see a java directory which has all of our java code. All of our java files are within the "com.example.task_manager" package.

### We also have a FMXL file called "Main_Display.fxml" that is responsible for displaying the main display. That file is also in the source directory. From there you can go to the resources directory and then to the "com.example.task_manager" package where you will find the file.

## Running Application:

### To run this application you'll first need to open up the docker application. Afterwards, in the root directory you'll need to enter in the command "docker compose up --build" to spin up the containers for the project. This will spin up the database and create the tables. Give it some time to build and create the tables. Afterwards, run the file called "DisplayApplication" inside the com.example.task_manager package.

## Testing Functional Requirements
## 1. Saving data to database: 

When you open up the application and click on "New Task, you will be prompted with a new window to enter in a task name, description, category, category color, and priority level. Once you fill out that information and click save it will save the data to the database. 

## 2. Loading data from database: 

After doing the above information, you can restart the application and you'll find that the tasks you created are still there because it's being loaded from the database.

## 3. Displaying tasks to user: 

When you open up the application and click on one of the 3 priority levels you will see the tasks displayed there. You can also click on these tasks to get more detailed information. 

## 4. Allow users to sort task by priority: 

Each of the tasks in the application will be put into one of the 3 priority levels which you can click on to show or hide tasks.

## 5. Allowing user to sort task by category: 

When click on the "Filter by Category" section on the left side of the application you will see a list of categories which you can click on to see tasks within those categories as long as you have clicked on priority level the task is in.  

## 6. Allow user to categorize tasks as complete: 

If you click on a task, in the top right corner of the window you will see a checkbox that says "completed." Once you click on that the task will be highlighted green signlaing that it's complete. 



## Testing Non-Functional Requirements

### 1. Reusability: Since this application can store a ton of tasks with different ways to filter them, it can be used for a long time to store many tasks.
### 2. Deployment: Starting up the application is made very simple. Once you have the folder, all you need to do is enter "docker compose up --build" in the root directory to build the containers and then run the "DisplayApplication" File. 
### 3. Data Persistence: With this application, since it's stored your data in a permanent database, it means that this application can be used over and over again.
### 4. Response Time: This application isn't super heavy and the amount of calls made to the database are limited ensuring that it's responsive. 
