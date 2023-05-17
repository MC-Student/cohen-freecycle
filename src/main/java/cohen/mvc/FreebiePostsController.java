package cohen.mvc;

import cohen.FreebieService;
import cohen.json.PostListInfo;
import cohen.json.Post;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.util.List;

public class FreebiePostsController
{
    private JTextArea postList;
    private FreebieService service;

    @Inject
    public FreebiePostsController(FreebieService service,
                                  @Named("postList") JTextArea postList)
    {
        this.service = service;
        this.postList = postList;
    }

    public void refreshPosts(String lat, String lon)
    {
        service.getPostList(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::setPostList, Throwable::printStackTrace);
    }

    public void setPostList(PostListInfo postListInfo)
    {
        List<Post> posts = postListInfo.getPosts();
        if (posts.isEmpty())
        {
            postList.removeAll();
            postList.append("There are no posts in this area. Try a different location!");
        }
        else
        {
            for (Post post : posts)
            {
                String description = post.getContent();
                postList.append(description + "\n");
            }
        }
    }
}