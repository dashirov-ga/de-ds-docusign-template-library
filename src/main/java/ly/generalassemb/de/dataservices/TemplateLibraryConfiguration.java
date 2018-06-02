package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.api.TemplateLibraryService;
import ly.generalassemb.de.dataservices.api.TemplateLibraryWebService;
import ly.generalassemb.de.dataservices.components.SimpleInstanceRepository;
import ly.generalassemb.de.dataservices.model.InstanceRepository;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Spring bean configuration class – which will be responsible for
 * instantiating the TemplateLibraryService bean and its dependencies:
 */
@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class TemplateLibraryConfiguration {
    private static final String drlFile = "RESOLUTION_RULES.drl";

    /**
     *
     * @return KieContainer
     * KieContainer is a placeholder for all the object that we need
     * to run the rule engine. The KieBase can be obtained from
     * the KieContainer.
     *
     * KieContainer is built with the help of other beans including
     * KieFileSystem, KieBuilder, and KieModule.
     *
     * KieBase is a repository which contains all knowledge related
     * to the application such as rules, processes, functions, type models
     * and it is hidden inside KieModule.
     */
    @Bean
    public KieContainer kieContainer() {
        // KieServices is a singleton which acts as a single point
        // entry to get all services provided by Kie.
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        // create a KieModule which is a container of all the resources which are
        // required to define rule knowledge known as KieBase.
        KieModule kieModule = kieBuilder.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    TemplateLibraryService templateLibraryService(KieContainer kieContainer){
        TemplateLibraryService templateLibraryService=new TemplateLibraryService();
        templateLibraryService.setKieContainer(kieContainer);
        return templateLibraryService;
    }

    @Bean
    public TemplateLibraryWebService templateLibraryWebService(TemplateLibraryService templateLibraryService,
                                                               InstanceRepository instanceRepository
    ){
        TemplateLibraryWebService templateLibraryWebService = new TemplateLibraryWebService();
        templateLibraryWebService.setTemplateLibraryService(templateLibraryService);
        templateLibraryWebService.setInstanceRepository(instanceRepository);
        return templateLibraryWebService;
    }

    @Bean
    public SimpleInstanceRepository instanceRepository(JdbcTemplate jdbcTemplate){
        SimpleInstanceRepository instanceRepository = new SimpleInstanceRepository();
        instanceRepository.setJdbcTemplate(jdbcTemplate);
        return instanceRepository;
    }
}
