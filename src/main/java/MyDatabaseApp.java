import model.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Scanner;

public class MyDatabaseApp {
    public static void main(String[] args) {

        String username = "";
        String password = "";
        for (String s: args){
            String[] parts = s.split("=");
            if (parts[0].equalsIgnoreCase("--username"))
                username = parts[1];
            else if (parts[0].equalsIgnoreCase("--password"))
                password = parts[1];
            System.out.println(s);
        }
        System.out.println("password = " + password);
        System.out.println("username = " + username);

        String url = "jdbc:mysql://localhost:3306/dealership";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the integer dealership id: ");
        int dealershipId = scanner.nextInt();
        scanner.nextLine();


        DealershipDAO dealershipDAO = new DealershipDAO(dataSource);

        Dealership d = dealershipDAO.findDealershipById(dealershipId);

        System.out.printf("""
                Dealership:
                   id = %d
                   name = %s
                   phone = %s
                   address = %s
                """, d.getId(), d.getName(), d.getPhone(), d.getAddress());










        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
