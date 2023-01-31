package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This class is responsible for connecting to our database
public class ConnectionUtil {

    //We want only one connection to the database the entire time
    private static Connection con;

    //Private constructor to prevent anyone from many an object
    private ConnectionUtil() {
        con = null;
    }

    //Method that will give us a connection to the DB
    //OR it will give the existing connection
    public static Connection getConnection()
    {
        //Determine if we already connection and if so give the current connection
        try {

            if (con != null && !con.isClosed()) {
                return con;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url, user, pass;

        //Bad idea! it will be exposed once we push this to the remote repo
        // url = "localhost";
        // user = "postgressql";
        // pass = "password";

        //Good idea to do!
        //System.getenv() method will check your System variables and will find a key exactly like what you specified in the parameter
        //It will then use the value of the key from System variables
        url = System.getenv("url");
        user = System.getenv("user");
        pass = System.getenv("pass");


        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("YOU PROBABLY GAVE THE WRONG PASSWORD OR URL");
        }

         return con;
    }
}
