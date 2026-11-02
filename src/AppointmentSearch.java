import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSearch {

    private final Calendar calendar;

    public AppointmentSearch(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Appointment> getAvailableAppointments() {
        List<Appointment> results = new ArrayList<>();

        for (Appointment appointment : calendar.getAppointments()) {
            if (!appointment.isBooked()) {
                results.add(appointment);
            }
        }

        return results;
    }

    public List<Appointment> searchByDoctor(String doctorName) {
        List<Appointment> results = new ArrayList<>();

        for (Appointment appointment : calendar.getAppointments()) {
            if (!appointment.isBooked()
                    && appointment.getDoctorName().equalsIgnoreCase(doctorName)) {
                results.add(appointment);
            }
        }

        return results;
    }

    public List<Appointment> searchByDate(LocalDate date) {
        List<Appointment> results = new ArrayList<>();

        for (Appointment appointment : calendar.getAppointments()) {
            if (!appointment.isBooked()
                    && appointment.getDate().equals(date)) {
                results.add(appointment);
            }
        }

        return results;
    }

    public List<Appointment> search(
            String doctorName,
            LocalDate date,
            LocalTime time) {

        List<Appointment> results = new ArrayList<>();

        for (Appointment appointment : calendar.getAppointments()) {

            if (appointment.isBooked()) {
                continue;
            }

            boolean doctorMatches =
                    doctorName == null
                    || doctorName.isBlank()
                    || appointment.getDoctorName().equalsIgnoreCase(doctorName);

            boolean dateMatches =
                    date == null
                    || appointment.getDate().equals(date);

            boolean timeMatches =
                    time == null
                    || appointment.getTime().equals(time);

            if (doctorMatches && dateMatches && timeMatches) {
                results.add(appointment);
            }
        }

        return results;
    }

    public void displayResults(List<Appointment> appointments) {

        if (appointments.isEmpty()) {
            System.out.println("No available appointments found.");
            return;
        }

        System.out.println("Available Appointments:");

        for (int i = 0; i < appointments.size(); i++) {
            System.out.println("\nAppointment " + (i + 1));
            System.out.println(appointments.get(i));
        }
    }
}