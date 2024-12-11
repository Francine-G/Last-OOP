import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;

class Facilitator{
    private String facilitatorName;
    private int pin;
    private String[] location;
    private String[] disasterType;
    private int[] popSize;
    private double donatedCash;
    private int food;
    private int water;
    private int medicalSupplies;
    private int clothing;
    private String[] others;
    private static final String FILE_PATH = "C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DisasterDetails.txt";



    public Facilitator(String facilitatorName, int pin, String[] location, String[] disasterType, int[] popSize, double donatedCash){
        this.facilitatorName = facilitatorName;
        this.pin = pin;
        this.location = location;
        this.disasterType = disasterType;
        this.popSize = popSize;
        this.donatedCash = donatedCash;

    }

public void transactions() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("==================================================================================================================================================");
    System.out.println("                                                  OPERATIONS DASHBOARD                                             ");
    System.out.println("    |===== 1. See Donors & Volunteers =====|    |===== 2. View Donations =====|    |===== 3. Create Transaction  =====|    |===== 4. Cancel =====|");
    System.out.println("==================================================================================================================================================");

    System.out.print("Enter your choice (1-4): ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1: // See Donors & Volunteers
            System.out.println("\n|===== 1. View List of Donors =====|    |===== 2. View List of Volunteers =====|");
            System.out.print("Enter your choice (1 or 2): ");
            int subChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (subChoice) {
                case 1: // View List of Donors
                    System.out.println("\n|===== 1. In-Kind =====|    |===== 2. Cash =====|");
                    System.out.print("Enter your choice (1 or 2): ");
                    int donorChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (donorChoice == 1) {
                        System.out.println("\nList of In-Kind Donors:");
                        System.out.printf("%-5s | %-30s\n", "No.", "Donor Name");
                        System.out.println("-------------------------------------");
                        displayDonors("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt", "In-Kind Support");

                    } else if (donorChoice == 2) {
                        System.out.println("\nList of Cash Donors:");
                        System.out.printf("%-5s | %-30s\n", "No.", "Donor Name");
                        System.out.println("-------------------------------------");
                        displayDonors("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt", "Cash");
                    } else {
                        System.out.println("\nInvalid choice. Returning to main menu...");
                    }
                    break;

                case 2: // View List of Volunteers
                    System.out.println("\nList of Volunteers:");
                    displayVolunteers("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\VolunteerDatabase.txt");
                    break;

                default:
                    System.out.println("\nInvalid choice. Returning to main menu...");
                    break;
            }
            break;

        case 2: // View Donations
            System.out.println("\n|===== 1. Check Cash Balance =====|    |===== 2. Check Supplies Balance =====|");
            System.out.print("Enter your choice (1 or 2): ");
            int donationChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (donationChoice == 1) {
                System.out.printf("\nTotal Cash Balance: %.2f\n");
            } else if (donationChoice == 2) {
                System.out.println("\nSupplies Balance:");
                System.out.printf("%-20s | %-10s\n", "Item", "Quantity");
                System.out.println("-----------------------------------");
                
            } else {
                System.out.println("\nInvalid choice. Returning to main menu...");
            }
            break;

        case 3: //trasaction process
            processTransactions();
       
        case 4: // Cancel
            System.out.println("\nTransaction canceled. Returning to main menu...");
            break;

        default:
            System.out.println("\nInvalid choice. Please try again.");
            break;
    }
}

public void processTransactions() {
    String filePath = "C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt";
    Map<String, Integer> supplies = new LinkedHashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        String currentItem = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("- ")) { // Example: "- Food: 1000"
                String[] parts = line.split(":");
                currentItem = parts[0].replace("- ", "").trim(); // Extract item name
                int quantity = Integer.parseInt(parts[1].trim()); // Extract quantity
                supplies.put(currentItem, quantity);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading the file: " + e.getMessage());
        return;
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n===================================================================");
        System.out.println("                     RELIEF GOODS SUPPLIES                        ");
        System.out.println("===================================================================");
        System.out.println("Available Supplies:");
        int index = 1;
        for (String supply : supplies.keySet()) {
            System.out.println(index++ + ". " + supply + " - Remaining: " + supplies.get(supply));
        }
        System.out.println(index + ". Cancel");
        System.out.println("===================================================================");

        System.out.print("Enter the number of the supply you would like to withdraw: ");
        int supplyType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (supplyType == index) { // Cancel option
            System.out.println("Transaction canceled. Returning to the main menu...");
            break;
        }

        String chosenItem = null;
        if (supplyType > 0 && supplyType <= supplies.size()) {
            chosenItem = new ArrayList<>(supplies.keySet()).get(supplyType - 1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            continue;
        }

        System.out.print("Enter the quantity needed for " + chosenItem + ": ");
        int supplyQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int availableQuantity = supplies.get(chosenItem);
        if (supplyQuantity <= availableQuantity) {
            supplies.put(chosenItem, availableQuantity - supplyQuantity);
            System.out.println("You have withdrawn " + supplyQuantity + " from " + chosenItem + 
                               ". Remaining supplies: " + (availableQuantity - supplyQuantity));
        } else {
            System.out.println("Insufficient supplies. Only " + availableQuantity + " available.");
        }
    }

    // Step 3: Write updated supplies back to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (Map.Entry<String, Integer> entry : supplies.entrySet()) {
            writer.write("- " + entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        }
        System.out.println("Supplies updated successfully.");
    } catch (IOException e) {
        System.out.println("Error writing to the file: " + e.getMessage());
    }
}


    public String getFacilitatorName() {
        return facilitatorName;
    }

    public int getPin() {
        return pin;
    }

    public String[] getLocation() {
        return location;
    }

    public String[] getDisasterType() {
        return disasterType;
    }

    public int[] getPopSize() {
        return popSize;
    }
    public double getDonatedCash() {
        return donatedCash;
    }

    public void displayDisasterDetails(){

        File file = new File (FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            System.out.println("There are no Disasters Recorded at the moment.");
            return;
        }

        System.out.println("\n=====================================================================================");
        System.out.println("|         Location        |          Disaster            |       Population Size    |");
        System.out.println("=====================================================================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split("\\|"); // Assuming "|" is the delimiter
                System.out.printf("%-25s %-28s %-29s|%n", "|          " + details[0], "|          " + details[1], "  |          " + details[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading disaster details: " + e.getMessage());
        }

        System.out.println("+=====================================================================+");
        System.out.println("");
        
    }

    public void addDisasterDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the location: ");
        String newLocation = scanner.nextLine();

        System.out.print("Enter the disaster type: ");
        String newDisasterType = scanner.nextLine();

        System.out.print("Enter the population size: ");
        int newPopSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Save the new details to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DisasterDetails.txt", true))) {
            writer.write(newLocation + "|" + newDisasterType + "|" + newPopSize);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving disaster details: " + e.getMessage());
        }

        System.out.println("Disaster details added successfully.");
    }
    

    public void displayInventories() {
        String donorFilePath = "C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt";
    String disasterFilePath = "C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DisasterDetails.txt";
    Map<String, Integer> supplies = new LinkedHashMap<>();
    List<Integer> popSizes = new ArrayList<>();

    // Step 1: Read supplies from the donor file
    try (BufferedReader reader = new BufferedReader(new FileReader(donorFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("- ")) { // Example: "- Food: 1000"
                String[] parts = line.split(":");
                String item = parts[0].replace("- ", "").trim(); // Extract item name
                int quantity = Integer.parseInt(parts[1].trim()); // Extract quantity
                supplies.put(item, quantity);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading the donor file: " + e.getMessage());
        return;
    }

    // Step 2: Read population sizes from the disaster file
    try (BufferedReader reader = new BufferedReader(new FileReader(disasterFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|");
            if (details.length == 3) {
                int population = Integer.parseInt(details[2].trim());
                popSizes.add(population);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading the disaster file: " + e.getMessage());
        return;
    }

    // Check if supplies or population sizes are empty
    if (supplies.isEmpty()) {
        System.out.println("There are no relief goods supplies stored at the moment.");
        return;
    }
    if (popSizes.isEmpty()) {
        System.out.println("There are no recorded disasters with population sizes at the moment.");
        return;
    }

    // Population size ranges
    int[][] ranges = {
        {100, 1000}, {1001, 5000}, {5001, 10000}, {10001, 15000},
        {15001, 20000}, {20001, 25000}, {25001, 30000}
    };

    System.out.println("\n=======================================");
    System.out.println("|      RANGES OF POPULATION SIZE      |");
    System.out.println("=======================================");

    for (int[] range : ranges) {
        int min = range[0];
        int max = range[1];
        System.out.println(String.format("|             %5d - %5d           |", min, max));
    }

    System.out.println("=======================================");

    System.out.println("\n============================================================================================================================================================================");
    System.out.println("|                                                                      RELIEF GOODS AND SUPPLIES                                                                           |");
    System.out.println("============================================================================================================================================================================");
    System.out.println("|     No. of Affected Citizens  |       Foods       |      Water       |   Medical Supplies  |     Clothing     |            Others             |           Status         |");
    System.out.println("============================================================================================================================================================================");

    for (int pop : popSizes) {
        // Dynamically get supply quantities
        int food = supplies.getOrDefault("Food", 0);
        int water = supplies.getOrDefault("Water", 0);
        int medicalSupplies = supplies.getOrDefault("Medical Supplies", 0);
        int clothing = supplies.getOrDefault("Clothing", 0);
        int others = supplies.getOrDefault("Others", 0);
    
        String status = (food >= pop && water >= pop && medicalSupplies >= pop && clothing >= pop && others >= pop) ? "Sufficient" : "Insufficient";
    
            System.out.println(String.format("|             %5d             |        %5d      |      %5d       |        %5d        |       %5d      |     %21d     |      %15s     |",
                    pop, food, water, medicalSupplies, clothing, others, status));
        }
    }
    
     
    public void displayVolunteers(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\VolunteerDatabase.txt"))) {
            String line;
        
            System.out.println("===================================================================================");
            System.out.println("|                                 LIST OF VOLUNTEERS                              |");
            System.out.println("===================================================================================");
    
            while ((line = reader.readLine()) != null) {
                System.out.println(line); 
            }
    
            System.out.println("===================================================================================");
        } catch (IOException e) {
            System.out.println("Error reading the volunteer information file.");
            e.printStackTrace();
        }

    }

    public void displayDonors(String filePath, String type){    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean donorFound = false;
    
            System.out.println("==================================================================");
            System.out.println("                LIST OF " + (type.equals("Cash") ? "CASH" : "IN-KIND") + " DONORS");
            System.out.println("==================================================================");
    
            while ((line = reader.readLine()) != null) {
                if (line.contains("Donation Type: " + type)) {
                    donorFound = true;
    
                    // Print donor details
                    System.out.println(line); // Donation Type line
                    while ((line = reader.readLine()) != null && !line.startsWith("Donation Type:")) {
                        if (!line.trim().isEmpty()) { // Skip empty lines
                            System.out.println(line); // Print each subsequent line
                        }
                    }
    
                    System.out.println("------------------------------------------------------------------");
                }
            }
    
            if (!donorFound) {
                System.out.println("No " + type + " donors found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the donor information file.");
            e.printStackTrace();
        }
    }

    public void displayFaciSummary(){
       
    }
}

public class FacilitatorInfo{
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Facilitator facilitator = new Facilitator("Francine", 1234, args, args, null, 0);

     
            

                while (true) {
                    System.out.println("\n====================================================================================================");
                    System.out.println("                                       FACILITATOR DASHBOARD                                         ");
                    System.out.println("     |=====    1. View Operations   =====|            |====      2. View Inventories    ====|        ");
                    System.out.println("     |====  3. View Disaster Reports ====|            |====== 4. Add Disaster Details ======|        ");
                    System.out.println("                            |====            5. Exit          ====|                                  ");
                    System.out.println("=====================================================================================================");
                    System.out.println(" ");
                
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                
                    switch (choice) {
                        case 1:
                            facilitator.transactions();
                            break;
                        case 2:
                            facilitator.displayInventories();
                            break;
                        case 3:
                            facilitator.displayDisasterDetails();
                            break;
                        case 4:
                            facilitator.addDisasterDetails();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                
                    System.out.println("");
                    System.out.println("Would you like to do another transaction? (yes/no) ");
                    String response = scanner.nextLine().toLowerCase();
                    if (response.equals("no")) {
                        System.out.println(". . . You are now exiting the program. Thank you! . . . ");
                        System.exit(0);
                    } else if (!response.equals("yes")) {
                        System.out.println("Invalid input. Returning to the dashboard.");
                    }
        }
            
    }                
        
}


