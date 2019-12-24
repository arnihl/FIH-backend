package is.tonlistarskolifih.TonlistarskoliFIH.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class NewsStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Lob
    private String content;
    private String tag;
    private String imgRef;
    private Date timestamp;

    public NewsStory(){

    }

    public NewsStory(String title, String content, String tag, String imgRef) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.imgRef = imgRef;
        this.timestamp = new Date(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
