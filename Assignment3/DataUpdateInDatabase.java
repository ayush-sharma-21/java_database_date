package Assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class DataUpdateInDatabase {
    public int UpdateData(CustomerDataUpdate customerDataUpdate){

        Calendar calendar = Calendar.getInstance();
        java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;

        try {
            String sql = "update customer_details set street = ?,city = ?,state = ?,zipcode = ?,product_name = ?,updated_on = ?,updated_by = ? where cid = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment1", "Trantor", "1234");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerDataUpdate.getStreet());
            preparedStatement.setString(2, customerDataUpdate.getCity());
            preparedStatement.setString(3, customerDataUpdate.getState());
            preparedStatement.setInt(4, customerDataUpdate.getZipcode());
            preparedStatement.setString(5, customerDataUpdate.getProduct_name());
            preparedStatement.setDate(6, ourJavaDateObject);
            preparedStatement.setString(7, customerDataUpdate.getUpdated_by());
            preparedStatement.setInt(8, customerDataUpdate.getCid());


            count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            return count;
    }
}
}
