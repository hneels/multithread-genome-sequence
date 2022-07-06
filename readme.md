# Multithreading in Java

Hope Neels
<hr>

*Note: this project was an assignment for CS622: Advanced Programming Techniques. Copying any portion and submitting it as your own work is a violation of Boston University's Academic Conduct Code and is prohibited.*

## Summary
This Java command-line program is an exploration of how performance is increased (or not!) when using multithreading with Thread and ExecutorService classes. I conducted timed experiments on three versions of the program with various data input sizes; see below for my observations.

## Genome Class
This class contains one method, makeSequence(), which is a static method that returns a String of length 10 containing a random assortment of the letters A,T,G,C, representing a genome sequence. The method uses a char array, a Random int generator, and a StringBuilder object to generate a random letter chosen from A,T,G,C for each of the ten characters in the sequence. When all 10 characters have been appended, it returns the String representation of the StringBuilder that holds the sequence.

## GenomeTask Class
This class implements the Runnable interface and defines its run() method to call the Genome class's makeSequence() method 20 times, additionally printing information about when the thread begins and finishes its work. In the runtime tests below, the for-loop on line 22 is adjusted to create larger data sets. Because it implements Runnable, a new GenomeTask object can be passed to the Thread constructor (as in Version2 below) or to ExecutorService's execute() method (as in Version3 below).

## Version1 Class
This class contains a main() method that creates 100 random genome sequences by calling Genome.makeSequence() 100 times, using a for-loop. It clocks the start time before it begins, then prints the elapsed time when the process is complete. Since there is no concurrency in this version, it does not use the GenomeTask class. (To create larger data sets in the tests below, the number of for-loop iterations is simply increased.)

## Version2 Class
This class updates the Version1 class, now using concurrency in an attempt make the process faster. (As you can see in the runtime tests below, it actually runs slower.) The process start time is saved so the elapsed time can be printed at the end. Five new threads are created in a for-loop, passing a new GenomeTask into the constructor of each, and the Threads are stored in an array so they can be accessed again once all threads have started running. (In the runtime tests below, I experiment with 5, 4, 3, and 1 spawned threads.) Once all five Threads are running, the Threads array is iterated over and each thread is joined to the main thread, ensuring that the elapsed time is only printed once all threads are finished. (Importantly, join() can't be called in the first loop where the threads are started, otherwise the main thread would wait for each thread to finish before starting the next one and they wouldn't run concurrently as they do now.)

## Version3 Class
This class uses concurrency like Version2, but it uses the ExecutorService class to create and manage the running threads instead of the Thread class. As in the other classes, the elapsed runtime of the process is printed to the console. An ExecutorService object is created and its execute() method is called to create five new threads, passing each a new GenomeTask. (As in Version2, the number of iterations on line 24 is adjusted in the runtime tests below.) After all threads are started, shutdown() and awaitTermination() are called on the ExecutorService object to ensure that the main() method waits for all threads to finish, so that the proper elapsed time is printed when all tasks are complete.

## Runtime Experiments
To test the efficiency of the multithreading process, I tested the three versions using 100, 10,000, and 1,000,000 total printed genome sequences, and 1, 2, 4, and 5 threads. For each test, I calculated the runtime as an average of 3 runs, and the total overall number of genome sequences printed was the same regardless of how many threads shared the work.

***Average runtimes are in milliseconds***

### 100 Genome Sequences
**Version1:** 4.67 ms

|               | Version2 | Version3 |
|---------------|----------|----------|
| **5 Threads** | 7        | 10.67    |
| **4 Threads** | 6.67     | 10.67    |
| **2 Threads** | 6.67     | 10       |
| **1 Thread**  | 5.67     | 9        |


### 10,000 Genome Sequences
**Version1:** 73.33 ms

|               | Version2 | Version3 |
|---------------|----------|----------|
| **5 Threads** | 92.33    | 103.33   |
| **4 Threads** | 101.33   | 107      |
| **2 Threads** | 96.33    | 98.67    |
| **1 Thread**  | 74.33    | 79       |


### 1,000,000 Genome Sequences
**Version1:** 3026 ms

|               | Version2 | Version3 |
|---------------|----------|----------|
| **5 Threads** | 4123     | 5702     |
| **4 Threads** | 5431     | 4728     |
| **2 Threads** | 4880     | 4727     |
| **1 Thread**  | 2811     | 2773     |


## Discussion
Although multithreading may increase efficiency in some applications, for this simple program in which few calculations are done and all threads print to the console, the time spent creating threads and switching between them outweighs any potential time saved. For all three genome sequence data sizes, the runtime of the single-thread Version1 is better than Version2 and Version3 with multithreading. However, as the data size gets larger, the margin between the single-thread and multithread processes becomes smaller. My theory is that multithreading is better suited for programs in which threads have to wait idle for periods of time (such as awaiting a response from disk), the threads don't depend on the same resource (such as console output), or each thread does a large amount of CPU calculations. For this program in which all the threads attempt to print to the console continuously, the high overhead of switching between threads slows it down. System.out.println is synchronized, so no matter how many threads are running, only one can be printing to the console at any given time.

One interesting observation is that for the largest dataset (1 million genome sequences) the Version2 and Version3 programs in which one new thread is spawned (in addition to the main() thread) are faster than Version1 (in which control stays in the main() thread). This may be an anomaly, but it's still a surprising result. I did a bit of research and could not find any hints about what might produce this unusual outcome.

Some resources to support my hypothesis about why multi-threading is slower in this case:
* https://stackoverflow.com/questions/18584809/java-system-out-effect-on-performance

* https://stackoverflow.com/questions/11798639/multithreading-takes-more-time-than-sequential-execution-in-c-sharp

* https://stackoverflow.com/questions/55551259/why-executor-service-takes-more-time-than-sequential-operation-in-java-8

* https://stackoverflow.com/questions/17304013/is-console-output-a-blocking-operation
