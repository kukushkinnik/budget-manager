import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = "purchases.txt";
        File file = new File(path);
        UI ui = new UI(scanner, file);
        ui.start();
    }
}
