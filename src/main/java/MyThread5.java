public class MyThread5 implements Runnable {

    @Override
    public void run() {
        Array.arrayPart3 = CalcIt.calc(Array.arrayPart3);
    }
}
