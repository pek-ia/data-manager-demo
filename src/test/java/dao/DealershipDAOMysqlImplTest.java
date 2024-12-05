package dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealershipDAOMysqlImplTest {

    @org.junit.jupiter.api.Test
    void test_findVehiclesByDealership(){
        // Arrange
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("root");
        ds.setPassword("yearup");
        ds.setUrl("jdbc:mysql://localhost:3306/dealership");
        DealershipDAOMysqlImpl dd = new DealershipDAOMysqlImpl(ds);

        int expectedNumVehiclesInTestDB = 3;

        // Act
        List<Vehicle> results = dd.findVehiclesByDealership(1);
        // Assert
        assertEquals( expectedNumVehiclesInTestDB, results.size());

        try {
            ds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}