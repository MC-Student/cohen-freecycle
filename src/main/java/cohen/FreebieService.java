package cohen;

import cohen.json.PostListInfo;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface FreebieService
{
    @GET("api/v1.3/posts?sort_by=date&types=offer&sources=open_archive_groups&" +
            "per_page=20&page=1&device_pixel_ratio=1&latitude=40.776676&" +
            "longitude=-73.971321&radius=21000&date_min=2023-05-01T18%3A30%3A16&" +
            "include_reposts=0&api_key=Q7gR3Ez9sBuwPS7OEMuHy6rP1TFWP29ylI5wghNC")
    Observable<PostListInfo> getPostList();
}