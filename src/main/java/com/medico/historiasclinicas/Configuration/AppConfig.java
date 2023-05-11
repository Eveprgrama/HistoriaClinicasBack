package com.medico.historiasclinicas.Configuration;

import com.medico.historiasclinicas.DTO.ActualizacionDTO;
import com.medico.historiasclinicas.DTO.ArchivoHistoriaClinicaDTO;
import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Entity.Paciente;
import com.medico.historiasclinicas.Mapper.ActualizacionMapper;
import com.medico.historiasclinicas.Mapper.ArchivoHistoriaClinicaMapper;
import com.medico.historiasclinicas.Mapper.HistoriaClinicaMapper;
import com.medico.historiasclinicas.Repository.HistoriaClinicaRepository;
import com.medico.historiasclinicas.Repository.PacienteRepository;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  
    @Autowired
    ActualizacionMapper actualizacionMapper;

    @Autowired
    ArchivoHistoriaClinicaMapper archivoHistoriaClinicaMapper;
    
    @Autowired
    PacienteRepository pacienteRepository;
    
    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;
    
    @Bean
    public HistoriaClinicaMapper historiaClinicaMapper() {
        return new HistoriaClinicaMapper() {
            @Override
            public HistoriaClinicaDTO toDto(HistoriaClinica historiaClinica) {
                if (historiaClinica == null) {
                    return null;
                }

                HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
                dto.setPacienteId(historiaClinica.getPaciente().getId());
                dto.setEnfermedad(historiaClinica.getEnfermedad());
                dto.setDescripcion(historiaClinica.getDescripcion());
                dto.setMedicacion(historiaClinica.getMedicacion());
                dto.setDroga(historiaClinica.getDroga());
                dto.setDosis(historiaClinica.getDósis());
                dto.setPeso(historiaClinica.getPeso());
                dto.setAltura(historiaClinica.getAltura());
                dto.setIndicaciones(historiaClinica.getIndicaciones());

                // Mapea las actualizaciones
                List<ActualizacionDTO> actualizacionesDto = historiaClinica.getActualizaciones()
                        .stream()
                        .map(actualizacionMapper::toDto)
                        .collect(Collectors.toList());
                dto.setActualizaciones(actualizacionesDto);

                // Mapea los archivos
                List<ArchivoHistoriaClinicaDTO> archivosDto = historiaClinica.getArchivos()
                        .stream()
                        .map(archivoHistoriaClinicaMapper::toDto)
                        .collect(Collectors.toList());
                dto.setArchivos(archivosDto);

                return dto;
            }

            @Override
            public HistoriaClinica toEntity(HistoriaClinicaDTO historiaClinicaDTO) {
                if (historiaClinicaDTO == null) {
                    return null;
                }

                HistoriaClinica entity = new HistoriaClinica();
                // Necesitarás tener un método para obtener el Paciente desde el pacienteId
                entity.setPaciente(getPacienteFromId(historiaClinicaDTO.getPacienteId()));
                entity.setEnfermedad(historiaClinicaDTO.getEnfermedad());
                entity.setDescripcion(historiaClinicaDTO.getDescripcion());
                entity.setMedicacion(historiaClinicaDTO.getMedicacion());
                entity.setDroga(historiaClinicaDTO.getDroga());
                entity.setDósis(historiaClinicaDTO.getDosis());
                entity.setPeso(historiaClinicaDTO.getPeso());
                entity.setAltura(historiaClinicaDTO.getAltura());
                entity.setIndicaciones(historiaClinicaDTO.getIndicaciones());

                // Mapea las actualizaciones
                List<Actualizacion> actualizaciones = historiaClinicaDTO.getActualizaciones()
                        .stream()
                        .map(actualizacionMapper::toEntity)
                        .collect(Collectors.toList());
                entity.setActualizaciones(actualizaciones);

                // Mapea los archivos
                List<ArchivoHistoriaClinica> archivos = historiaClinicaDTO.getArchivos()
                        .stream()
                        .map(archivoHistoriaClinicaMapper::toEntity)
                        .collect(Collectors.toList());
                entity.setArchivos(archivos);

                return entity;
            }
            
            private Paciente getPacienteFromId(Long id) {
                return pacienteRepository.findById(id).orElse(null);
            }
        };
    }
            
       @Bean
    public ActualizacionMapper actualizacionMapper() {
        return new ActualizacionMapper() {

            @Override
            public ActualizacionDTO toDto(Actualizacion actualizacion) {
                if (actualizacion == null) {
                    return null;
                }
                ActualizacionDTO actualizacionDTO = new ActualizacionDTO();
                actualizacionDTO.setId(actualizacion.getId());
                actualizacionDTO.setFecha(actualizacion.getFecha());
                actualizacionDTO.setDescripcion(actualizacion.getDescripcion());
                actualizacionDTO.setHistoriaClinicaId(actualizacion.getHistoriaClinica().getId());

                return actualizacionDTO;
            }

            @Override
            public Actualizacion toEntity(ActualizacionDTO actualizacionDTO) {
                if (actualizacionDTO == null) {
                    return null;
                }
                Actualizacion actualizacion = new Actualizacion();
                actualizacion.setId(actualizacionDTO.getId());
                actualizacion.setFecha(actualizacionDTO.getFecha());
                actualizacion.setDescripcion(actualizacionDTO.getDescripcion());
                actualizacion.setHistoriaClinica(getHistoriaClinicaFromId(actualizacionDTO.getHistoriaClinicaId()));

                return actualizacion;
            }

            private HistoriaClinica getHistoriaClinicaFromId(Long id) {
                 historiaClinicaRepository.findById(id).orElse(null);
                throw new UnsupportedOperationException("Implementar la búsqueda de HistoriaClinica por ID.");
            }
        };
    }

    @Bean
    public ArchivoHistoriaClinicaMapper archivoHistoriaClinicaMapper() {
        return new ArchivoHistoriaClinicaMapper() {
            @Override
            public ArchivoHistoriaClinicaDTO toDto(ArchivoHistoriaClinica archivoHistoriaClinica) {
                if (archivoHistoriaClinica == null) {
                    return null;
                }
                ArchivoHistoriaClinicaDTO archivoHistoriaClinicaDTO = new ArchivoHistoriaClinicaDTO();
                archivoHistoriaClinicaDTO.setId(archivoHistoriaClinica.getId());
                archivoHistoriaClinicaDTO.setNombre(archivoHistoriaClinica.getNombre());
                archivoHistoriaClinicaDTO.setTipo(archivoHistoriaClinica.getTipo());
                // Aquí asumimos que convertimos el archivo byte[] a un String, necesitarás adaptar esto si es incorrecto
                archivoHistoriaClinicaDTO.setArchivo(Base64.getEncoder().encodeToString(archivoHistoriaClinica.getArchivo()));
                archivoHistoriaClinicaDTO.setHistoriaClinicaId(archivoHistoriaClinica.getHistoriaClinica().getId());

                return archivoHistoriaClinicaDTO;
            }

            @Override
            public ArchivoHistoriaClinica toEntity(ArchivoHistoriaClinicaDTO archivoHistoriaClinicaDTO) {
                if (archivoHistoriaClinicaDTO == null) {
                    return null;
                }
                ArchivoHistoriaClinica archivoHistoriaClinica = new ArchivoHistoriaClinica();
                archivoHistoriaClinica.setId(archivoHistoriaClinicaDTO.getId());
                archivoHistoriaClinica.setNombre(archivoHistoriaClinicaDTO.getNombre());
                                archivoHistoriaClinica.setTipo(archivoHistoriaClinicaDTO.getTipo());
                // Aquí asumimos que convertimos un String a un archivo byte[], necesitarás adaptar esto si es incorrecto
                archivoHistoriaClinica.setArchivo(Base64.getDecoder().decode(archivoHistoriaClinicaDTO.getArchivo()));
                archivoHistoriaClinica.setHistoriaClinica(getHistoriaClinicaFromId(archivoHistoriaClinicaDTO.getHistoriaClinicaId()));

                return archivoHistoriaClinica;
            }

            private HistoriaClinica getHistoriaClinicaFromId(Long id) {
                 return historiaClinicaRepository.findById(id).orElse(null);
            }
        };
    }

}
    

