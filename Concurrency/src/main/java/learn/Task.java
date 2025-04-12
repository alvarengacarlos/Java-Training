package learn;

public class Task {

    public void execute() {
        Thread task1 = new Thread(() -> {
            try {
                System.out.println("Task 1 - ...");
                Thread.sleep(4000);
                System.out.println("Task 1 - OK");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        Thread task2 = new Thread(() -> {
            try {
                System.out.println("Task 2 - ...");
                Thread.sleep(4000);
                System.out.println("Task 2 - OK");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        Thread task3 = new Thread(() -> {
            try {
                System.out.println("Task 3 - ...");
                Thread.sleep(4000);
                System.out.println("Task 3 - OK");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        Thread task4 = new Thread(() -> {
            try {
                task1.join();
                task2.join();
                task3.join();
                System.out.println("Task 4 - ...");
                Thread.sleep(1000);
                System.out.println("Task 4 - OK");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        task1.start();
        task2.start();
        task3.start();
        task4.start();
    }
}
