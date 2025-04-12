package learn;

public class Transaction {

    // All synchronized methods will be locked when the thread is running.
    // In other words, printName method locks printHello method,
    // but does not lock the printHelloWorld method.
    public void execute() {

        class Info {

            public synchronized void printName(String name) {
                try {
                    Thread.sleep(8000);
                    System.out.println(name);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }

            public synchronized void printHello(String name) {
                System.out.println("Hello " + name);
            }

            public void printHelloWorld() {
                System.out.println("Hello, World!");
            }
        }

        Info info = new Info();

        Thread tx1 = new Thread(() -> {
            info.printName("tx1");
        });
        Thread tx2 = new Thread(() -> {
            info.printHelloWorld();
            info.printHello("tx2");
        });
        Thread tx3 = new Thread(() -> {
            info.printHelloWorld();
            info.printHello("tx3");
        });

        tx1.start();
        tx2.start();
        tx3.start();
    }
}
