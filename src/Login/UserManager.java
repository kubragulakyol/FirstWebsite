package Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserManager {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private User user;

    public boolean login(User user) {
        String searchQuery = "select * from dbo.users where username='" + user.getUsername() + "' AND password='" + user.getPassword() + "'";
        Statement queryStatement = null;
        this.user = user;

        try {
            connection = ConnectionManager.getConnection();
            queryStatement = connection.createStatement();
            resultSet = queryStatement.executeQuery(searchQuery);

            if ( resultSet.next() ) {
                this.setUser( resultSet );
            }
        }
        catch ( Exception e ) {
            System.out.println( "Login failed!" + e );
        }
        finally {
            try {
                if ( resultSet != null )
                    resultSet.close();

                if ( queryStatement != null )
                    queryStatement.close();

                if ( connection != null )
                    connection.close();
            }
            catch ( Exception e ) {
                System.out.println( "Connection release attempt has failed!" + e );
            }
        }

        return this.user.isValid();
    }

    private void setUser(ResultSet resultSet) {
        try {
            this.user.setFirstName( resultSet.getString("FirstName") );
            this.user.setLastName( resultSet.getString("LastName") );
            this.user.setValid(true);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser() throws Exception {
        if (this.user.isValid()) {
            // System.out.println("Welcome" + this.firstName);
            System.out.println("Your user name is " + this.user.getUsername());
            System.out.println("Your password is " + this.user.getPassword());

        /*
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setValid(true);
         */

        }
        else {
            throw new Exception("No User");
        }

        return this.user;
    }
}
