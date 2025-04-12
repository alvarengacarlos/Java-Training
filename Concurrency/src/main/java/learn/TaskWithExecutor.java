package learn;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskWithExecutor {

    public void execute() {
        Callable<String> task1 = () -> {
            try {
                System.out.println("Task 1 - ...");
                Thread.sleep(2000);
                return "Task 1 - OK";
            } catch (InterruptedException e) {
                return e.getMessage();
            }
        };
        Callable<String> task2 = () -> {
            try {
                System.out.println("Task 2 - ...");
                Thread.sleep(4000);
                return "Task 2 - OK";
            } catch (InterruptedException e) {
                return e.getMessage();
            }
        };
        Callable<String> task3 = () -> {
            try {
                System.out.println("Task 3 - ...");
                Thread.sleep(8000);
                return "Task 3 - OK";
            } catch (InterruptedException e) {
                return e.getMessage();
            }
        };
        Callable<String> task4 = () -> {
            try {
                System.out.println("Task 4 - ...");
                Thread.sleep(1000);
                return "Task 4 - OK";
            } catch (InterruptedException e) {
                return e.getMessage();
            }
        };

        /*
         * With execution order
         */
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            Future<String> future1 = executorService.submit(task1);
            Future<String> future2 = executorService.submit(task2);
            Future<String> future3 = executorService.submit(task3);
            Future<String> future4 = executorService.submit(task4);
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
            System.out.println(future4.get());
        } catch (InterruptedException | ExecutionException exception) {
            System.err.println(exception.getMessage());
        }

        /*
         * Without execution order
         */
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            List<Future<String>> futureList = executorService.invokeAll(Set.of(task1, task2, task3, task4), 20L, TimeUnit.SECONDS);
            futureList.forEach((future) -> {
                try {
                    System.out.println(future.get());

                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
            });
        } catch (InterruptedException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
