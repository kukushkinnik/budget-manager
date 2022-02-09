import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    private static File file;
    private final FileWriterHandler fileWriterHandler;
    private final FileLoaderHandler fileLoaderHandler;
    private final Logic logic;
    SortingMethod sortingMethod;

    public UI(Scanner scanner, File file) {
        this.scanner = scanner;
        UI.file = file;
        this.logic = new Logic();
        this.fileWriterHandler = new FileWriterHandler();
        this.fileLoaderHandler = new FileLoaderHandler();
        this.sortingMethod = new SortingMethod();
    }

    public void start() {
        while (true) {
            printMenu();
            String commands = scanner.nextLine();

            if (commands.equals("0")) {
                System.out.println();
                System.out.println("Bye!");
                break;
            }

            if (commands.equals("1")) {
                System.out.println();
                System.out.println("Enter income:");
                double input = Double.parseDouble(scanner.nextLine());
                Logic.setIncome(input);
                System.out.println("Income was added!");
                System.out.println();
            }

            if (commands.equals("2")) {
                while (true) {
                    typeOfPurchaseToAdd();
                    String typeOfP = this.scanner.nextLine();
                    if ("5".equals(typeOfP)) {
                        break;
                    }

                    if ("1".equals(typeOfP)) {
                        System.out.println();
                        String name = this.addingPurchaseName();
                        double itemPrice = this.addingPurchasePrice();
                        Logic.addFood(name, itemPrice);
                        System.out.println("Purchase was added!");
                        System.out.println();
                    }

                    if ("2".equals(typeOfP)) {
                        System.out.println();
                        String name = this.addingPurchaseName();
                        double priceOfItem = this.addingPurchasePrice();
                        Logic.addClothes(name, priceOfItem);
                        System.out.println("Purchase was added!");
                        System.out.println();
                    }

                    if ("3".equals(typeOfP)) {
                        System.out.println();
                        String name = this.addingPurchaseName();
                        double priceOfItem = this.addingPurchasePrice();
                        Logic.addEntertainment(name, priceOfItem);
                        System.out.println("Purchase was added!");
                        System.out.println();
                    }

                    if ("4".equals(typeOfP)) {
                        System.out.println();
                        String name = this.addingPurchaseName();
                        double priceOfItem = this.addingPurchasePrice();
                        Logic.addOther(name, priceOfItem);
                        System.out.println("Purchase was added!");
                        System.out.println();
                    }
                }

                System.out.println();
            }
            Logic.addAll();

            if (commands.equals("3")) {

                if (Logic.getFood().isEmpty() && Logic.getClothes().isEmpty() && Logic.getEntertainment().isEmpty() && Logic.getOther().isEmpty() && Logic.getAll().isEmpty()) {
                    System.out.println("The purchase list is empty!");
                    System.out.println();
                    continue;
                }

                while (true) {
                    typeOfPurchaseToDisplay();
                    String typeOfP = this.scanner.nextLine();

                    if ("6".equals(typeOfP)) {
                        break;
                    }

                    if ("1".equals(typeOfP)) {
                        if (!Logic.getFood().isEmpty()) {
                            System.out.println();
                            System.out.println("Food:");
                            Logic.getListOfItems(Logic.getFood());
                            System.out.println();
                        } else {
                            listIsEmptyByCategory("Food");
                        }
                    }

                    if ("2".equals(typeOfP)) {
                        if (!Logic.getClothes().isEmpty()) {
                            System.out.println();
                            Logic.getListOfItems(Logic.getClothes());
                            System.out.println();
                        } else {
                            listIsEmptyByCategory("Clothes");
                        }
                    }

                    if ("3".equals(typeOfP)) {
                        if (!Logic.getEntertainment().isEmpty()) {
                            System.out.println();
                            Logic.getListOfItems(Logic.getEntertainment());
                            System.out.println();
                        } else {
                            listIsEmptyByCategory("Entertainment");
                        }
                    }

                    if ("4".equals(typeOfP)) {
                        if (!Logic.getOther().isEmpty()) {
                            System.out.println();
                            System.out.println("Other");
                            Logic.getListOfItems(Logic.getOther());
                            System.out.println();
                        } else {
                            listIsEmptyByCategory("Other");
                        }
                    }

                    if ("5".equals(typeOfP)) {
                        if (!Logic.getAll().isEmpty()) {
                            System.out.println();
                            System.out.println("All");
                            Logic.getListOfItems(Logic.getAll());
                            System.out.println();
                        } else {
                            listIsEmptyByCategory("All");
                        }
                    }
                }
                System.out.println();
            }

            if (commands.equals("4")) {
                System.out.println();
                System.out.println("Balance: $" + Logic.getIncome());
                System.out.println();
            }

            //serialisation
            if (commands.equals("5")) {
                System.out.println();
                this.fileWriterHandler.write();
                System.out.println("Purchases were saved!");
                System.out.println();

            }

            if (commands.equals("6")) {
                System.out.println();
                this.fileLoaderHandler.load();
                System.out.println("Purchases were loaded!");
                System.out.println();
            }

            if (commands.equals("7")) {
                System.out.println();
                while (true) {
                    menuForSorting();
                    String typeOfS = this.scanner.nextLine();

                    if (typeOfS.equals("4")) {
                        break;
                    }

                    if (typeOfS.equals("1")) {
                        if (!Logic.getAll().isEmpty()) {
                            System.out.println();
                            Logic.getSorting(new SortAll(), Logic.getAll());
                            System.out.println();
                        } else {
                            listIsEmpty();
                        }
                    }

                    if (typeOfS.equals("2")) {
                        System.out.println();
                        System.out.println("Types:");
                        Map < String, Double > categoriesWithSum = Logic.sumByCat();
                        Logic.getSorting(new SortByType(), categoriesWithSum);
                        double sum = Logic.getSumOfList(categoriesWithSum);
                        System.out.println("Total sum: " + sum);
                        System.out.println();
                    }

                    if (typeOfS.equals("3")) {
                        menuForSortingByType();

                        String sortByCategory = scanner.nextLine();
                        if (sortByCategory.equals("1")) {
                            if (!Logic.getFood().isEmpty()) {
                                System.out.println();
                                Logic.getSorting(new SortCertainType(), Logic.getFood());
                                System.out.println();
                            } else {
                                listIsEmpty();
                            }
                        }

                        if (sortByCategory.equals("2")) {
                            if (!Logic.getClothes().isEmpty()) {
                                System.out.println();
                                Logic.getSorting(new SortCertainType(), Logic.getClothes());
                                System.out.println();
                            } else {
                                listIsEmpty();
                            }
                        }

                        if (sortByCategory.equals("3")) {
                            if (!Logic.getEntertainment().isEmpty()) {
                                System.out.println();
                                Logic.getSorting(new SortCertainType(), Logic.getEntertainment());
                                System.out.println();
                            } else {
                                listIsEmpty();
                            }
                        }

                        if (sortByCategory.equals("4")) {
                            if (!Logic.getOther().isEmpty()) {
                                System.out.println();
                                Logic.getSorting(new SortCertainType(), Logic.getOther());
                                System.out.println();
                            } else {
                                listIsEmpty();
                            }
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    private String addingPurchaseName() {
        System.out.println("Enter purchase name:");
        return this.scanner.nextLine();
    }

    private double addingPurchasePrice() {
        System.out.println("Enter its price:");
        String price = this.scanner.nextLine();
        return Double.parseDouble(price);
    }

    public static File getFile() {
        return file;
    }

    private void printMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    private void typeOfPurchaseToAdd() {
        System.out.println();
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    private void typeOfPurchaseToDisplay() {
        System.out.println();
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    private void menuForSorting() {
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
    }

    public void menuForSortingByType() {
        System.out.println();
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
    }

    public void listIsEmpty() {
        System.out.println();
        System.out.println("The purchase list is empty!");
        System.out.println();
    }

    private void listIsEmptyByCategory(String name) {
        System.out.println();
        System.out.printf("%s:", name);
        System.out.println("The purchase list is empty!");
        System.out.println();
    }
}