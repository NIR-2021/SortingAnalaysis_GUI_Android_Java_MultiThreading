There are two ways to run the program.

You can install the apk "DaaProject" which can be found in Project\app\build\outputs\apk\debug.
Or
You can load the project folder in android studio and run the application manually.

This project was build with intention to study the time complexity of each sorting algorithm.
Instead of running the program on the main thread and halting the UI while the sorting are being performed, I have implemented these sorts on seperate threads and so as to the reduce the time and also keep the main thread free for UI operations.After running all the threads the program generates a graph that compares all the times take by each sorting algorithm.
