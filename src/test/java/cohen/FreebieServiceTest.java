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

        //when
        PostListInfo postListInfo = service.getPostList("40.776676",
                "-73.971321",
                "2023-05-01T18%3A30%3A16"
        ).blockingFirst();
        //then
        assertNotNull(postListInfo);
        assertNotEquals(0, postListInfo.getPosts().size());
    }
}