import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortAll implements  Sorting{
    @Override
    public void sort(Map<String, Double> list) {
        Map < String, Double >  reverseSortedMap = new LinkedHashMap<>();

        list.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        reverseSortedMap.forEach((key, value) -> System.out.printf("%s" + "$" + "%.2f" + "\n", key, value));
    }
}