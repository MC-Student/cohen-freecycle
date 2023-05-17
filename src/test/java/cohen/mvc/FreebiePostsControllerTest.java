package cohen.mvc;

import cohen.FreebieService;
import cohen.json.PostListInfo;
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
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void refreshPosts()
    {
        //given
        FreebieService service = mock();
        JTextArea postList = mock();
        FreebiePostsController controller = new FreebiePostsController(service, postList);
        PostListInfo postListInfo = mock();
        Observable<PostListInfo> observableList = Observable.just(postListInfo);
        doReturn(observableList).when(service).getPostList("40.776676", "-73.971321");

        //when
        controller.refreshPosts();

        //then
        verify(service).getPostList("40.776676", "-73.971321");
    }
}