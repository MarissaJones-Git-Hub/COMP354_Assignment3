
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Demonstrates scheduled appointment viewing with sample data.
 */
public class AppointmentViewerTest {

    public static void main(String[] args) {
        Calendar calendar = new Calendar();
        AppointmentViewer viewer = new AppointmentViewer();
        Scanner scanner = new Scanner(System.in);

        Appointment first = new Appointment(
                "Smith",
                LocalDate.of(2026, 11, 4),
                LocalTime.of(15, 30));
        first.book("Tina");
        calendar.addAppointment(first);

        Appointment second = new Appointment(
                "Brown",
                LocalDate.of(2026, 11, 5),
                LocalTime.of(10, 0));
        second.book("Marissa");
        calendar.addAppointment(second);

        // Available slots are excluded from scheduled appointment views.
        Appointment available = new Appointment(
                "Wilson",
                LocalDate.of(2026, 11, 6),
                LocalTime.of(9, 0));
        calendar.addAppointment(available);

        boolean running = true;

        while (running) {
            System.out.println("\n--- Scheduled Appointments ---");
            System.out.println("1. View all scheduled appointments");
            System.out.println("2. View my scheduled appointments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println(
                            "\n--- All Scheduled Appointments ---");
                    viewer.viewAppointments(calendar);
                    break;

                case "2":
                    System.out.print("Enter your name: ");
                    String patientName = scanner.nextLine();

                    System.out.println(
                            "\n--- My Scheduled Appointments ---");
                    viewer.viewAppointments(calendar, patientName);
                    break;

                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please enter 1, 2, or 0.");
            }
        }

        scanner.close();
    }
}
