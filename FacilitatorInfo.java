import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
                            displayDonors("C:\Users\Francine\OneDrive\Desktop\Pleaseeeee\Last-OOP\DonorInfo.txt", "In-Kind Support");
    
                        } else if (donorChoice == 2) {
                            System.out.println("\nList of Cash Donors:");
                            System.out.printf("%-5s | %-30s\n", "No.", "Donor Name");
                            System.out.println("-------------------------------------");
                            displayDonors("C:\Users\Francine\OneDrive\Desktop\Pleaseeeee\Last-OOP\DonorInfo.txt", "Cash");
                        } else {
                            System.out.println("\nInvalid choice. Returning to main menu...");
                        }
                        break;
    
                    case 2: // View List of Volunteers
                        System.out.println("\nList of Volunteers:");
                        System.out.printf("%-5s | %-20s | %-20s | %-20s\n", "No.", "Name", "Contact Info", "Preferred Location");
                        System.out.println("-------------------------------------------------------------------------------");
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
            
       
        case 4: // Cancel
            System.out.println("\nTransaction canceled. Returning to main menu...");
            break;

        default:
            System.out.println("\nInvalid choice. Please try again.");
            break;
    }
}

    public void processTransactions(int FoodQtyd, int WaterQty, int MedicalSuppliesQty, int ClothingQty, int OthersQty){
    Scanner scanner = new Scanner (System.in);
        while (true) {
            System.out.println(" ");
        System.out.println("\n======================================================================================================================================================");
        System.out.println("                                                       RELIEF GOODS SUPPLIES                                ");
        System.out.println("========================================================================================================================================================");
        System.out.println("                   1.Foods             2.Water             3.Medical Supplies             4.Clothing               5.Others               6.Cancel      ");
        System.out.println("========================================================================================================================================================");
        System.out.println(" ");

        System.out.println("Enter the Supply you would like to withdraw: ");
        int supplyType = scanner.nextInt();
    
        System.out.println("Enter the quantity needed for the supply: ");
        int supplyQuantity = scanner.nextInt();

        switch (supplyType){
            case 1: //foods
                if (supplyQuantity <= FoodQtyd){
                    FoodQtyd -= supplyQuantity; 
                    System.out.println("You have withdrawn " + supplyQuantity + " from food, " + "remaining supplies: " + FoodQtyd );
                }else{
                        System.out.println("Insufficient Supplies");
                    }
                    break;
            case 2: //water
                if (supplyQuantity <= WaterQty){
                    WaterQty -= supplyQuantity;
                    System.out.println("You have withdrawn " + supplyQuantity + " from water, " + "remaining supplies: " + WaterQty);
                }else{
                        System.out.println("Insufficient Supplies");
                    }
                    break;

            case 3: //medical supplies
                if (supplyQuantity <= MedicalSuppliesQty){
                    MedicalSuppliesQty -= supplyQuantity; 
                    System.out.println("You have withdrawn " + supplyQuantity + " from medical supplies, " + "remaining supplies: " + MedicalSuppliesQty);
                }else{
                    System.out.println("Insufficient Supplies");
                    }
                    break;

            case 4: //clothing
                    if (supplyQuantity <= ClothingQty){
                    ClothingQty -= supplyQuantity; 
                    System.out.println("You have withdrawn " + supplyQuantity + " from clothing, " + " remaining supplies: " + ClothingQty);
                }else{
                    System.out.println("Insufficient Supplies");
                    }
                    break;

            case 5: //others
                if (supplyQuantity <= OthersQty){
                    OthersQty -= supplyQuantity; 
                    System.out.println("You have withdrawn " + supplyQuantity +  " from others, " + "remaining supplies: " + OthersQty);
                }else{
                    System.out.println("Insufficient Supplies");
                }
                break;
            }
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

        if (location == null || disasterType == null || popSize == null ||
            location.length == 0 || disasterType.length == 0 || popSize.length == 0) {
            System.out.println("There are no Disasters Recorded at the moment.");
            return;
        }
        
        System.out.println("\n=====================================================================================");
        System.out.println("|         Location        |          Disaster            |       Population Size    | ");
        System.out.println("=====================================================================================");

        for (int i = 0; i < location.length && i < disasterType.length && i < popSize.length; i++) {
            System.out.printf("%-25s %-28s %-29s|%n", "|          " + location[i], "|          " + disasterType[i], "  |          " + popSize[i]);

        }

        System.out.println("=====================================================================================");
        System.out.println("");
        
    }

    public void addDisasterDetails() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter the location: ");
        String newLocation = scanner.nextLine();
    
        System.out.println("Enter the disaster type: ");
        String newDisasterType = scanner.nextLine();
    
        System.out.print("Enter the population size: ");
        int newPopSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        // Dynamically extend arrays
        location = addToArray(location, newLocation);
        disasterType = addToArray(disasterType, newDisasterType);
        popSize = addToIntArray(popSize, newPopSize);
    
        System.out.println("Disaster details added successfully.");
    }
    
    // Helper method to add a string to a string array
    private String[] addToArray(String[] array, String newElement) {
        if (array == null) {
            return new String[]{newElement};
        }
        String[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = newElement;
        return newArray;
    }
    
    // Helper method to add an integer to an int array
    private int[] addToIntArray(int[] array, int newElement) {
        if (array == null) {
            return new int[]{newElement};
        }
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = newElement;
        return newArray;
    }
    

    public void displayInventories(){

        if (food == 0 || water == 0 || medicalSupplies == 0 || clothing == 0) {
        System.out.println("There are no relief goods supplies stored at the moment.");
        return;
    }

    int[][] ranges = {
        {100, 1000},{1001, 5000},{5001, 10000},{10001, 15000},{15001, 20000},{20001, 25000},{25001, 30000},{30001, 35000},{35001, 40000},{40001, 45000},{45001, 50000},
        {50001, 55000},{55001, 60000},{60001, 65000},{65001, 70000},{70001, 75000},{75001, 80000},{80001, 85000},{85001, 90000},{90001, 95000},{95001, 100000}
    };

    System.out.println("\n=======================================");
    System.out.println("|      RANGES OF POPULATION SIZE      |");
    System.out.println("=======================================");

    for (int i = 0; i < ranges.length; i++) { 
        int min = ranges[i][0];
        int max = ranges[i][1];
        System.out.println(String.format("|             %3d - %3d               |", min, max));
    }
    
    
    System.out.println("=======================================");

    System.out.println(" ");
    System.out.println("\n============================================================================================================================================================================");
    System.out.println("|                                                                      RELIEF GOODS AND SUPPLIES                                                                           |");
    System.out.println("============================================================================================================================================================================");
    System.out.println("|     No. of Affected Citizens  |       Foods       |      Water       |   Medical Supplies  |     Clothing     |            Others             |           Status         |");
    System.out.println("============================================================================================================================================================================");

    int othersIndex = 0;

    for (int i = 0; i < popSize.length; i++) { 
        int pop = popSize[i]; 
        
        int min = ranges[i][0];
        int max = ranges[i][1];

        String status = (pop >= min && pop <= max) ? "Sufficient" : "Insufficient"; 
        String othersValue = othersIndex < others.length ? others[othersIndex] : ""; 
        System.out.println(String.format("|             %3d               |        %3d       |       %3d       |         %3d        |       %3d       |     %21s     |      %15s     |", 
        pop, food, water, medicalSupplies, clothing, othersValue, status));
        
        othersIndex++; 
    }

    System.out.println("============================================================================================================================================================================");
    System.out.println("");
    }
    
     
    public void displayVolunteers(String filePath, String type){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt"))) {
            String line;
            boolean donorFound = false;

            System.out.println("===================================================================================");
            System.out.println("|                                 LIST OF VOLUNTEERS                              |");
            System.out.println("===================================================================================");

            while ((line = reader.readLine()) != null) {
                if (line.contains("Volunteer Type: " + type)) {
                    donorFound = true;

                    // Print donor details
                    System.out.println(line); // Volunteer Type line
                    while ((line = reader.readLine()) != null && !line.startsWith("Volunteer Type:")) {
                        System.out.println(line); // Print each subsequent line until the next "Volunteer Type" or EOF
                    }

                    System.out.println("===================================================================================");
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

    public void displayDonors(String filePath, String type){    
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt"))) {
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
                        System.out.println(line); // Print each subsequent line until the next "Donation Type" or EOF
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
            if (facilitator != null) {
                System.out.println("Hello, facilitator " + facilitator.getFacilitatorName() + "!");

                while (true) {
                    System.out.println("Please enter your PIN number: ");
                    int pin = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (facilitator.getPin() == pin) {
                        break; // Exit PIN entry loop once PIN is correct
                    } else {
                        System.out.println("Incorrect PIN. Please try again.");
                    }
                }

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
        
    }

}
