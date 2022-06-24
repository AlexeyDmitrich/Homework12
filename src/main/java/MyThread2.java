public class MyThread2 implements Runnable{

    @Override
    public void run() {
        Array.arrayHalf2 = CalcIt.calc(Array.arrayHalf2);
    }
}
