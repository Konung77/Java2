package lesson5;

public class MyThreadClass {
    // Необходимо написать два метода, которые делают следующее:
    static void First () {
        // 1) Создают одномерный длинный массив
        final int size = 10000000;
        float[] arr = new float[size];
        // 2) Заполняют этот массив единицами;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        // 3) Засекают время выполнения
        long start = System.currentTimeMillis();
        // 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле
        computeArray(arr);
        // 5) Проверяется время окончания метода
        // 6) В консоль выводится время работы
        System.out.println(System.currentTimeMillis() - start);
    }

    // Отличие первого метода от второго:
    // Первый просто бежит по массиву и вычисляет значения.
    // Второй разбивает массив на два массива, в двух потоках высчитывает новые значения
    // и потом склеивает эти массивы обратно в один.
    static void Second () {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        new Thread(()->computeArray(a1)).start();
        new Thread(()->computeArray(a2)).start();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - start);
    }

    static void computeArray (float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    public static void main(String[] args) {
        First();
        Second();
    }
}
