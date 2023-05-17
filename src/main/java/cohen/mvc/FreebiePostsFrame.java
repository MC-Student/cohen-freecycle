package cohen.mvc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;

public class FreebiePostsFrame extends JFrame
{
    private final FreebiePostsController controller;
    private JTextArea postList;

    @Inject
    public FreebiePostsFrame(FreebiePostsController controller,
                             @Named("postList") JTextArea postList)
    {
        this.controller = controller;
        this.postList = postList;

        setSize(800, 600);
        setTitle("Freebie Posts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton bGetPosts = new JButton("Get recent NYC posts");
        bGetPosts.setSize(25, 45);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bGetPosts, BorderLayout.PAGE_START);
        mainPanel.add(postList, BorderLayout.CENTER);

        //TODO: Add fields for lat/lon, display image, title, and description of post,
        // link image to url to open in browser
        //TODO: JList - for side menu list of posts

        bGetPosts.addActionListener(e ->
        {
            controller.refreshPosts();
        });

        controller.refreshPosts();

        setContentPane(mainPanel);
    }
}