package cohen.dagger;

import cohen.mvc.FreebiePostsFrame;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {FreebieServiceModule.class})

public interface FreebieComponent
{
    FreebiePostsFrame providesFreebiePostsFrame();
}