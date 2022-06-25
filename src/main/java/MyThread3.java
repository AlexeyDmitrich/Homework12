public class MyThread3 implements Runnable {

    @Override
    public void run() {
        Array.arrayPart1 = CalcIt.calc(Array.arrayPart1);
    }
}
