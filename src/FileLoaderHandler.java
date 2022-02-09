import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoaderHandler {

    public void load() {
        try (Scanner reader = new Scanner(UI.getFile())) {

            while (reader.hasNext()) {
                String[] row = reader.nextLine().split("\\s");

                if (row[0].equals("Income")) {
                    Logic.setIncome(Double.parseDouble(row[1]));
                }

                if (row[0].equals("Food:")) {
                    String key = getName(row);
                    Logic.loadFood(key, Double.parseDouble(row[row.length - 1]));
                }

                if (row[0].equals("Clothes:")) {
                    String key = getName(row);
                    Logic.loadClothes(key, Double.parseDouble(row[row.length - 1]));
                }

                if (row[0].equals("Entertainment:")) {
                    String key = getName(row);
                    Logic.loadEntertainment(key, Double.parseDouble(row[row.length - 1]));
                }

                if (row[0].equals("Other:")) {
                    String key = getName(row);
                    Logic.loadOther(key, Double.parseDouble(row[row.length - 1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName(String[] arr) {
        String key = "";
        for (int i = 1; i < arr.length - 1; i++) {
            key += arr[i] + " ";
        }
        return key;
    }
}
