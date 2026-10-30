
/**
 * Displays scheduled appointments for employees and patients.
 * Each appointment includes its doctor, date, time, and patient.
 */
public class AppointmentViewer {

    // Employees can view all booked appointments.
    public void viewAppointments(Calendar calendar) {
        boolean found = false;

        for (Appointment appointment : calendar.getAppointments()) {
            if (appointment.isBooked()) {
                System.out.println(appointment);
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No scheduled appointments.");
        }
    }

    // Patients can view appointments booked under their name.
    public void viewAppointments(Calendar calendar, String patientName) {
        if (patientName == null || patientName.trim().isEmpty()) {
            System.out.println("Please enter your name.");
            return;
        }

        boolean found = false;
        patientName = patientName.trim();

        for (Appointment appointment : calendar.getAppointments()) {
            if (appointment.isBooked()
                    && appointment.getPatientName()
                            .equalsIgnoreCase(patientName)) {
                System.out.println(appointment);
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No scheduled appointments for this patient.");
        }
    }
}
