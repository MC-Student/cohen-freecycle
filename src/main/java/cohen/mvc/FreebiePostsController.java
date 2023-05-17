package cohen.mvc;

import cohen.FreebieService;
import cohen.json.PostListInfo;
import cohen.json.Post;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.util.List;

public class FreebiePostsController
{
    private JList<String> postTitles;
    private FreebieService service;

    @Inject
    public FreebiePostsController(FreebieService service,
                                  @Named("postTitles") JList<String> postTitles)
    {
        this.service = service;
        this.postTitles = postTitles;
    }

    public void refreshPosts(String lat, String lon)
    {
        service.getPostList(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(this::setPostList, Throwable::printStackTrace);
    }

    public void setPostList(PostListInfo postListInfo)
    {
        List<Post> origPosts = postListInfo.getPosts();

        postTitles.setListData((String[]) null);

        if (origPosts.isEmpty())
        {
            postTitles.add(new JLabel("Empty"));
        }
        else
        {
            String[] titlesArray = new String[origPosts.size()];

            for (int i = 0; i < titlesArray.length; i++)
            {
                titlesArray[i] = origPosts.get(i).getTitle();
            }

            postTitles.setListData(titlesArray);
        }
    }
}