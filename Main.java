import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinTree<Game> tree = new BinTree<>();  // Binary search tree to store game inventory
        Scanner scanner = new Scanner(System.in);  // Scanner for user input

        // Prompt user to input the inventory file name
        System.out.print("Enter inventory file: ");
        String inventoryFile = scanner.nextLine();

        // Load inventory from file
        try (BufferedReader br = new BufferedReader(new FileReader(inventoryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse game title enclosed in quotes
                int firstQuote = line.indexOf('"');
                int secondQuote = line.indexOf('"', firstQuote + 1);
                String title = line.substring(firstQuote + 1, secondQuote);

                // Parse available and rented counts
                String[] parts = line.substring(secondQuote + 2).split(",");
                int available = Integer.parseInt(parts[0].trim());
                int rented = Integer.parseInt(parts[1].trim());

                // Insert game into the binary tree
                tree.insert(new Game(title, available, rented));
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

        // Prompt user to input the transaction file name
        System.out.print("Enter transaction file: ");
        String transactionFile = scanner.nextLine();

        // Process each transaction from the file
        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // Skip empty lines

                // Extract command and game details
                String[] tokens = line.split(" ", 2);
                String command = tokens[0].toLowerCase();  // Command: add, remove, rent, return
                String details = tokens[1];

                // Extract game title
                int firstQuote = details.indexOf('"');
                int secondQuote = details.indexOf('"', firstQuote + 1);
                String title = details.substring(firstQuote + 1, secondQuote);

                // Search for the game in the tree
                Game searchGame = new Game(title, 0, 0);
                Node<Game> node = tree.search(searchGame);

                // Execute the command
                if (command.equals("add")) {
                    int number = Integer.parseInt(details.substring(secondQuote + 2).trim());
                    if (node != null) {
                        // Increase available count
                        node.getPayload().setAvailable(node.getPayload().getAvailable() + number);
                    } else {
                        // If game doesn't exist, insert new game
                        tree.insert(new Game(title, number, 0));
                    }
                } else if (command.equals("remove")) {
                    int number = Integer.parseInt(details.substring(secondQuote + 2).trim());
                    if (node != null) {
                        Game g = node.getPayload();
                        // Decrease available count but don't go below 0
                        g.setAvailable(Math.max(0, g.getAvailable() - number));
                        // If both available and rented are 0, remove game from tree
                        if (g.getAvailable() == 0 && g.getRented() == 0) {
                            tree.delete(g);
                        }
                    }
                } else if (command.equals("rent")) {
                    if (node != null) {
                        Game g = node.getPayload();
                        // Rent game if available
                        if (g.getAvailable() > 0) {
                            g.setAvailable(g.getAvailable() - 1);
                            g.setRented(g.getRented() + 1);
                        }
                    }
                } else if (command.equals("return")) {
                    if (node != null) {
                        Game g = node.getPayload();
                        // Return game if rented
                        if (g.getRented() > 0) {
                            g.setAvailable(g.getAvailable() + 1);
                            g.setRented(g.getRented() - 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transaction file: " + e.getMessage());
        }

        // Display the final report in tabular format
        System.out.println("\nTitle                           Available  Rented");
        System.out.println("------------------------------------------------");
        tree.inOrderTraversal();  // Print all games in sorted order

        scanner.close();  // Close scanner to release system resource
    }
}


