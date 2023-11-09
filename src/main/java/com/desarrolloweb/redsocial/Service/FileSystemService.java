package com.desarrolloweb.redsocial.Service;

import com.desarrolloweb.redsocial.Entity.Photo;
import com.desarrolloweb.redsocial.Repository.PhotoRepository;
import com.desarrolloweb.redsocial.Repository.UserRepository;
import com.desarrolloweb.redsocial.Tools.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    String userNotFound = "Pendejo el usuario no existe";

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/fileUp")
    public ResponseEntity<HashMap<String, String>> UpFile(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("path") String path,
                                                          @RequestParam("server") String server) {
        response.clear();
        try {
            if (file.isEmpty()) {
                response.put("message", messageFailEmpty);
                return ResponseEntity.badRequest().body(response);
            } else {
                System.out.println(file);
                System.out.println("viene a subir image");
                    // Generar un nombre de archivo único
                    String nameFile = String.valueOf(new Encoding().SessionManager());
                    String pathOrigin = path + "/original";
                    String pathMedium = path + "/medium";
                    String pathThumbnail = path + "/thumbnail";

                    // Resuelve la ubicación de destino para la imagen original
                    Path targetLocationOriginal = Paths.get(pathOrigin).resolve(nameFile);

                    // Resuelve la ubicación de destino para la imagen de tamaño mediano
                    Path targetLocationMedium = Paths.get(pathMedium).resolve(nameFile);

                    // Resuelve la ubicación de destino para la imagen en miniatura
                    Path targetLocationThumbnail = Paths.get(pathThumbnail).resolve(nameFile);

                    // Guarda la imagen original en la ubicación de destino original
                    Files.copy(file.getInputStream(), targetLocationOriginal, StandardCopyOption.REPLACE_EXISTING);

                    // Carga la imagen original utilizando la biblioteca Pillow
                    BufferedImage originalImage = ImageIO.read(targetLocationOriginal.toFile());

                    // Redimensiona la imagen original a un tamaño mediano
                    BufferedImage mediumImage = resizeImage(originalImage, 800, 600);

                    // Redimensiona la imagen original a un tamaño de miniatura
                    BufferedImage thumbnailImage = resizeImage(originalImage, 100, 75);

                    // Guarda las imágenes redimensionadas en las ubicaciones correspondientes
                    ImageIO.write(mediumImage, "jpg", targetLocationMedium.toFile());
                    ImageIO.write(thumbnailImage, "jpg", targetLocationThumbnail.toFile());

                    Photo photo = new Photo();
                    photo.setIdPhoto(nameFile);
                    photo.setRoute(path);
                    photo.setIpServer(server);

                    photoRepository.save(photo);

                    response.put("idImagen", String.valueOf(nameFile));
                System.out.println(response);
                    return ResponseEntity.ok(response);

            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage() + "\nerror causa " + e.getCause());
            response.put("message", messageFail);
            return ResponseEntity.badRequest().body(response);
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }





}
