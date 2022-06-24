public class MyThread1 implements Runnable {

    @Override
    public void run() {
        CalcIt.calc(Array.arrayHalf1);
    }
}
