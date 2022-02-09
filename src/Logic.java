import java.util.Map;
import java.util.TreeMap;
public class Logic {

    private static double income;
    private static Map < String, Double > food;
    private static Map < String, Double > clothes;
    private static Map < String, Double > entertainment;
    private static Map < String, Double > other;
    private static Map < String, Double > all;
    private static SortingMethod sortingMethod;

    public Logic() {
        food = new TreeMap < > ();
        clothes = new TreeMap < > ();
        entertainment = new TreeMap < > ();
        other = new TreeMap < > ();
        all = new TreeMap < > ();
        income = 0;
        sortingMethod = new SortingMethod();
    }

    public static void setIncome(double income) {
        Logic.income = income;
    }

    public static void addFood(String name, double priceOfItem) {
        food.put(name, priceOfItem);
        checkIncome(priceOfItem);
    }

    public static void addClothes(String name, double priceOfItem) {
        clothes.put(name, priceOfItem);
        checkIncome(priceOfItem);
    }

    public static void addEntertainment(String name, double priceOfItem) {
        entertainment.put(name, priceOfItem);
        checkIncome(priceOfItem);
    }

    public static void addOther(String name, double priceOfItem) {
        other.put(name, priceOfItem);
        checkIncome(priceOfItem);
    }

    public static double getIncome() {
        return income;
    }

    public static Map < String, Double > getFood() {
        return food;
    }

    public static Map < String, Double > getClothes() {
        return clothes;
    }

    public static Map < String, Double > getEntertainment() {
        return entertainment;
    }

    public static Map < String, Double > getOther() {
        return other;
    }

    public static Map < String, Double > getAll() {
        return all;
    }

    public static void addAll() {
        all.putAll(food);
        all.putAll(clothes);
        all.putAll(entertainment);
        all.putAll(other);
    }


    public static void loadFood(String name, double priceOfItem) {
        food.put(name, priceOfItem);
    }
    public static void loadClothes(String name, double priceOfItem) {
        clothes.put(name, priceOfItem);
    }
    public static void loadEntertainment(String name, double priceOfItem) {
        entertainment.put(name, priceOfItem);
    }
    public static void loadOther(String name, double priceOfItem) {
        other.put(name, priceOfItem);
    }

    private static void checkIncome(double priceOfItem) {
        if (income - priceOfItem < 0) {
            income = 0;
        } else {
            income -= priceOfItem;
        }
    }

    public static void getListOfItems(Map < String, Double > list) {
        list.forEach((key, value) -> System.out.printf("%s" + "$" + "%.2f" + "\n", key, value));
        double sum = list.values().stream().mapToDouble(d -> d).sum();
        System.out.printf("%s" + "%.2f", "Total sum: $", sum);
    }

    public static Map < String, Double > sumByCat() {
        double sumFood = getSumOfList(food);
        double sumClothes = getSumOfList(clothes);
        double sumEntertainment = getSumOfList(entertainment);
        double sumOther = getSumOfList(other);

        Map < String, Double > listByCat = new TreeMap < > ();

        listByCat.put("Food", sumFood);
        listByCat.put("Clothes", sumClothes);
        listByCat.put("Entertainment", sumEntertainment);
        listByCat.put("Other", sumOther);
        return listByCat;

    }

    public static double getSumOfList(Map < String, Double > list) {
        return list.values().stream().mapToDouble(d -> d).sum();
    }

    public static void getSorting(Sorting sortingMe, Map < String, Double > list) {
        sortingMethod.setSorting(sortingMe);
        sortingMethod.sort(list);
    }
}