package cohen.mvc;

import cohen.FreebieService;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;

public class FreebiePostsController
{
    private FreebieService service;
    private FreebiePostsView view;

    @Inject
    public FreebiePostsController(FreebieService service, FreebiePostsView view)
    {
        this.service = service;
        this.view = view;
    }

    public void refreshPosts()
    {
        service.getPostList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(view::setPosts, Throwable::printStackTrace);
    }
}