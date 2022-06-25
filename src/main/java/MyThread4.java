public class MyThread4 implements Runnable {

    @Override
    public void run() {
        Array.arrayPart2 = CalcIt.calc(Array.arrayPart2);
    }
}
