package is.tonlistarskolifih.TonlistarskoliFIH.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadFile(MultipartFile file);
    // bæta við deletefile
}
