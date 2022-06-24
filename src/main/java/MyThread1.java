public class MyThread1 implements Runnable {

    @Override
    public void run() {
        Array.arrayHalf1 = CalcIt.calc(Array.arrayHalf1);
    }
}
