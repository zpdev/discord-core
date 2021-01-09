package com.zp4rker.discore.bootstrap;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zp4rker
 */
public class DownloadCounter extends Thread {

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private final int size;
    private final Runnable onComplete;
    private int count = 0;
    private String string;

    public DownloadCounter(int size, Runnable onComplete) {
        this.size = size;
        this.onComplete = onComplete;
        System.out.print("Loading dependencies... ");
        string = count + "/" + size;
        System.out.print(string);
    }

    public void increment() {
        count++;

        String s = count + "/" + size;
        if (count < size) {
            queue.offer(s);
        } else {
            queue.offer(s);
            queue.offer("end");
        }
    }

    @Override
    public void run() {
        try {
            String s;
            while (!(s = queue.take()).equals("end")) {
                System.out.print(new String(new char[string.length()]).replace("\0", "\b"));
                System.out.print(s);
                string = s;
            }

            System.out.println();
            System.out.println("Successfully loaded dependencies.");

            onComplete.run();
        } catch (InterruptedException ignored) {
        }
    }
}
