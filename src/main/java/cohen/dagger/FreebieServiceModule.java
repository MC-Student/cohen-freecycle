package cohen.dagger;

import cohen.FreebieService;
import cohen.json.Post;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.*;
import java.util.*;

@Module
public class FreebieServiceModule
{
    @Provides
    public FreebieService providesFreebieService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trashnothing.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        FreebieService service = retrofit.create(FreebieService.class);
        return service;
    }

    @Provides
    @Named("postTitles")
    @Singleton
    public JList<String> providesPostTitles()
    {
        return new JList<>();
    }

    @Provides
    @Named("allPosts")
    @Singleton
    public ArrayList<Post> providesAllPosts()
    {
        return new ArrayList<>();
    }

    @Provides
    @Named("title")
    @Singleton
    public JLabel providesTitle()
    {
        return new JLabel();
    }

    @Provides
    @Named("description")
    @Singleton
    public JTextArea providesDescription()
    {
        return new JTextArea();
    }

    @Provides
    @Named("photo")
    @Singleton
    public JLabel providesPhoto()
    {
        return new JLabel();
    }
}