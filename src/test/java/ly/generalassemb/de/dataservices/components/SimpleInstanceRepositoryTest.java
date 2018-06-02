package ly.generalassemb.de.dataservices.components;

import com.zaxxer.hikari.HikariDataSource;
import ly.generalassemb.de.dataservices.model.Instance;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class SimpleInstanceRepositoryTest {


    SimpleInstanceRepository simpleInstanceRepository;

    @Before
    public void setUp() throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:redshift://localhost:15439/dev");
        dataSource.setUsername("david_ashirov");
        dataSource.setPassword("past-wood-WHICH-STREET-46");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        simpleInstanceRepository = new SimpleInstanceRepository();
        simpleInstanceRepository.setJdbcTemplate(jdbcTemplate);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        Optional<Instance> i = simpleInstanceRepository.getById("43466");
        Assert.assertTrue(i.isPresent());
        Instance instance = i.get();
        Assert.assertEquals("43466", instance.getStockKeepingUnit());

    }
}