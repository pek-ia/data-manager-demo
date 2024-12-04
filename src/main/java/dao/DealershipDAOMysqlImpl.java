package dao;

import model.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAOMysqlImpl implements DealershipDAO {

    private final DataSource dataSource;

    public DealershipDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Dealership findDealershipById(int id){
        String name = "";
        String phone = "";
        String address = "";

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * 
                    FROM dealerships 
                    WHERE id = ?;
                    """);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getString("phone");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Dealership(id, name, phone, address);

    }

    @Override
    public List<Dealership> findAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();



        return dealerships;
    }
}
