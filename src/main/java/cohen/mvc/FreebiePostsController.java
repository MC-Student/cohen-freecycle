package cohen.mvc;

import cohen.FreebieService;
import cohen.json.PostListInfo;
import cohen.json.Post;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.util.ArrayList;

public class FreebiePostsController
{
    private final JList<String> postTitles;
    private final FreebieService service;
    private final JLabel title;
    private final JLabel description;
    private ArrayList<Post> allPosts;

    @Inject
    public FreebiePostsController(FreebieService service,
                                  @Named("title") JLabel title,
                                  @Named("description") JLabel description,
                                  @Named("postTitles") JList<String> postTitles)
    {
        this.service = service;
        this.title = title;
        this.description = description;
        this.postTitles = postTitles;
    }

    public void refreshPosts(String lat, String lon, String date)
    {
        service.getPostList(lat, lon, date)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(this::setPosts, Throwable::printStackTrace);
    }

    public void setPosts(PostListInfo postListInfo)
    {
        allPosts = postListInfo.getPosts();

        if (allPosts.isEmpty())
        {
            postTitles.add(new JLabel("Empty"));
        }

        else
        {
            String[] titlesArray = new String[allPosts.size()];

            for (int i = 0; i < titlesArray.length; i++)
            {
                titlesArray[i] = allPosts.get(i).getTitle();
            }

            postTitles.setListData(titlesArray);

            title.setText(allPosts.get(0).getTitle());
            description.setText(allPosts.get(0).getContent());

        }
    }

    public void updatePost(int postSelected)
    {
        title.setText(allPosts.get(postSelected).getTitle());
        description.setText(allPosts.get(postSelected).getContent());
    }
}