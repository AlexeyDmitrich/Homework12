public class CalcIt {
    public static float[] calc (float[] array){
        float[] calcArr = array;
        for (int i = 0; i < array.length; i++) {
            //array[i] = (float)(array[i] + 1);
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                if (i%100000 == 0) {
                    System.out.println("Пройдено " + i + " итераций");
                }

                if (i == (array.length/2)){
                    System.out.println("пройдена половина");
                }
            }
        calcArr = array;
return calcArr;
        }

    }
