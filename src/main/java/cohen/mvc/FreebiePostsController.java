package cohen.mvc;

import cohen.FreebieService;
import cohen.json.PostListInfo;
import cohen.json.Post;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class FreebiePostsController
{
    private final JList<String> postTitles;
    private final FreebieService service;
    private final JTextArea title;
    private final JTextArea description;

    private final JLabel photo;
    private ArrayList<Post> allPosts;

    private String photoUrl;

    @Inject
    public FreebiePostsController(FreebieService service,
                                  @Named("title") JTextArea title,
                                  @Named("description") JTextArea description,
                                  @Named("photo") JLabel photo,
                                  @Named("postTitles") JList<String> postTitles)
    {
        this.service = service;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postTitles = postTitles;
    }

    public void refreshPosts(String lat, String lon, String date)
    {
        service.getPostList(lat, lon, date)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(this::setPosts, Throwable::printStackTrace);
    }

    public void setPosts(PostListInfo postListInfo) throws MalformedURLException
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
            if (allPosts.get(0).getPhotos() != null)
            {
                photoUrl = allPosts.get(0).getPhotos().get(0).getUrl();
                photo.setIcon(new ImageIcon(new URL(photoUrl)));
            }
        }
    }

    public void updatePost(int postSelected) throws MalformedURLException
    {
        Post current = allPosts.get(postSelected);
        title.setText(current.getTitle());
        description.setText(current.getContent());
        photoUrl = null;
        photo.setIcon(null);
        photo.setText(null);
        if (current.getPhotos() != null)
        {
            photoUrl = current.getPhotos().get(0).getUrl();
            photo.setIcon(new ImageIcon(new URL(photoUrl)));
        }
        else
        {
            photo.setText("** no photos available **");
        }
    }

    public void openPhotoInBrowser() throws URISyntaxException, IOException
    {
        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE))
            {
                desktop.browse(new URI(photoUrl));
            }
        }
    }
}