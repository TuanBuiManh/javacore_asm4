package application;

import com.example.ui.Console;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class application {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        Console lc = new Console();
        lc.preStart();
        lc.start();
    }
}
