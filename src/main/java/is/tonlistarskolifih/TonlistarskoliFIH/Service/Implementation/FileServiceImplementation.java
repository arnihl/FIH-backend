package is.tonlistarskolifih.TonlistarskoliFIH.Service.Implementation;

import is.tonlistarskolifih.TonlistarskoliFIH.Exception.FileStorageException;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImplementation implements FileService {

    @Value("${app.upload.image.dir}")
    public String uploadDir;

    @Override
    public void uploadFile(MultipartFile file) {
        System.out.println(uploadDir);
        try {
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e){
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}
