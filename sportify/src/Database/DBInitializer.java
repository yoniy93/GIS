package Database;

import java.io.File;
import java.sql.*;

public class DBInitializer {

    private static DBInitializer instance;

    private DBInitializer () {}

    //SingleTon Function
    public static DBInitializer getInstance(){
        if (instance==null)
            instance=new DBInitializer();
        return instance;
    }

    //Connection to DataBase
    public Connection connect() {
        String url = Locations.getDatabasePath();

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //Function to create new DB
    public static void createNewDatabase() {

        String url = Locations.getDatabasePath();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("Connection to DB was successfully.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private static boolean isDBExists() {
        String s= Locations.getDatabasePath();
        String location = s.substring(s.lastIndexOf(':') + 1).trim();
        File file=new File(location);
        return file.exists();
    }

    //Create new DB table
    private static void createTablesInDB() {
        createNewDatabase();
        createNewTables();
    }

    //Check if DB is already exists or create new DB with basic data
    public static void loadDatabaseWithInitialData(){
        boolean isDBexists=isDBExists();
        createTablesInDB();
        if (!isDBexists) {
            System.out.println("Initial database was loaded successfully ");
            DBInserts db=new DBInserts();
            db.insertInitialData();
        }
    }

    public static void createNewTables() {

        String url,sql,sql2,sql3;

        url = Locations.getDatabasePath();

        sql = "CREATE TABLE IF NOT EXISTS users (id text PRIMARY KEY, password text NOT NULL, firstname text NOT NULL," +
                "lastname text NOT NULL, email text NOT NULL, weight double, height double, birthdate date NOT NULL, userRole text," +
                "yearOfExperiens integer, gender text, phoneNumber text NOT NULL);";

        sql2="CREATE TABLE IF NOT EXISTS treatments ( id integer PRIMARY KEY NOT NULL, treatmentname text NOT NULL,"+
                " durationmin integer NOT NULL, price double NOT NULL, description text);";

        sql3="CREATE TABLE IF NOT EXISTS appointments (id integer PRIMARY KEY NOT NULL,treatmentID integer NOT NULL, appointmentDATE date NOT NULL,"+
                " appointmentTIME time NOT NULL,clientID text NOT NULL, doctorID text NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            stmt.execute(sql2);
            stmt.execute(sql3);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Connect to DB and run sql query
    protected String connectAndExecute(String sql){
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            return "Sucssesfuly";
        } catch (SQLException e) {
            System.out.println(e.getMessage() );
            return e.getMessage();
        }
    }


}
