/*package Database;

import Database.DBInitializer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBQuerys{

    DBInitializer dbInitializer = DBInitializer.getInstance();

    public Admin getAdminDetails (String id) {
        String sql = "SELECT * FROM users WHERE id="+id;
        try (Connection conn = dbInitializer.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date date=format.parse(rs.getString("birthdate"));

            return new Admin(id,rs.getString("password"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email"), date, rs.getString("gender"),
                    rs.getString("phoneNumber"));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Doctor getDoctorDetails (String id) {
        String sql = "SELECT * FROM users WHERE id="+id;
        try (Connection conn = dbInitializer.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date date=format.parse(rs.getString("birthdate"));

            return new Doctor(id,rs.getString("password"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email"), date, rs.getInt("yearOfExperiens"),
                    rs.getString("gender"),
                    rs.getString("phoneNumber"));

        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Patient getPatientDetails (String id) {
        String sql = "SELECT * FROM users WHERE id="+id;
        try (Connection conn = dbInitializer.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(rs.getString("birthdate"));

            return new Patient(
                    id, rs.getString("password"), rs.getString("firstname"),
                    rs.getString("lastname"), rs.getString("email"),
                    rs.getDouble("weight"), rs.getDouble("height"),
                    date, rs.getString("gender"),
                    rs.getString("phoneNumber"));

        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean isUserExists (String userid){
        String sql = "SELECT id FROM users WHERE id ="+userid;
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            boolean isExist=resultSet.next();
            return isExist;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isPasswordCorrect (String userid, String password){
        String sql = "SELECT password FROM users WHERE id ="+userid;
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            return resultSet.getString("password").equals(password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public USER_TYPE getTypeOfUser (String userid){
        String sql = "SELECT userRole FROM users WHERE id ="+userid;
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            String userType = pstmt.executeQuery().getString("userRole");
            switch (userType) {
                case "A":
                    return USER_TYPE.ADMIN;
                case "D":
                    return USER_TYPE.DOCTOR;
                case "P":
                    return USER_TYPE.PATIENT;
                default:
                    return USER_TYPE.ERROR;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return USER_TYPE.ERROR;
        }
    }

    public List<Doctor> getDoctors () {
        List<Doctor> doctors = new ArrayList<>(0);
        String sql ="SELECT * FROM users WHERE userRole='D';";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(resultSet.getString("birthdate"));
                doctors.add(new Doctor(resultSet.getString("id"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        date,
                        resultSet.getInt("yearOfExperiens"),
                        resultSet.getString("gender"),
                        resultSet.getString("phoneNumber")));
            }
            return doctors;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAvailableTime (String doctorID,String date, String time) {
        String sql = "SELECT appointmentTIME FROM appointments WHERE appointmentDATE like " + '"' + date + '"' + " AND doctorID like " + '"' + doctorID + '"' + "AND appointmentTIME like "+'"'+time+'"';
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getLastId(String tablename){
        String sql="Select max(id) from "+tablename+";";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public List<Treatments> getTreatments (){
        List<Treatments> treatments=new ArrayList<>(0);
        String sql="SELECT * FROM treatments;";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                treatments.add(new Treatments(resultSet.getString("id"),
                        resultSet.getString("treatmentname"),
                        resultSet.getInt("durationmin"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description")));
            }
            return treatments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public List<Appointment> getPatientAppointmentsHistory(String id){
        List<Appointment> appointments = new ArrayList<>(0);

        String sql="SELECT * FROM appointments WHERE clientID="+id+" AND appointmentDATE<date('now', 'localtime');";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            Appointment temp;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date date;
            Time time;
            while (resultSet.next()){

                date = format.parse(resultSet.getString("appointmentDATE"));
                time = new Time(timeFormat.parse(resultSet.getString("appointmentTIME")).getTime());
                temp=new Appointment(resultSet.getString("id"),
                        resultSet.getString("treatmentID"),
                        date, time,
                        resultSet.getString("clientID"),
                        resultSet.getString("doctorID"));
                appointments.add(temp);
            }
            return appointments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Appointment> getPatientAppointments(String id){
        List<Appointment> appointments=new ArrayList<>(0);
        String sql="SELECT * FROM appointments WHERE clientID="+id+";";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            Appointment temp;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date date;
            Time time;
            while (resultSet.next()){
                date = format.parse(resultSet.getString("appointmentDATE"));
                time = new Time(timeFormat.parse(resultSet.getString("appointmentTIME")).getTime());
                temp=new Appointment(resultSet.getString("id"),
                        resultSet.getString("treatmentID"),
                        date, time,
                        resultSet.getString("clientID"),
                        resultSet.getString("doctorID"));
                appointments.add(temp);
            }
            return appointments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getAllPatientsID() {
        List<String> patientsIdList=new ArrayList<>(0);
        String sql="SELECT DISTINCT clientID FROM appointments;";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                patientsIdList.add(resultSet.getString(1));
            }
            if (patientsIdList.size()==0)
                return null;
            return patientsIdList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getTreatmentName (String id){
        String sql="SELECT treatmentname FROM treatments WHERE id="+id+";";
        try (Connection conn = dbInitializer.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            ResultSet resultSet=pstmt.executeQuery();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
            else return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}*/
