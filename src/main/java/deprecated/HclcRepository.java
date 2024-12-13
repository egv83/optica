package deprecated;

import com.ochobits.optica.athentication.entities.Hclc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Deprecated
public interface HclcRepository extends JpaRepository<Hclc, Void>, JpaSpecificationExecutor<Hclc> {

}