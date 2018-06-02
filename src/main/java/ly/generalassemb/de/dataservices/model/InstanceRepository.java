package ly.generalassemb.de.dataservices.model;

import java.util.Optional;

public interface InstanceRepository {
        Optional<Instance> getById(String id);
}
