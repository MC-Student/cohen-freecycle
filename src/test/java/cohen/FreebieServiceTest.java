package cohen;

import cohen.json.PostListInfo;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.*;

public class FreebieServiceTest
{

    @Test
    public void getPostList()
    {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trashnothing.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        FreebieService service = retrofit.create(FreebieService.class);

        //latitude=40.776676 longitude=-73.971321
        //when
        PostListInfo postListInfo = service.getPostList("40.776676","-73.971321").blockingFirst();
        //then
        assertNotNull(postListInfo);
        assertNotEquals(0, postListInfo.getPosts().size());
    }
}