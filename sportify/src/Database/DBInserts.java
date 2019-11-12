package Database;

public class DBInserts {
    DBInitializer dbInitializer=DBInitializer.getInstance();

    public void insertInitialData(){
        initializeUsers();
        initializeTreatments();
        initializeAppointments();
    }

    private void initializeUsers(){
        insertForAdmin("1", "1", "Admin", "Admin", "admin@gmail.com", "1993-07-100", "male","0547690760");

    }

    private void initializeTreatments(){
        insertTreatment(1, "Braces", 45, 59.9, "A dental brace is a device used to correct\n the alignment of teeth and bite-related problems");
    }

    private void initializeAppointments(){
        insertAppointments(1,1,"2019-07-03","10:00", "302208178", "312656366");
    }

    public String insertTreatment(int id,String name, int duration, double price, String description){
        String sql="INSERT INTO treatments(id, treatmentname, durationmin, price, description)" +
                " VALUES ('"+id+"', '"+name+"', '"+duration+"', '"+price+"', '"+description+"');";
        return dbInitializer.connectAndExecute(sql);
    }

    public String insertAppointments(int id,int treatmentID,String date, String time, String clientid, String doctorid){
        String sql="INSERT INTO appointments(id, treatmentID,appointmentDATE, appointmentTIME,clientID,doctorID)"+
                " VALUES ('"+id+"', '"+treatmentID+"', '"+date+"', '"+time+"', '"+clientid+"', '"+doctorid+"');";
        return dbInitializer.connectAndExecute(sql);
    }

    public String insertForAdmin (String id, String pswd, String name, String lname, String email, String bdate, String gender,String phoneNumber ){
        String sql="INSERT INTO users (id, password, firstname, lastname, email, birthdate, userRole, gender,phoneNumber)"+
                " VALUES ('"+id+"', '"+pswd+"', '"+name+"', '"+lname+"', '"+email+"', '"+bdate+"', 'A','" +gender+"', '"+phoneNumber+"');";
        return dbInitializer.connectAndExecute(sql);
    }

    public String insertForPatient (String id, String pswd, String name, String lname, String email, double wieght, double height, String bdate, String gender,String phoneNumber) {
        String sql="INSERT INTO users(id, password, firstname, lastname, email, weight, height, birthdate, userRole, gender, phoneNumber)" +
                "VALUES ('"+id+"', '"+pswd+"', '"+name+"', '"+lname+"', '"+email+"', '"+wieght+"', '"+height+"', '"+bdate+"', 'P','"+gender +"', '"+phoneNumber+"');";
        return dbInitializer.connectAndExecute(sql);
    }

    public String insertForDoctor (String id, String pswd, String name, String lname, String email, String bdate, String gender,int yearofEx,String phoneNumber) {
        String sql="INSERT INTO users(id, password, firstname, lastname, email, birthdate, userRole,yearOfExperiens,gender,phoneNumber)" +
                "VALUES ('"+id+"', '"+pswd+"', '"+name+"', '"+lname+"', '"+email+"', '"+bdate+"','D','"+yearofEx+"', '"+gender+"', '"+phoneNumber+"');";
        return dbInitializer.connectAndExecute(sql);
    }
}
