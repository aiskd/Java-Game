public class Time {

    private static int FRAME_RATE = 60;
    private static int MILLI_TO_SECOND = 1000;
    private boolean start;
    private int timer;
    private int counter;
    private boolean timerStart = true;

    public Time(int countDown) {
        counter = 0;
        start = false;
        timer = countDown;
    }

    public void startCD() {
        start = true;
    }

    /**
     * Starting timer count down
     * int max: how long the timer ends
     */
    public void timer() {
//        System.out.println(counter);
        if (counter < timer / MILLI_TO_SECOND * FRAME_RATE) {
            counter++;
        } else {
            start = false;
            counter = 0;
        }
    }

    public boolean isStart() {
        return start;
    }
}
