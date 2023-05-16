package com.medico.historiasclinicas.Cloud;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class CloudStorageService {
        private final S3Client s3Client;
    private final String bucketName = "mi-bucket"; // reemplaza con el nombre de tu bucket

    public CloudStorageService() {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1) // reemplaza con tu región
                .build();
    }

    public String subirArchivo(String nombreArchivo, byte[] contenido) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(nombreArchivo)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(contenido));

        return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(nombreArchivo)).toString();
    }

    public byte[] descargarArchivo(String nombreArchivo) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(nombreArchivo)
                .build();

        return s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray();
    }
    
    //Elimina el archivo de la nube
    public void eliminarArchivo(String nombreArchivo) {
    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(nombreArchivo)
            .build();

    s3Client.deleteObject(deleteObjectRequest);
}
}
