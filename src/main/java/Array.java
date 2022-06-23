public class Array {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2 ;
    // формула:
    // arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

    static float[] array = new float[SIZE];

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            array[i]=1;
        }
        long a = System.currentTimeMillis();
        System.out.println("Начало вычислений прямым брутфорсом");
        array = CalcIt.calc(array);
        System.currentTimeMillis();
        System.out.println("Метод брутфорс отработал за" + (System.currentTimeMillis()-a));

        MyThread1 thread1 = new MyThread1();
        new Thread(thread1, "Первый поток");
        MyThread2 thread2 = new MyThread2();
        new Thread(thread2, "Второй поток");

        System.out.println("начало работы многопоточного метода");
        long b = System.currentTimeMillis();
        float[] arrayHalf1 = new float[HALF];
                System.arraycopy(array, 0, arrayHalf1, 0, HALF);
        float[] arrayHalf2 = new float[HALF];
                System.arraycopy(array, HALF, arrayHalf2, HALF, array.length);

        try {
            thread1.run();
        } catch (InterruptedException e){
            e.getStackTrace();
        }
        array =
    }

}
