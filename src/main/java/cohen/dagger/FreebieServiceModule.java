package cohen.dagger;

import cohen.FreebieService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.*;

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
    @Named("postList")
    @Singleton
    public JTextArea providesPostList()
    {
        return new JTextArea();
    }
}