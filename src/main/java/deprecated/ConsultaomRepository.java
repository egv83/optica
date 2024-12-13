package deprecated;

import com.ochobits.optica.athentication.entities.Consultaom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Deprecated
public interface ConsultaomRepository extends JpaRepository<Consultaom, Void>, JpaSpecificationExecutor<Consultaom> {

}