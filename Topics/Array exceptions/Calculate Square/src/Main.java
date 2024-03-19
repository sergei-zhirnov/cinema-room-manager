
class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        if (array == null) {
            System.out.println("Exception!"); //NullPointerException
        } else if (index < 0 || index > array.length) {
            System.out.println("Exception!"); // ArrayIndexOutOfBoundsException
        } else {
            System.out.println(array[index] * array[index]);
        }

    }
}