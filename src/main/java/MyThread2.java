public class MyThread2 implements Runnable{

    @Override
    public void run() {
        CalcIt.calc(Array.array);
    }
}
