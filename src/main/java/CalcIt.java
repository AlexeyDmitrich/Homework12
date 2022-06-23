public class CalcIt {
    public static float[] calc (float[] array){
        float[] resultArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            for (int j = 0; j < resultArray.length; j++) {
                resultArray[j] = resultArray[i];
            }
        }
        return resultArray;
    }
}
