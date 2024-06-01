package fr.g4zoo.fantastizoo.models;

public class Watch implements Runnable {
    private int seconds;

    public Watch() {
        this.seconds = 0;
    }

    public String getTime() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                seconds++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
