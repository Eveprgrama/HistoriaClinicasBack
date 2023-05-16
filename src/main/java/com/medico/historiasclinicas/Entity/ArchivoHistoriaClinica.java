
package com.medico.historiasclinicas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "archivos_historia_clinica")
@Getter @Setter
public class ArchivoHistoriaClinica {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private String tipo;
   
    private String url;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_clinica_id", nullable = false)
    private HistoriaClinica historiaClinica;

    public ArchivoHistoriaClinica() {
    }

    public ArchivoHistoriaClinica(Long id, String nombre, String tipo, String url, HistoriaClinica historiaClinica) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.url = url;
        this.historiaClinica = historiaClinica;
    }
    
    
    public byte[] getBytes() throws IOException {
        Path rutaArchivo = Paths.get("ruta/a/tu/carpeta/de/archivos/" + url);
        return Files.readAllBytes(rutaArchivo);
    }
}
