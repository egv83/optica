package deprecated;

import com.ochobits.optica.athentication.entities.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Deprecated
public interface OperadorRepository extends JpaRepository<Operador, Void>, JpaSpecificationExecutor<Operador> {

}