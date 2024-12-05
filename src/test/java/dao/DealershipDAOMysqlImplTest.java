package dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class DealershipDAOMysqlImplTest {

    static BasicDataSource ds = new BasicDataSource();

    @BeforeAll
    static void setup() {
        ds.setUsername("dealershiptest");
        ds.setPassword("yearup");
        ds.setUrl("jdbc:mysql://localhost:3306/dealership");
    }

    @org.junit.jupiter.api.Test
    void test_findVehiclesByDealership(){
        // Arrange
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

    @Test
    void findVehiclesByPriceRange() {
        // Arrange
        DealershipDAOMysqlImpl dd = new DealershipDAOMysqlImpl(ds);
        double minPrice = 10000.;
        double maxPrice = 20000.;

        int expectedNumVehiclesInTestDB = 3;
        // Act
        List<Vehicle> results = dd.findVehiclesByPriceRange(minPrice, maxPrice);
        // Assert
        assertEquals( expectedNumVehiclesInTestDB, results.size());

        try {
            ds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}