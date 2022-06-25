public class MyThread6 implements Runnable {

    @Override
    public void run() {
        Array.arrayPart4 = CalcIt.calc(Array.arrayPart4);
    }
}

