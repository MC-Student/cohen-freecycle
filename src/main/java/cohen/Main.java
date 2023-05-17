package cohen;

import cohen.dagger.FreebieComponent;
import cohen.mvc.FreebiePostsFrame;
import cohen.dagger.DaggerFreebieComponent;

public class Main
{
    public static void main(String[] args)
    {
        FreebieComponent component = DaggerFreebieComponent
                .builder()
                .build();
        FreebiePostsFrame frame = component.providesFreebiePostsFrame();
        frame.setVisible(true);
    }
}