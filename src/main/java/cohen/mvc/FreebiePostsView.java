package cohen.mvc;

import cohen.json.Post;
import cohen.json.PostListInfo;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.util.List;

@Singleton
public class FreebiePostsView extends JComponent
{
    @Inject
    public FreebiePostsView()
    {
    }

    private PostListInfo postListInfo;

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int height = getHeight();
        g.translate(0, height);

        int width = getWidth();

        int y = height;

        if (postListInfo != null)
        {
            List<Post> posts = postListInfo.getPosts();

            for (Post onePost : posts)
            {
                String itemDescription = onePost.getContent();

                g.drawString(itemDescription, width/4, y);

                y -= 20;
            }
        }
    }

    public void setPosts(PostListInfo postListInfo)
    {
        this.postListInfo = postListInfo;
        repaint();
    }
}
