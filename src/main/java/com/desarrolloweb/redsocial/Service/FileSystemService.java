package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.Photo;
import com.desarrolloweb.redsocial.Repository.PhotoRepository;
import com.desarrolloweb.redsocial.Tools.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    PhotoRepository photoRepository;

    @PostMapping("/fileUp")
    public ResponseEntity<HashMap<String, String>> UpFile(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("path") String path,
                                                          @RequestParam("user") String user,
                                                          @RequestParam("server") String server) {
        response.clear();
        try {
            if (file.isEmpty()) {
                response.put("message", messageFailEmpty);
                return ResponseEntity.badRequest().body(response);
            } else {
                // Generar un nombre de archivo único
               // String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
                String nameFile = String.valueOf(new Encoding().SessionManager());
                Path targetLocation = Paths.get(path).resolve(nameFile);

                Photo photo = new Photo();
                photo.setIdPhoto(nameFile);
                photo.setRoute(path);
                photo.setIpServer(server);
                photo.setIdUser(user);
                photoRepository.save(photo);
                // Guardar el archivo en el directorio de almacenamiento
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                response.put("ruta", String.valueOf(targetLocation));
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage() + "\nerror causa " + e.getCause());
            response.put("message", messageFail);
            return ResponseEntity.badRequest().body(response);
        }
    }




}
