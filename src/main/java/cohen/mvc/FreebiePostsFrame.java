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

        JTextArea postsList = new JTextArea();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bGetPosts, BorderLayout.PAGE_START);
        mainPanel.add(postsList, BorderLayout.CENTER);

        bGetPosts.addActionListener(e ->
        {
            controller.refreshPosts();
        });

        controller.refreshPosts();

        setContentPane(mainPanel);
    }
}