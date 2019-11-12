package Database;

public class DBUpdates {

    DBInitializer dbInitializer = DBInitializer.getInstance();

    /*
    public void updatePatientDetails(Patient p){
        String sql= "UPDATE users SET password='"+p.getPassword()+"', firstname='"+p.getFirstName() +
                "', lastname='"+p.getLastName() +"', email='"+p.getEmail() +"', phoneNumber='"+p.getPhoneNumber() +
                "', weight='"+p.getWeight() +"', height='"+p.getHeight() +"' WHERE id='" +p.getId()+"';";
        dbInitializer.connectAndExecute(sql);
    }

    public void updateDoctorDetails(Doctor d){
        String sql= "UPDATE users SET password='"+d.getPassword()+"', firstname='"+d.getFirstName() +
                "', lastname='"+d.getLastName() +"', email='"+d.getEmail() +"', phoneNumber='"+d.getPhoneNumber() +
                "', yearOfExperiens='"+d.getYearsOfExp() +"' WHERE id='" +d.getId()+"';";
        dbInitializer.connectAndExecute(sql);
    }

    public void updateAdmin(Admin a) {
        String sql= "UPDATE users SET password='"+a.getPassword()+"', firstname='"+a.getFirstName() +
                "', lastname='"+a.getLastName() +"', email='"+a.getEmail() +"', phoneNumber='"+a.getPhoneNumber() +"' WHERE id='" +a.getId()+"';";
        dbInitializer.connectAndExecute(sql);
    }

    public String deleteUserFromDB(String id) {
        String sql="DELETE FROM users WHERE id='"+id+"';";
        return dbInitializer.connectAndExecute(sql);
    }

    public String deleteAppointmentFromDB(String id) {
        String sql="DELETE FROM appointments WHERE id='"+id+"';";
        return dbInitializer.connectAndExecute(sql);
    }

    public String deleteTreatmentFromDB(String id) {
        String sql="DELETE FROM treatments WHERE id='"+id+"';";
        return dbInitializer.connectAndExecute(sql);
    }
    */
}

