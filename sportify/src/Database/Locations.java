package Database;

import java.io.File;

public class Locations {

    public static String getDatabasePath() {
        return "jdbc:sqlite:"+new File("DB.db").getAbsolutePath();
    }
    public static String getImagePath (String fileName){
        return new File("Backgrounds/"+fileName).getAbsolutePath();
    }

}
