import main.*;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        B3RTree b = null;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to the B3R Tree operations:tre");
        
        while (true) {
            System.out.print("Enter the name of the operations file (or press Enter to quit): ");
            String fileName = consoleReader.readLine().trim();

            if (fileName.isEmpty()) {
                System.out.println("Exiting program...");
                break;
            }

            try (BufferedReader fileReader = new BufferedReader(new FileReader("src/"+fileName))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    String[] data = line.split(" ");

                    switch (data[0]) {
                        case "c":
                            b = new B3RTree();
                            break;
                        case "i":
                            if (b != null) {
                                int value = Integer.parseInt(data[1]);
                                b.insert(value);
                            }
                            break;
                        case "mn":
                            if (b != null) {
                                System.out.println("Minimum value: " + b.minValue());
                            }
                            break;
                        case "mx":
                            if (b != null) {
                                System.out.println("Maximum value: " + b.maxValue());
                            }
                            break;
                        case "s":
                            if (b != null) {
                                System.out.println("Tree:");
                                System.out.println(b.toString());
                            }
                            break;
                        case "nn":
                            if (b != null) {
                                System.out.println("Number of nodes: " + b.getNumNodes());
                            }
                            break;
                        case "nk":
                            if (b != null) {
                                System.out.println("Number of keys: " + b.getTotalKeys());
                            }
                            break;
                        case "b":
                            if (b != null) {
                                int search = Integer.parseInt(data[1]);
                                System.out.println("Value " + search + " in tree? " + b.searchValue(search));
                            }
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + fileName);
                e.printStackTrace();
            }
        }

        consoleReader.close();
    }
}
