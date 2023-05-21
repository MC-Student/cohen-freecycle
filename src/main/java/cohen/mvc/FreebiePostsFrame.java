package cohen.mvc;

import cohen.json.Post;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class FreebiePostsFrame extends JFrame
{
    private final FreebiePostsController controller;
    private final List<Post> allPosts;
    private JList<String> postTitles;
    private JLabel title;
    private JLabel description;

    @Inject
    public FreebiePostsFrame(FreebiePostsController controller,
                             @Named("allPosts") ArrayList<Post> allPosts,
                             @Named("postTitles") JList<String> postTitles,
                             @Named("title") JLabel title,
                             @Named("description") JLabel description)
    {
        this.controller = controller;
        this.allPosts = allPosts;
        this.postTitles = postTitles;
        this.title = title;
        this.description = description;

        setSize(800, 600);
        setTitle("Freebie Posts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lat = new JLabel("Latitude");
        lat.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField userLat = new JTextField();
        userLat.setText("40.776676");
        userLat.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lon = new JLabel("Longitude");
        lon.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField userLon = new JTextField();
        userLon.setText("-73.971321");
        userLon.setHorizontalAlignment(SwingConstants.CENTER);
        userLat.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (character == KeyEvent.VK_ENTER)
                {
                    if (userLat.getText().length() > 0 & userLon.getText().length() > 0)
                    {
                        controller.refreshPosts(userLat.getText(), userLon.getText());
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });
        userLat.setFocusable(true);
        userLat.requestFocus();

        userLon.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (character == KeyEvent.VK_ENTER)
                {
                    if (userLat.getText().length() > 0 & userLon.getText().length() > 0)
                    {
                        controller.refreshPosts(userLat.getText(), userLon.getText());
                        requestFocus();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {

            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });
        userLon.setFocusable(true);
        userLon.requestFocus();

        JButton postsButton = new JButton("Get recent posts");
        postsButton.setSize(25, 45);

        JPanel paramPanel = new JPanel(new GridLayout(2, 2));
        paramPanel.add(lat);
        paramPanel.add(lon);
        paramPanel.add(userLat);
        paramPanel.add(userLon);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(paramPanel, BorderLayout.CENTER);
        topPanel.add(postsButton, BorderLayout.EAST);

        JPanel individualPost = new JPanel(new GridLayout(3, 1));
        individualPost.add(title);
        individualPost.add(description);

        ListSelectionModel listSelectionModel = postTitles.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (!e.getValueIsAdjusting())
                {
                    int postSelected = e.getLastIndex();
                    controller.refreshPosts(userLat.getText(), userLon.getText());
                    title.setText(allPosts.get(postSelected).getTitle()); //have to read in the list of posts so can get this info also
                    description.setText(allPosts.get(postSelected).getContent());//same here
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(postTitles, BorderLayout.WEST);

        postsButton.addActionListener(e ->
        {
            if (userLat.getText().length() > 0 & userLon.getText().length() > 0)
            {
                controller.refreshPosts(userLat.getText(), userLon.getText());
                requestFocus();
            }
        });

        controller.refreshPosts(userLat.getText(), userLon.getText());

        setContentPane(mainPanel);
    }
}

//TODO: Add fields for lat/lon, display image, title, and description of post,
// link image to url to open in browser