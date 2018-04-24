package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.api.SkuLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InstanceCache {
    private static Logger LOGGER = LoggerFactory.getLogger(InstanceCache.class);

    private Map<String,InstanceCacheItem> cachedInstanceSet;
   private LocalDateTime updatedAt;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, InstanceCacheItem> getCachedInstanceSet() {
        return cachedInstanceSet;
    }

    public void setCachedInstanceSet(Map<String, InstanceCacheItem> cachedInstanceSet) {
        this.cachedInstanceSet = cachedInstanceSet;
    }

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void update() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("\n" +
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
                "from instance join campus using (campus_id);\n");
        ResultSet rs = ps.executeQuery();
        Map<String, InstanceCacheItem> newVolume = new HashMap<>();
        while(rs.next()){
            InstanceCacheItem item = new InstanceCacheItem();
            item.setInventoryManagementSystem(rs.getString("inventory_management_system"));
            item.setStockKeepingUnit(rs.getString("stock_keeping_unit"));
            item.setListCurrency(rs.getString("price_currency"));
            item.setListPrice(rs.getDouble("price_amount"));
            item.setMetroName(rs.getString("metro_name"));
            item.setProgramFormat(rs.getString("program_format"));
            item.setProgramAbbreviation(rs.getString("program_abbreviation"));
            newVolume.put(item.stockKeepingUnit,item);
        }
        this.cachedInstanceSet = newVolume;
        this.updatedAt = LocalDateTime.now();

    }


}
