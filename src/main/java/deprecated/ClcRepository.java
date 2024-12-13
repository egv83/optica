package deprecated;

import com.ochobits.optica.athentication.entities.Clc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Deprecated
public interface ClcRepository extends JpaRepository<Clc, Void>, JpaSpecificationExecutor<Clc> {

}