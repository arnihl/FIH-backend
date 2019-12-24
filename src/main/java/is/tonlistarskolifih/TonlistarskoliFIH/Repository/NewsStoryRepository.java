package is.tonlistarskolifih.TonlistarskoliFIH.Repository;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.NewsStory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsStoryRepository extends JpaRepository<NewsStory, Long> {
    NewsStory save(NewsStory newsStory);
    List<NewsStory> findAll();
    NewsStory findById(long id);
    void deleteById(long id);
}
