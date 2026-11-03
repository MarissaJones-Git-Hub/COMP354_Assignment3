import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentBooking {

    private final Calendar calendar;

    public AppointmentBooking(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean bookAppointment(Appointment appointment, String patientName) {

        if (appointment == null) {
            System.out.println("Invalid appointment.");
            return false;
        }

        if (patientName == null || patientName.isBlank()) {
            System.out.println("Patient name is required.");
            return false;
        }

        if (!calendar.getAppointments().contains(appointment)) {
            System.out.println("Appointment does not exist in the calendar.");
            return false;
        }

        if (appointment.isBooked()) {
            System.out.println("This appointment has already been booked.");
            return false;
        }

        appointment.book(patientName.trim());

        System.out.println("Appointment successfully booked.\n");
        System.out.println(appointment);
        System.out.println();
        return true;
    }

    public boolean bookAppointment(
            String doctorName,
            LocalDate date,
            LocalTime time,
            String patientName) {

        if (doctorName == null || doctorName.isBlank()
                || date == null
                || time == null) {
            System.out.println("Doctor, date, and time are required.");
            return false;
        }

        if (patientName == null || patientName.isBlank()) {
            System.out.println("Patient name is required.");
            return false;
        }

        for (Appointment appointment : calendar.getAppointments()) {

            boolean matches =
                    appointment.getDoctorName().equalsIgnoreCase(doctorName)
                    && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time);

            if (matches) {

                if (appointment.isBooked()) {
                    System.out.println("Selected appointment is already booked.");
                    return false;
                }

                appointment.book(patientName.trim());

                System.out.println("Appointment successfully booked.");
                System.out.println(appointment);
                System.out.println();
                return true;
            }
        }

        System.out.println("Selected appointment could not be found.");
        return false;
    }
}