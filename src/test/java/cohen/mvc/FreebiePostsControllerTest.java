package cohen.mvc;

import cohen.FreebieService;
import cohen.json.Post;
import cohen.json.PostListInfo;
import hu.akarnokd.rxjava3.swing.RxSwingPlugins;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class FreebiePostsControllerTest
{
    static
    {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxSwingPlugins.setOnEdtScheduler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void refreshPosts()
    {
        //given
        FreebieService service = mock();
        JList<String> posts = mock();
        FreebiePostsController controller = new FreebiePostsController(service, posts);
        PostListInfo postListInfo = mock();
        Observable<PostListInfo> observableList = Observable.just(postListInfo);
        doReturn(observableList).when(service).getPostList("40.776676", "-73.971321");

        //when
        controller.refreshPosts("40.776676", "-73.971321");

        //then
        verify(service).getPostList("40.776676", "-73.971321");
    }
}