package com.ochobits.optica.medicalRecord.service;

import com.ochobits.optica.medicalRecord.dto.MedicalRecordDTO;
import com.ochobits.optica.medicalRecord.dto.MedicalRecordMapper;
import com.ochobits.optica.medicalRecord.repository.JpaMedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateMedicalRecordService {

    private final JpaMedicalRecordRepository jpaMedicalRecordRepository;

    public CreateMedicalRecordService(JpaMedicalRecordRepository jpaMedicalRecordRepository) {
        this.jpaMedicalRecordRepository = jpaMedicalRecordRepository;
    }

    public MedicalRecordDTO save(MedicalRecordDTO dto){

        if(dto.nombre().isBlank()){
            throw new RuntimeException("Por favor ingrese el nombre");
        }

        if(dto.primerApellido().isBlank()){
            throw new RuntimeException("Por favor ingrese el primer apellido");
        }

        if(dto.segundoApellido().isBlank()){
            throw new RuntimeException("Por favor ingrese el segundo apellido");
        }

        if(dto.ciudad().isBlank()){
            throw new RuntimeException("Por favor ingrese la ciudad");
        }

        if(dto.edad().isBlank()){
            throw new RuntimeException("Por favor ingrese la edad");
        }

        if(dto.ocupacion().isBlank()){
            throw new RuntimeException("Por favor ingrese la ocupación");
        }

        var medicalRecordOpt = jpaMedicalRecordRepository.findByNombreAndPrimerApellidoAndSegundoApellido(
                dto.nombre(),dto.primerApellido(), dto.segundoApellido()
        );

        if(medicalRecordOpt.isPresent()){
            throw new RuntimeException("En pasiente ya esta registrado");
        }

        return MedicalRecordMapper.toDTO(jpaMedicalRecordRepository.save(MedicalRecordMapper.toEntity(dto)));

    }

    public MedicalRecordDTO update(Long id, MedicalRecordDTO dto){

        var medicalRecordOpt = jpaMedicalRecordRepository.findById(id);
        if(medicalRecordOpt.isEmpty()){
            throw new RuntimeException("La historia clinica no existe con es nuero: "+id);
        }

        return MedicalRecordMapper.toDTO(jpaMedicalRecordRepository.save(MedicalRecordMapper.updateToEntity(
                medicalRecordOpt.get(), dto
        )));

    }

    public void delete(Long id){

        var medicalRecordOpt = jpaMedicalRecordRepository.findById(id);
        if(medicalRecordOpt.isEmpty()){
            throw new RuntimeException("La historia clinica no existe con es nuero: "+id);
        }

        jpaMedicalRecordRepository.delete(medicalRecordOpt.get());

    }

}
