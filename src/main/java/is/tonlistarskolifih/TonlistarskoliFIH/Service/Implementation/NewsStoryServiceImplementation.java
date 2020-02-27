package is.tonlistarskolifih.TonlistarskoliFIH.Service.Implementation;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.NewsStory;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.TempNewsStory;
import is.tonlistarskolifih.TonlistarskoliFIH.Repository.NewsStoryRepository;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.FileService;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.NewsStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NewsStoryServiceImplementation implements NewsStoryService {
    NewsStoryRepository repository;
    FileService fileService;

    @Autowired
    public NewsStoryServiceImplementation(NewsStoryRepository repository, FileService fileService) {
        this.repository = repository;
        this.fileService = fileService;
    }

    @Override
    public NewsStory save(NewsStory newsStory) {
        return repository.save(newsStory);
    }

    @Override
    public List<NewsStory> findAll() {
        List<NewsStory> stories = repository.findAll();
        if(stories.isEmpty()) return null;

        Collections.sort(stories, new Comparator<NewsStory>() {
            @Override
            public int compare(NewsStory o1, NewsStory o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });

        Collections.reverse(stories);
        if(stories.size()>=3) {
            stories = stories.subList(0, 3);
        }
        for(NewsStory story : stories){
            String[] s = story.getContent().split(" ");
            String b = "";
            for(int i = 0; i < 15; i++){
                if(i>=s.length) break;
                b+= s[i] + " ";
            }
            b+= "...";
            story.setContent(b);
            if(story.getImgRef().equals("")){
                story.setImgRef("piano.jpg");
            }
        }
        return stories;
    }

    @Override
    public NewsStory findById(long id) {
        NewsStory story = repository.findById(id);
        if(story.getImgRef().equals("")){
            story.setImgRef("piano.jpg");
        }
        return story;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public NewsStory saveTempNewsStory(TempNewsStory newsStory) {
        NewsStory realStory = new NewsStory(newsStory.getTitle(), newsStory.getContent(), newsStory.getTag(), newsStory.getImg().getOriginalFilename());
        return save(realStory);
    }

    @Override
    public TempNewsStory convertToTempNewsStory(NewsStory newsStory) {
        TempNewsStory tempStory = new TempNewsStory(newsStory.getTitle(), newsStory.getContent(), newsStory.getTag(), null);
        return tempStory;
    }

    @Override
    public void updateStory(TempNewsStory newsStory, long id) {
        NewsStory realStory = findById(id);
        realStory.setContent(newsStory.getContent());
        realStory.setTitle(newsStory.getTitle());
        realStory.setTag(newsStory.getTag());
        if(!newsStory.getImg().isEmpty()) {
            fileService.uploadFile(newsStory.getImg(), "f");
            realStory.setImgRef(newsStory.getImg().getOriginalFilename());
        }
        save(realStory);
    }

    @Override
    public List<NewsStory> getNewestStories() {
        List<NewsStory> stories = repository.findAll();
        if(stories.isEmpty()) return null;

        Collections.sort(stories, new Comparator<NewsStory>() {
            @Override
            public int compare(NewsStory o1, NewsStory o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });

        Collections.reverse(stories);
        if(stories.size()>=3) {
            stories = stories.subList(0, 3);
        }
        for(NewsStory story : stories){
            if(story.getContent().length()>100){
                story.setContent(story.getContent().substring(0,98) + "...");
            }
            if(story.getImgRef().equals("")){
                story.setImgRef("piano.jpg");
            }
        }

        return stories;

    }

    @Override
    public List<NewsStory> getStoriesNewestFirst() {
        List<NewsStory> stories = repository.findAll();
        if(stories.isEmpty()) return null;

        Collections.sort(stories, new Comparator<NewsStory>() {
            @Override
            public int compare(NewsStory o1, NewsStory o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });

        for(NewsStory story : stories){
            if(story.getImgRef().equals("")){
                story.setImgRef("piano.jpg");
            }
        }

        Collections.reverse(stories);
        return stories;

    }
}
