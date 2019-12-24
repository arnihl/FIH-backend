package is.tonlistarskolifih.TonlistarskoliFIH.Service;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.NewsStory;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.TempNewsStory;

import java.util.List;

public interface NewsStoryService {

    NewsStory save(NewsStory newsStory);
    List<NewsStory> findAll();
    NewsStory findById(long id);
    void deleteById(long id);
    TempNewsStory convertToTempNewsStory(NewsStory newsStory);
    NewsStory saveTempNewsStory(TempNewsStory newsStory);
    void updateStory(TempNewsStory newsStory, long id);
    // get 3 newest stories newest first;
    List<NewsStory> getNewestStories();
    // get all stories newest first
    List<NewsStory> getStoriesNewestFirst();
}
