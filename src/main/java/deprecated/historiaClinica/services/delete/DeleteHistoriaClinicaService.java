package deprecated.historiaClinica.services.delete;

import deprecated.historiaClinica.exceptions.HistoriaClinicaException;
import deprecated.historiaClinica.repository.JpaHistoriaClinicaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteHistoriaClinicaService {
    private final JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository;

    public DeleteHistoriaClinicaService(JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository) {
        this.jpaHistoriaClinicaRepository = jpaHistoriaClinicaRepository;
    }

    public void execute(Long id){
        var historiaOPT = jpaHistoriaClinicaRepository.findById(id);
        if(historiaOPT.isEmpty()){
            throw new HistoriaClinicaException("NO existe la Historiaclinica con ese número: "+id);
        }

        try{
            jpaHistoriaClinicaRepository.delete(historiaOPT.get());
        } catch (Exception e) {
            throw new HistoriaClinicaException("Error al eliminar la historia clinica: "+e.getMessage());
        }

    }
}
