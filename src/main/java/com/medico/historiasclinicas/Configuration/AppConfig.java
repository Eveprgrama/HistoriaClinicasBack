package com.medico.historiasclinicas.Configuration;

import com.medico.historiasclinicas.DTO.ActualizacionDTO;
import com.medico.historiasclinicas.DTO.ArchivoHistoriaClinicaDTO;
import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.DTO.MedicacionDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Entity.Medicacion;
import com.medico.historiasclinicas.Entity.Paciente;
import com.medico.historiasclinicas.Mapper.ActualizacionMapper;
import com.medico.historiasclinicas.Mapper.ArchivoHistoriaClinicaMapper;
import com.medico.historiasclinicas.Mapper.HistoriaClinicaMapper;
import com.medico.historiasclinicas.Mapper.MedicacionMapper;
import com.medico.historiasclinicas.Repository.HistoriaClinicaRepository;
import com.medico.historiasclinicas.Repository.PacienteRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Autowired
    MedicacionMapper medicacionMapper;

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
                dto.setPeso(historiaClinica.getPeso());
                dto.setAltura(historiaClinica.getAltura());
                dto.setIndicaciones(historiaClinica.getIndicaciones());
                dto.setFechaCreacion(historiaClinica.getFechaCreacion());

                // Mapea las actualizaciones si no es nulo
                if (historiaClinica.getActualizaciones() != null) {
                    List<ActualizacionDTO> actualizacionesDto = historiaClinica.getActualizaciones()
                            .stream()
                            .map(actualizacionMapper::toDto)
                            .collect(Collectors.toList());
                    dto.setActualizaciones(actualizacionesDto);
                }

                // Mapea los archivos si no es nulo
                if (historiaClinica.getArchivos() != null) {
                    List<ArchivoHistoriaClinicaDTO> archivosDto = historiaClinica.getArchivos()
                            .stream()
                            .map(archivoHistoriaClinicaMapper::toDto)
                            .collect(Collectors.toList());
                    dto.setArchivos(archivosDto);
                }

                // Mapea las medicaciones si no es nulo
                if (historiaClinica.getMedicacion() != null) {
                    List<MedicacionDTO> medicacionesDto = historiaClinica.getMedicacion()
                            .stream()
                            .map(medicacionMapper::toDto)
                            .collect(Collectors.toList());
                    dto.setMedicacion(medicacionesDto);
                }

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
                entity.setPeso(historiaClinicaDTO.getPeso());
                entity.setAltura(historiaClinicaDTO.getAltura());
                entity.setIndicaciones(historiaClinicaDTO.getIndicaciones());
                entity.setFechaCreacion(historiaClinicaDTO.getFechaCreacion());

                // Mapea las actualizaciones
                List<Actualizacion> actualizaciones = null;
                if (historiaClinicaDTO.getActualizaciones() != null) {
                    actualizaciones = historiaClinicaDTO.getActualizaciones()
                            .stream()
                            .map(actualizacionMapper::toEntity)
                            .collect(Collectors.toList());
                }
                entity.setActualizaciones(actualizaciones);

                // Mapea los archivos
                List<ArchivoHistoriaClinica> archivos = null;
                if (historiaClinicaDTO.getArchivos() != null) {
                    archivos = historiaClinicaDTO.getArchivos()
                            .stream()
                            .map(archivoHistoriaClinicaMapper::toEntity)
                            .collect(Collectors.toList());
                }
                entity.setArchivos(archivos);

                // Mapea las medicaciones
                List<Medicacion> medicaciones = null;
                if (historiaClinicaDTO.getMedicacion() != null) {
                    medicaciones = historiaClinicaDTO.getMedicacion()
                            .stream()
                            .map(medicacionMapper::toEntity)
                            .collect(Collectors.toList());
                }
                entity.setMedicacion(medicaciones);

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
                actualizacionDTO.setIndicaciones(actualizacion.getIndicaciones());
                actualizacionDTO.setMedicacion(actualizacion.getMedicacion());
                actualizacionDTO.setDroga(actualizacion.getDroga());
                actualizacionDTO.setDosis(actualizacion.getDosis());
                actualizacionDTO.setPeso(actualizacion.getPeso());
                actualizacionDTO.setAltura(actualizacion.getAltura());
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
                actualizacion.setIndicaciones(actualizacionDTO.getIndicaciones());
                actualizacion.setHistoriaClinica(getHistoriaClinicaFromId(actualizacionDTO.getHistoriaClinicaId()));
                actualizacion.setMedicacion(actualizacionDTO.getMedicacion());
                actualizacion.setDroga(actualizacionDTO.getDroga());
                actualizacion.setDosis(actualizacionDTO.getDosis());
                actualizacion.setPeso(actualizacionDTO.getPeso());
                actualizacion.setAltura(actualizacionDTO.getAltura());

                return actualizacion;
            }

            private HistoriaClinica getHistoriaClinicaFromId(Long id) {
                return historiaClinicaRepository.findById(id).orElse(null);
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
                archivoHistoriaClinicaDTO.setUrl(archivoHistoriaClinica.getUrl());
                archivoHistoriaClinicaDTO.setHistoriaClinicaId(archivoHistoriaClinica.getHistoriaClinica().getId());

                return archivoHistoriaClinicaDTO;
            }

            @Override
            public ArchivoHistoriaClinica toEntity(ArchivoHistoriaClinicaDTO archivoHistoriaClinicaDTO) {
                if (archivoHistoriaClinicaDTO == null) {
                    return null;
                }
                ArchivoHistoriaClinica archivoHistoriaClinica = new ArchivoHistoriaClinica();
                archivoHistoriaClinica.setNombre(archivoHistoriaClinicaDTO.getNombre());
                archivoHistoriaClinica.setTipo(archivoHistoriaClinicaDTO.getTipo());
                archivoHistoriaClinica.setUrl(archivoHistoriaClinicaDTO.getUrl());
                archivoHistoriaClinica.setHistoriaClinica(getHistoriaClinicaFromId(archivoHistoriaClinicaDTO.getHistoriaClinicaId()));

                return archivoHistoriaClinica;
            }

            private HistoriaClinica getHistoriaClinicaFromId(Long id) {
                return historiaClinicaRepository.findById(id).orElse(null);
            }
        };
    }

    @Bean
    public MedicacionMapper medicacionMapper() {
        return new MedicacionMapper() {
            @Override
            public MedicacionDTO toDto(Medicacion medicacion) {
                if (medicacion == null) {
                    return null;
                }

                MedicacionDTO dto = new MedicacionDTO(medicacion);
                dto.setNombreMedicacion(medicacion.getNombreMedicacion());
                dto.setDroga(medicacion.getDroga());
                dto.setDosis(medicacion.getDosis());

                return dto;
            }

            @Override
            public Medicacion toEntity(MedicacionDTO medicacionDTO) {
                if (medicacionDTO == null) {
                    return null;
                }

                Medicacion entity = new Medicacion();
                entity.setNombreMedicacion(medicacionDTO.getNombreMedicacion());
                entity.setDroga(medicacionDTO.getDroga());
                entity.setDosis(medicacionDTO.getDosis());

                return entity;
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
