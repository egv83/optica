package deprecated;

import com.ochobits.optica.athentication.entities.DetalleInstrucciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Deprecated
public interface DetalleInstruccionesRepository extends JpaRepository<DetalleInstrucciones, Void>, JpaSpecificationExecutor<DetalleInstrucciones> {

}