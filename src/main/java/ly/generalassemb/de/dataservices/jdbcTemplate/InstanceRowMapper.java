package ly.generalassemb.de.dataservices.jdbcTemplate;

import ly.generalassemb.de.dataservices.model.Instance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstanceRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Instance instance = new Instance();
        instance.setInventoryManagementSystem(resultSet.getString("inventory_management_system"));
        instance.setStockKeepingUnit(resultSet.getString("stock_keeping_unit"));
        instance.setListCurrency(resultSet.getString("price_currency"));
        instance.setListPrice(resultSet.getDouble("price_amount"));
        instance.setProgramFormat(resultSet.getString("program_format"));
        instance.setProgramAbbreviation(resultSet.getString("program_abbreviation"));
        instance.setMetroName(resultSet.getString("metro_name"));
        return instance;
    }
}
