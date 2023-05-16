package cohen.mvc;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class FreebiePostsFrame extends JFrame
{
    private final FreebiePostsView freebiePostsView;

    private final FreebiePostsController controller;

    @Inject
    public FreebiePostsFrame(FreebiePostsView freebiePostsView,
                             FreebiePostsController controller)
    {
        this.freebiePostsView = freebiePostsView;
        this.controller = controller;

        setSize(800, 600);
        setTitle("Freebie Posts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton bGetPosts = new JButton("Get recent NYC posts");
        bGetPosts.setSize(25, 45);

        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bGetPosts, BorderLayout.PAGE_START);
        mainPanel.add(postsPanel, BorderLayout.CENTER);

        bGetPosts.addActionListener(e ->
        {
            controller.refreshPosts();
        });

        controller.refreshPosts();

        setContentPane(mainPanel);
    }
}