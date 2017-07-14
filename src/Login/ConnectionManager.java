package Login;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class ConnectionManager {

    private static Connection connection;

    public static Connection getConnection() {

       try {
           SQLServerDataSource ds = new SQLServerDataSource();
           ds.setServerName( "10.10.200.253" );
           ds.setUser( "kubra" );
           ds.setPassword( "352*" );
           ds.setDatabaseName( "PEOPLE" );
           connection = ds.getConnection();
       }
       catch(Exception e){
           System.out.println(e);
       }

       return connection;
    }
}
