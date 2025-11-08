
//✅ Example 1: Daemon Thread Finishes Before Main Thread
public class DaemonEarlyFinishExample {
    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            System.out.println("Daemon thread started.");
            try {
                Thread.sleep(1000); // finishes quickly
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread finished before main.");
        });

        daemon.setDaemon(true); // set before start
        daemon.start();

        // Main thread doing more work
        System.out.println("Main thread doing work...");
        try {
            Thread.sleep(3000); // main thread is longer
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished.");
    }
}
/*
 * output:
 * --------------------------------------
 * Main thread doing work...
 * Daemon thread started.
 * Daemon thread finished before main.
 * Main thread finished.
 * --------------------------------------------------------------------
 * Note:
 * -Daemon thread finishes before main thread naturally, but JVM does not kill
 * it early beacause main (user) thread is still running
 * -Daemon thread is killed when main finishes
 * 
 */