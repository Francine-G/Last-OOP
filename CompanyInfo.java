import java.util.Scanner;

class Company {
    private String companyName;
    private String companyEmail;
    private String companyAddress;
    private String companyNumber; // Change to String for validation

    // Constructor
    public void setCompanyDetails(String companyName, String companyEmail, String companyAddress, String companyNumber) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyAddress = companyAddress;
        this.companyNumber = companyNumber;
    }

    // Getters 
    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void displayCompanyInfo() {
        System.out.println("\n==============================================================================");
        System.out.println("                           WELCOME TO " + companyName.toUpperCase());
        System.out.println("Address: " + companyAddress);
        System.out.println("Email: " + companyEmail);
        System.out.println("Contact Number: " + companyNumber);
        System.out.println("==============================================================================\n");
    }
}

public class CompanyInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Company company = new Company();

        System.out.println("\n==============================================================================");
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine();

        System.out.print("Enter Company Email: ");
        String companyEmail = scanner.nextLine();

        System.out.print("Enter Company Address: ");
        String companyAddress = scanner.nextLine();

        // Contact number validation loop
        String companyNumber = "";
        while (true) {
            System.out.print("Enter your Contact Number (11 digits): ");
            companyNumber = scanner.nextLine();

            if (companyNumber.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("Invalid contact number. Please enter exactly 11 digits.");
            }
        }
        
        System.out.println("\n==============================================================================");
        
        company.setCompanyDetails(companyName, companyEmail, companyAddress, companyNumber);

        // Display company info
        company.displayCompanyInfo();

        scanner.close();
    }
}
