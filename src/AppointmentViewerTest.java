
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentViewerTest {

    public static void main(String[] args) {
        Calendar calendar = new Calendar();
        AppointmentViewer viewer = new AppointmentViewer();

        Appointment first = new Appointment(
                "Smith", LocalDate.of(2026, 11, 4),
                LocalTime.of(15, 30));
        first.book("Tina");
        calendar.addAppointment(first);

        Appointment second = new Appointment(
                "Brown", LocalDate.of(2026, 11, 5),
                LocalTime.of(10, 0));
        second.book("Marissa");
        calendar.addAppointment(second);

        System.out.println("EMPLOYEE VIEW:");
        viewer.viewAppointments(calendar);

        System.out.println("TINA'S VIEW:");
        viewer.viewAppointments(calendar, "Tina");

        System.out.println("PATIENT WITH NO BOOKINGS:");
        viewer.viewAppointments(calendar, "Alex");
    }
}
