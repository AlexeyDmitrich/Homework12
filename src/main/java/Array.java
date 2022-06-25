public class Array {

    /**
     * размер массива увеличен для наглядности
     */
    static final int SIZE = 50000000;   // при значении 28600 время работы методов будет
                                     // примерно равное, при уменьшении этого значения
                                     // прямой однопоточный метод начинает выигрывать
    static final int HALF = SIZE / 2 ;
    // формула:
    // arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

    static float[] array = new float[SIZE];
    static float[] arrayHalf1;
    static float[] arrayHalf2;

    static float[] arrayPart1;
    static float[] arrayPart2;
    static float[] arrayPart3;
    static float[] arrayPart4;


    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            array[i]=1;
        }
        System.out.printf("массив готов, и содержит %d элементов \n", SIZE);
        System.out.println("Содержимое последнего элемента: " + array[array.length-1]);
        System.out.print("К каждому элементу будет применена формула: \n" +
                "(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2)\n");
        System.out.println();
        System.out.println("Начало вычислений последовательным методом");
        long a = System.currentTimeMillis();   // засекаем время для однопоточного метода
        array = CalcIt.calc(array);    // прогоняем массив по методу
        System.currentTimeMillis();    // отсекаем время
        float onceTime = System.currentTimeMillis()-a;
        System.out.println("Последовательный метод отработал за " + onceTime + " мс");

        Thread thread1 = new Thread(new MyThread1());
        Thread thread2 = new Thread(new MyThread2());
        // методы потоков не универсальны, у каждого своя узкая задача:
        // один класс - один поток - один метод.

        System.out.println();
        System.out.println("начало работы двупоточного метода");
        long b = System.currentTimeMillis();
        arrayHalf1 = new float[HALF+1];
        System.arraycopy(array, 0, arrayHalf1, 0, HALF);
        arrayHalf2 = new float[HALF+1];
        System.arraycopy(array, HALF, arrayHalf2, 0, HALF);


        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e){
            e.getStackTrace();
        }

        System.currentTimeMillis();   // отсекаем время до склейки
        System.out.println("двупоточный метод без учета склейки отработал за " + (System.currentTimeMillis()-b) + " мс");

        System.arraycopy(arrayHalf1, 0, array, 0, arrayHalf1.length-1);
        System.arraycopy(arrayHalf2, 0, array, HALF, arrayHalf2.length-1);

        System.currentTimeMillis();   // и после склейки
        float twiceTime = System.currentTimeMillis()-b;
        System.out.println("двупоточный метод отработал за " + twiceTime + " мс");

        // пробуем разбить на 4 потока
        Thread thread3 = new Thread(new MyThread3());
        //new Thread(thread1, "Первый поток");
        Thread thread4 = new Thread(new MyThread4());
        //new Thread(thread2, "Второй поток");
        Thread thread5 = new Thread(new MyThread5());
        //new Thread(thread1, "Первый поток");
        Thread thread6 = new Thread(new MyThread6());
        //new Thread(thread2, "Второй поток");

        System.out.println();
        System.out.println("начало работы многопоточного метода");
        long f = System.currentTimeMillis();
        arrayPart1 = new float[HALF/2 +1];
        System.arraycopy(array, 0, arrayPart1, 0, HALF/2);
        arrayPart2 = new float[HALF/2 +1];
        System.arraycopy(array, HALF/2, arrayPart2, 0, HALF/2);
        arrayPart3 = new float[HALF/2 +1];
        System.arraycopy(array, HALF, arrayPart3, 0, HALF/2);
        arrayPart4 = new float[HALF/2 +1];
        System.arraycopy(array, (HALF+HALF/2), arrayPart4, 0, HALF/2);


        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        try {
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException e){
            e.getStackTrace();
        }

        System.currentTimeMillis();
        float copyTime = System.currentTimeMillis()-f;
        System.out.println("многопоточный метод без учёта склейки отработал за " + (System.currentTimeMillis()-f) + " мс");

        System.arraycopy(arrayPart1, 0, array, 0, arrayPart1.length-1);
        System.arraycopy(arrayPart2, 0, array, HALF/2, arrayPart2.length-1);
        System.arraycopy(arrayPart3, 0, array, HALF, arrayPart3.length-1);
        System.arraycopy(arrayPart4, 0, array, (HALF+HALF/2), arrayPart4.length-1);

        System.currentTimeMillis();
        float foursTime = System.currentTimeMillis()-f;
        copyTime = foursTime - copyTime;
        System.out.println("многопоточный метод отработал за " + foursTime + " мс");

        System.out.println();    // выводим статистику
        System.out.println("Вывод:");
        System.out.printf("Вычисления двупоточным методом занимает в %.2f раза меньше времени, \n" +
                "чем при последовательном вычислении в одном потоке. \nМетод с четырьмя потоками тратит" +
                " в %.2f раза меньше врмени, чем последовательный. " +
                "\nВременем склейки (%.1f мс) можно пренебречь.", onceTime/twiceTime, onceTime/foursTime, copyTime);
    }

}
