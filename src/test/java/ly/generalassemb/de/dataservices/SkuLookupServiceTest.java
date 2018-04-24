package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.api.SkuLookupService;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@PropertySource("classpath:application.properties")
@TestPropertySource("classpath:application.properties")                              //critical
@Import(SkuLookupService.class)
public class SkuLookupServiceTest {
    @Autowired
    SkuLookupService skuLookupService;

    @Test
    public void contextLoads(){
    }

    @Test
    public void testKnownInstance(){
        Optional<InstanceCacheItem> item = skuLookupService.lookup("42700");
        Assert.assertNotNull(item);
        // Payload is present
        Assert.assertTrue(item.isPresent());
        InstanceCacheItem payload =  item.get();
        // Payload metro can be bound to a constant
        Assert.assertEquals("New York City", payload.getMetroName());
        Assert.assertEquals(Metro.NEW_YORK_CITY, Metro.findByName(payload.getMetroName()));

        Assert.assertEquals("IMMERSIVE", payload.getProgramFormat());
        Assert.assertEquals(ProgramFormat.IMMERSIVE, ProgramFormat.valueOf(payload.getProgramFormat()));

    }
}
