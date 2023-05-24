package cohen.mvc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FreebiePostsFrame extends JFrame
{
    private final JList<String> postTitles;
    private final JLabel title;
    private final JLabel description;

    private final JTextField userLat;
    private final JTextField userLon;
    private final JFormattedTextField userDate;

    @Inject
    public FreebiePostsFrame(FreebiePostsController controller,
                             @Named("postTitles") JList<String> postTitles,
                             @Named("title") JLabel title,
                             @Named("description") JLabel description)
    {
        this.postTitles = postTitles;
        this.title = title;
        this.description = description;

        setSize(800, 600);
        setTitle("Freebie Posts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel dateTime = new JLabel("Minimum date (YYYY-MM-DD)");
        dateTime.setHorizontalAlignment(SwingConstants.CENTER);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        userDate = new JFormattedTextField(df);
        userDate.setText("2023-05-17");
        userDate.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lat = new JLabel("Latitude");
        lat.setHorizontalAlignment(SwingConstants.CENTER);
        userLat = new JTextField();
        userLat.setText("40.776676");
        userLat.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lon = new JLabel("Longitude");
        lon.setHorizontalAlignment(SwingConstants.CENTER);
        userLon = new JTextField();
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
                    if (allInputComplete())
                    {
                        controller.refreshPosts(userLat.getText(), userLon.getText(), getUTCDateParam());
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

        userLon.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (character == KeyEvent.VK_ENTER)
                {
                    if (allInputComplete())
                    {
                        controller.refreshPosts(userLat.getText(), userLon.getText(), getUTCDateParam());
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

        userDate.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (character == KeyEvent.VK_ENTER)
                {
                    if (allInputComplete())
                    {
                        controller.refreshPosts(userLat.getText(), userLon.getText(), getUTCDateParam());
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

        JButton postsButton = new JButton("View posts");
        postsButton.setSize(25, 45);

        postsButton.addActionListener(e ->
        {
            if (allInputComplete())
            {
                controller.refreshPosts(userLat.getText(), userLon.getText(), getUTCDateParam());
                requestFocus();
            }
        });

        JPanel paramPanel = new JPanel(new GridLayout(2, 3));
        paramPanel.add(lat);
        paramPanel.add(lon);
        paramPanel.add(dateTime);
        paramPanel.add(userLat);
        paramPanel.add(userLon);
        paramPanel.add(userDate);

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
                    controller.updatePost(postSelected);
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(postTitles, BorderLayout.WEST);
        mainPanel.add(individualPost, BorderLayout.CENTER);

        controller.refreshPosts(userLat.getText(), userLon.getText(), getUTCDateParam());

        setContentPane(mainPanel);
    }

    private String getUTCDateParam()
    {
        return userDate.getText() + "T00%3A00%3A00";
    }

    private boolean allInputComplete()
    {
        return !userLat.getText().isBlank() & !userLon.getText().isBlank() & !userDate.getText().isBlank();
    }
}

//TODO: Add fields for lat/lon, display image, title, and description of post,
// link image to url to open in browser