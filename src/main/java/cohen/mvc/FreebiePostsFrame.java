package cohen.mvc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FreebiePostsFrame extends JFrame
{
    private final JTextField userLat;
    private final JTextField userLon;
    private final JFormattedTextField userDate;

    @Inject
    public FreebiePostsFrame(FreebiePostsController controller,
                             @Named("postTitles") JList<String> postTitles,
                             @Named("title") JLabel title,
                             @Named("description") JTextArea description,
                             @Named("photo") JLabel photo)
    {

        setSize(800, 600);
        setTitle("Freebie Posts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel dateTime = new JLabel("Minimum date (YYYY-MM-DD)");
        dateTime.setHorizontalAlignment(SwingConstants.CENTER);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        userDate = new JFormattedTextField(df);
        userDate.setText(String.valueOf(LocalDate.now()));
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
                        controller.refreshPosts(userLat.getText(),
                                userLon.getText(),
                                getUtcDateParam());
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
                        controller.refreshPosts(userLat.getText(),
                                userLon.getText(),
                                getUtcDateParam());
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
                        controller.refreshPosts(userLat.getText(),
                                userLon.getText(),
                                getUtcDateParam());
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

        GridLayout gridLayout = new GridLayout(2,3);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        JPanel paramPanel = new JPanel(gridLayout);
        paramPanel.setBorder(null);
        paramPanel.setBackground(Color.white);

        paramPanel.add(lat);
        paramPanel.add(lon);
        paramPanel.add(dateTime);
        paramPanel.add(userLat);
        paramPanel.add(userLon);
        paramPanel.add(userDate);

        JButton postsButton = new JButton("View posts");
        postsButton.setSize(25, 45);
        postsButton.setBackground(Color.white);
        postsButton.setBorder(new StrokeBorder(new BasicStroke(1)));
        postsButton.setMinimumSize(getMinimumSize());
        postsButton.setFont(Font.getFont("Arial Narrow"));
        postsButton.addActionListener(e ->
        {
            if (allInputComplete())
            {
                controller.refreshPosts(userLat.getText(),
                        userLon.getText(),
                        getUtcDateParam());
                requestFocus();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(paramPanel, BorderLayout.CENTER);
        topPanel.add(postsButton, BorderLayout.EAST);

        JPanel individualPost = new JPanel(new GridLayout(3, 1));
        individualPost.setBackground(Color.white);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setSize(individualPost.getWidth(), individualPost.getHeight()/5);

        description.setEditable(false);
        description.setLineWrap(true);
        description.setSize(individualPost.getWidth()/2,individualPost.getHeight()/3);
        description.setMargin(new Insets(20, 20, 20, 20));

        individualPost.add(title);
        individualPost.add(description);
        individualPost.add(photo);

        ListSelectionModel listSelectionModel = postTitles.getSelectionModel();
        postTitles.setName("Posts");
        postTitles.setDragEnabled(true);
        postTitles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postTitles.setSelectionBackground(Color.lightGray);
        postTitles.setFixedCellWidth(200);
        //postTitles.setVisibleRowCount(20);
        //TODO: make cells in list hoverable
        listSelectionModel.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (!e.getValueIsAdjusting())
                {
                    int postSelected = e.getLastIndex();
                    try
                    {
                        controller.updatePost(postSelected);
                    }
                    catch (MalformedURLException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(500, 500);
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(postTitles, BorderLayout.WEST);
        mainPanel.add(individualPost, BorderLayout.CENTER);

        controller.refreshPosts(userLat.getText(), userLon.getText(), getUtcDateParam());

        setContentPane(mainPanel);
    }

    private String getUtcDateParam()
    {
        return userDate.getText() + "T00:00:00";
    }

    private boolean allInputComplete()
    {
        return !userLat.getText().isBlank()
                & !userLon.getText().isBlank()
                & !userDate.getText().isBlank();
    }
}

//TODO: Add fields for lat/lon, display image, title, and description of post,
// link image to url to open in browser