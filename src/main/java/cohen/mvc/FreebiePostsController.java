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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class FreebiePostsController
{
    private final JList<String> postTitles;
    private final FreebieService service;
    private final JLabel title;
    private final JTextArea description;

    private final JLabel photo;
    private ArrayList<Post> allPosts;

    @Inject
    public FreebiePostsController(FreebieService service,
                                  @Named("title") JLabel title,
                                  @Named("description") JTextArea description,
                                  @Named("postTitles") JList<String> postTitles,
                                  @Named("photo") JLabel photo)
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
            setPhoto(0);
        }
    }

    public void updatePost(int postSelected) throws MalformedURLException
    {
        title.setText(allPosts.get(postSelected).getTitle());
        description.setText(allPosts.get(postSelected).getContent());
        photo.setIcon(setPhoto(postSelected));
    }

    public ImageIcon setPhoto(int index) throws MalformedURLException
    {
        String url = allPosts.get(index).getPhotos().get(index).getUrl();
        ImageIcon icon = new ImageIcon(new URL(url));
        Image origImage = icon.getImage();
        Image scaledInstance = origImage.getScaledInstance(600, 800, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledInstance); //this returns null because does not wait for image to load
        /*

private static boolean openWebpage(URL url) {
    try {
        return openWebpage(url.toURI());
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
    return false;
}*/
    /*private boolean openWebpage(URI uri)
    {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;

        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
        {
            try
            {
                desktop.browse(uri);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }*/
    }
}