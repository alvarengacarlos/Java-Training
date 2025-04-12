package learn;

public class ProducerConsumer {

    private final String phrase = "Hello\n";

    class Paper {

        private char character;
        private boolean isWriting = true;

        public synchronized void write(char c) {
            log("Writing:", c);
            character = c;
            isWriting = false;
            notifyAll();

            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        private void log(String msg, char character) {
            if (character != '\n') {
                System.out.println(msg + " " + character);
            }
        }

        public synchronized char read() {
            if (isWriting) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            log("Reading:", character);
            isWriting = true;
            notifyAll();
            return character;
        }
    }

    void execute() {
        Paper paper = new Paper();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < phrase.length(); i++) {
                paper.write(phrase.charAt(i));
            }
        });

        Thread consumer = new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            char c = '\000';
            while (c != '\n') {
                c = paper.read();
                sb.append(c);
            }
            System.out.println("Complete phrase: " + sb.toString());
        });

        producer.start();
        consumer.start();
    }
}
