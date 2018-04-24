package ly.generalassemb.de.dataservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataSource;

@Transactional
@EnableAutoConfiguration
public class InstanceService {
    @Autowired
    DataSource dataSource;
}
