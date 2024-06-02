package fr.g4zoo.fantastizoo.models;

public class Watch implements Runnable {
    private int seconds;

    /**
     * Instantiates a new Watch.
     */
    public Watch() {
        this.seconds = 0;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
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
