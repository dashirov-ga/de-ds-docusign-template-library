package ly.generalassemb.de.dataservices.components;

import ly.generalassemb.de.dataservices.jdbcTemplate.InstanceRowMapper;
import ly.generalassemb.de.dataservices.model.Instance;
import ly.generalassemb.de.dataservices.model.InstanceRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SimpleInstanceRepository implements InstanceRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Cacheable("instances")
    public Optional<Instance> getById(String id) {
        Instance instance = (Instance)jdbcTemplate.queryForObject(
                "WITH instance as (\n" +
                        "  /* DEDUPLICATE TO ENSURE CORRECT NUMBER OF RECORDS*/\n" +
                        "  SELECT DISTINCT\n" +
                        "     instance_core_instance_id as stock_keeping_unit,\n" +
                        "     instance_price_currency as price_currency,\n" +
                        "     instance_price_cents::DECIMAL(18,8)/100.00 as price_amount,\n" +
                        "     UPPER(REPLACE(instance_program_format,' ','_')) as program_format,\n" +
                        "     UPPER(instance_program_abbreviation) as program_abbreviation,\n" +
                        "     instance_core_campus_id as campus_id\n" +
                        "     from presentation.dim_instances\n" +
                        "), campus as (\n" +
                        "  SELECT DISTINCT\n" +
                        "  campus_core_campus_id as campus_id,\n" +
                        "  campus_district_metro_name as metro_name\n" +
                        "  from presentation.dim_campuses\n" +
                        ")\n" +
                        "select\n" +
                        "     'CORE' as inventory_management_system,\n" +
                        "     instance.stock_keeping_unit::VARCHAR(20),\n" +
                        "     instance.price_currency,\n" +
                        "     instance.price_amount,\n" +
                        "     instance.program_format,\n" +
                        "     instance.program_abbreviation,\n" +
                        "     campus.metro_name\n" +
                        "from instance join campus using (campus_id) \n" +
                        "where stock_keeping_unit = ?;\n",
                new Object[] { id },
                new InstanceRowMapper());
        return Optional.ofNullable(instance);

    }
}
