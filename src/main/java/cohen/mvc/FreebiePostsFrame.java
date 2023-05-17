package cohen.mvc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

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

        postList.setEditable(false);

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
                    if (!Objects.equals(userLat.getText(), "")
                            & !Objects.equals(userLon.getText(), ""))
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
                    if (!Objects.equals(userLat.getText(), "")
                            & !Objects.equals(userLon.getText(), ""))
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(postList, BorderLayout.CENTER);

        //TODO: Add fields for lat/lon, display image, title, and description of post,
        // link image to url to open in browser
        //TODO: JList - for side menu list of posts

        postsButton.addActionListener(e ->
        {
            if (!Objects.equals(userLat.getText(), "") & !Objects.equals(userLon.getText(), ""))
            {
                controller.refreshPosts(userLat.getText(), userLon.getText());
                requestFocus();
            }
        });

        controller.refreshPosts(userLat.getText(), userLon.getText());

        setContentPane(mainPanel);
    }
}