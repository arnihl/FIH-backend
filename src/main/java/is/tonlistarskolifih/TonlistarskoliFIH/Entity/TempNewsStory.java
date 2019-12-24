package is.tonlistarskolifih.TonlistarskoliFIH.Entity;

import org.springframework.web.multipart.MultipartFile;

public class TempNewsStory {

    private String Title;
    private String content;
    private String Tag;
    private MultipartFile img;

    public TempNewsStory(){

    }

    public TempNewsStory(String title, String content, String tag, MultipartFile img) {
        Title = title;
        this.content = content;
        Tag = tag;
        this.img = img;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
