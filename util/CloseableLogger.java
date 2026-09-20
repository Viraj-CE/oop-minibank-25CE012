package util;

public class CloseableLogger implements AutoCloseable {

    public CloseableLogger() {
        System.out.println("Logger opened");
    }

    public void log(String msg) {
        System.out.println(msg);
    }

    @Override
    public void close() {
        System.out.println("Logger closed");
    }
}
