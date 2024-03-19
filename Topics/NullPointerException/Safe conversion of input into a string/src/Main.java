import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String result = safeToString(input);
        System.out.println(result);

        scanner.close();
    }

    public static String safeToString(Object obj) {
        return obj != null ? obj.toString() : "null";
    }
}