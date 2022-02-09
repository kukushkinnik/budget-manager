import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class FileWriterHandler {

    public void write() {
        try (PrintWriter writer = new PrintWriter(UI.getFile())) {
            double inc = Logic.getIncome();
            writer.printf("Income " + "%.2f ", inc);
            writer.println();

            if (!Logic.getFood().isEmpty()) {
                saveInFile(Logic.getFood(), "Food", writer);
            }

            if (!Logic.getClothes().isEmpty()) {
                saveInFile(Logic.getClothes(), "Clothes", writer);
            }

            if (!Logic.getEntertainment().isEmpty()) {
                saveInFile(Logic.getEntertainment(), "Entertainment", writer);
            }

            if (!Logic.getOther().isEmpty()) {
                saveInFile(Logic.getOther(), "Other", writer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveInFile(Map < String, Double > list, String name, PrintWriter writer) {
        for (Map.Entry < String, Double > entry: list.entrySet()) {
            writer.printf("%s: " + "%s" + " " + "%.2f ", name, entry.getKey(), entry.getValue());
            writer.println();
        }
    }
}
