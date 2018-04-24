package ly.generalassemb.de.dataservices.api;

import ly.generalassemb.de.dataservices.InstanceCache;
import ly.generalassemb.de.dataservices.InstanceCacheItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

@EnableAutoConfiguration
public class SkuLookupService implements InitializingBean {
    private static Logger LOGGER = LoggerFactory.getLogger(SkuLookupService.class);
    InstanceCache cache = new InstanceCache();
    Boolean isInitialized = false;

    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Initializing Cache...");
        cache.setDataSource(dataSource);
        cache.update();
        this.isInitialized=true;
        LOGGER.info("Cache loaded.");
    }

    public Optional<InstanceCacheItem> lookup(String key){
        Map<String,InstanceCacheItem> map = cache.getCachedInstanceSet();
        if (map!=null && map.containsKey(key))
           return Optional.of(map.get(key));
        return Optional.empty();
    }
}
