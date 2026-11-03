import java.util.Scanner;

/**
 Represents the home screen for the MediLink appointment system.

 This class welcomes the user and handles all options regarding scheduling appointments.
 main(String) -> Home screen menu
 employeeMenu() -> Employee sub-menu
 patientMenu() -> Patient sub-menu
 addAppointment() -> Method to add appointments
 printAppointments() -> Method to print all appointments
 viewMyAppointments() -> Method to view personal appointments
 scheduleAppointment() -> Method to schedule an appointment
 prompt(String) -> Helper method for user prompts
 */
public class HomeScreen {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Calendar calendar = new Calendar();

    /*
        Main menu and landing page for users.
        Funnels users to the correct sub-menu for their role, exits on "0".
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the MediLink Appointment System!");
        while (true) {
            System.out.println("""
                    Who are you?
                    1. Clinic employee
                    2. Patient
                    0. Exit""");
            switch(prompt("Choose an option: ")) {
                case "1": employeeMenu(); break;
                case "2": patientMenu(); break;
                case "0":
                    System.out.println("Thank you for using MediLink!\nExiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter either 0, 1, or 2.");
            }

        }
    }

    /*
        Sub-menu for employees.
        This handles the addition of appointment slots (PBI1) and the listing of all appointment slots (PBI5).
        Returns to home screen on "0"
     */
    private static void employeeMenu() {
        System.out.println("\nWelcome to the Employee Menu!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1. Add appointment slot
                    2. List all appointment slots
                    0. Back to home screen""");
            switch(prompt("Choose an option: ")) {
                case "1": addAppointment(); break;
                case "2": printAppointments(); break;
                case "0": return;
                default:
                    System.out.println("Invalid choice, please enter either 0, 1, or 2.");
            }
        }
    }

    /*
        Sub-menu for patients.
        This handles the scheduling of appointment slots (PBI4) and the viewing of all scheduled appointments (PBI5).
        Returns to home screen on "0".
     */
    private static void patientMenu() {
        System.out.println("\nWelcome to the Patient Menu!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1. Schedule an appointment
                    2. View scheduled appointments
                    0. Back to home screen""");
            switch(prompt("Choose an option: ")) {
                case "1": scheduleAppointment(); break;
                case "2": viewMyAppointments(); break;
                case "0": return;
                default:
                    System.out.println("Invalid choice, please enter either 0, 1, or 2.");
            }
        }
    }

    /*
        Employee Only.
        This method allows an employee to add an open appointment slot.
     */
    private static void addAppointment() {
        new AddAppointment(calendar).run();
    }

    /*
        Employee Only.
        This method prints all scheduled appointments and available appointment slots (PBI5).
     */
    private static void printAppointments() {
        System.out.println("All appointment slots:");
        System.out.println("This feature is currently unavailable.");
    }

    /*
        Patient Only.
        This method prints all scheduled appointments for the user (PBI5).
     */
    private static void viewMyAppointments() {
        System.out.println("Your scheduled appointments:");
        System.out.println("This feature is currently unavailable.");
    }

    /*
        Patient Only.
        This method handles the scheduling of an appointment for the user (PBI4).
     */
    private static void scheduleAppointment() {
        System.out.println("Schedule your appointment:");
        System.out.println("This feature is currently unavailable.");
    }

    /*
        Helper method for user prompting.
        If the scanner has information, read and trim the line and return it as a prompt.
        Return 0 if there is no more input to treat as "Exit" or "Go back to home screen".
     */
    public static String prompt(String message) {
        System.out.print(message);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "0";
    }
}