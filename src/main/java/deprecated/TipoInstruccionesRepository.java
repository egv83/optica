package deprecated;

import com.ochobits.optica.athentication.entities.TipoInstrucciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Deprecated
public interface TipoInstruccionesRepository extends JpaRepository<TipoInstrucciones, Void>, JpaSpecificationExecutor<TipoInstrucciones> {

}