import java.util.Map;

public class SortingMethod {
    private Sorting sorting;

    public void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }

    public void sort(Map<String, Double> list) {
        this.sorting.sort(list);
    }
}
