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
    private JList<String> postTitles;
    private FreebieService service;
    private JLabel title;
    private JLabel description;
    private ArrayList<Post> allPosts;

    @Inject
    public FreebiePostsController(FreebieService service,
                                  @Named("title") JLabel title,
                                  @Named("description") JLabel description,
                                  @Named("postTitles") JList<String> postTitles,
                                  @Named("allPosts") ArrayList<Post> allPosts)
    {
        this.service = service;
        this.title = title;
        this.description = description;
        this.postTitles = postTitles;
        this.allPosts = allPosts;
    }

    public void refreshPosts(String lat, String lon)
    {
        service.getPostList(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(this::setPosts, Throwable::printStackTrace);
    }

    public void setPosts(PostListInfo postListInfo)
    {
        allPosts = (ArrayList<Post>) postListInfo.getPosts();

        postTitles.setListData((String[]) null);

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
}