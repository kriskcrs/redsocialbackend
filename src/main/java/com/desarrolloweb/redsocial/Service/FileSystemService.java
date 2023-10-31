package com.desarrolloweb.redsocial.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

@RestController
@RequestMapping("v1")
@CrossOrigin
public class FileSystemService {

    //vars
    HashMap<String, String> response = new HashMap<>();
    String messageFail = "Error al agregar archivo, revise la ruta";
    String messageFailEmpty = "Carga de archivo fallida. Por favor selecciona un archivo";

    @PostMapping("/fileUp")
    public ResponseEntity<HashMap<String,String>> UpFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        response.clear();
        try {
            if (file.isEmpty()) {
                response.put("message",messageFailEmpty);
                return ResponseEntity.badRequest().body(response);
            } else {
                // Generar un nombre de archivo único
                String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
                Path targetLocation = Paths.get(path).resolve(nameFile);
                // Guardar el archivo en el directorio de almacenamiento
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                response.put("message","ruta: " + targetLocation);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage() +"\nerror causa " + e.getCause());
            response.put("message",messageFail);
            return ResponseEntity.badRequest().body(response);
        }
    }



    @PostMapping("/fileDown")
    private ResponseEntity<Resource> DownFile(@RequestParam  String file,@RequestParam String path){

        Path routeFile = Paths.get(path).resolve(file);
        System.out.println(routeFile);
        try {
            // Cargar el archivo como recurso
            Resource recurso = new UrlResource(routeFile.toUri());

            // Verificar si el archivo existe y es legible
            if (recurso.exists() && recurso.isReadable()) {
                // Devolver el archivo como respuesta HTTP
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                        .body(recurso);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }


    }


}
