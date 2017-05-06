package customerdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *CustomerDb class that perform SQL Queries
 * @author arp226
 */
public class CustomerDB {

    private static CustomerDB instance;

    private CustomerDB() {

    }

    static CustomerDB getInstance() {

        if (instance == null) {
            instance = new CustomerDB();

        }
        return instance;

    }

    Connection connection = null;

    public void add(String email, String fName, String lName) throws SQLException {
        open();
        // the mysql insert statement
        String insertQuery = "INSERT INTO `Customer_Table`(`Email`, `First_Name`, `Last_Name`) "
                + "VALUES (?,?,?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt;

        preparedStmt = connection.prepareStatement(insertQuery);
        preparedStmt.setString(1, email);
        preparedStmt.setString(2, fName);
        preparedStmt.setString(3, lName);
        preparedStmt.execute();
        close();

    }
    ArrayList<Customer> list;
    // runs select query 
    public ArrayList<Customer> getUserList() {
        open();
        list = new ArrayList<Customer>();
        String query = "Select * from Customer_Table;";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Customer user;
            while (rs.next()) {
                String id = rs.getString("ID");
                String email = rs.getString("Email");
                String fName = rs.getString("First_Name");
                String lName = rs.getString("Last_Name");
                user = new Customer(id, email, fName, lName);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        close();
        return list;

    }
    // runs delete query
    public void delete(String id) throws SQLException {
        open();

        String queryDelete = "DELETE FROM `Customer_Table` WHERE `ID`=" + id;
        Statement st = connection.createStatement();;
        // execute the preparedstatement
        st.executeUpdate(queryDelete);
        st.close();

        close();

    }

    /**
     *
     * Open connection to the database
     *
     * @return boolean
     * @see database.Database#open()
     */
    public boolean open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySql JDBC driver not found");
            e.printStackTrace();
            return false;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://sql2.njit.edu/arp226", "arp226", "oEtHMUQi");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

            return false;
        }
        return connection != null;
    }

    /**
     * Close the database connection
     *
     * @return boolean
     * @see database.Database#close()
     */
    public boolean close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //creates Customer_Table table in SQL database
    public void createTable() throws SQLException {
        open();

        String sqlCreate = "Create table IF NOT EXISTS Customer_Table(ID INT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL ,"
                + " Email VARCHAR(100)UNIQUE NOT NULL,First_Name VARCHAR(100),Last_Name VARCHAR(100))";
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(sqlCreate);
        close();

    }
    // runs update query
    public void update(String email, String fName, String lName, String id) throws SQLException {
        open();
        String updateQuery = "UPDATE `Customer_Table` SET `Email`='" + email + "',"
                + "`First_Name`='" + fName + "',`Last_Name`='" + lName + "' WHERE `ID`=" + id;
        Statement st;

        st = connection.createStatement();
        st.executeUpdate(updateQuery);

        close();
    }

}
