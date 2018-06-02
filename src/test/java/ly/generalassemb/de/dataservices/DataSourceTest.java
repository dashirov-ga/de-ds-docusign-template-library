package ly.generalassemb.de.dataservices;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RunWith(SpringRunner.class)

@PropertySource("classpath:application.properties")
@TestPropertySource("classpath:application.properties")                              //critical
@EnableAutoConfiguration                                                            //critical
public class DataSourceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(DataSourceTest.class);
    @Autowired
    DataSource dataSource;

    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
    }
    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testConnection() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT 1;");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int one = rs.getInt(1);
            rs.close();
            Assert.assertEquals(1, one);
        } catch (Exception e){
            Assert.assertNull(e);
        }
    }
}

