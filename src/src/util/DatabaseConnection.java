package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


//TODO implement( edit) it as a managed bean
//TODO add annotation ApplicationScope
//TODO migrate the methods from Connector class
public class DatabaseConnection
{
    private Logger logger = Logger.getLogger(getClass().getName());
    private static DatabaseConnection singleInstance;
    private static Connection dbConnect;

    private DatabaseConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/TrackInSchema", "root", "jaja123");
            logger.info("DatabaseConnection: Connection is done");
        }
        catch (SQLException e)
        {
            logger.info("SQLException: " + e.getMessage() );
        }catch (ClassNotFoundException e) {
            logger.info("ClassNotFoundException: " + e.getMessage() );
        }
    }

    public static DatabaseConnection getInstance()
    {
        if(singleInstance == null)
        {
            synchronized (DatabaseConnection.class)
            {
                if(singleInstance == null)
                {
                    singleInstance = new DatabaseConnection();
                }
            }
        }

        return singleInstance;
    }

    public static Connection getConnInst()
    {
        if(dbConnect == null)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme", "root", "jaja123");
            }
            catch (SQLException e)
            {
                Logger.getLogger("util.DatabaseConnection").info("SQLException: " + e.getMessage() );
            }catch (ClassNotFoundException e) {
                Logger.getLogger("util.DatabaseConnection").info("ClassNotFoundException: " + e.getMessage() );
            }
        }

        return dbConnect;
    }
}