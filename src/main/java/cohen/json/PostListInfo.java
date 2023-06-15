package cohen.json;

import java.util.ArrayList;
import java.util.List;

public class PostListInfo
{
    private Long end_index;
    private Object group_ids;
    private Object last_listings_view;
    private Long num_pages;
    private Long num_posts;
    private Long page;
    private Long per_page;
    private List<Post> posts;
    private Long start_index;

    public Long getEndIndex()
    {
        return end_index;
    }

    public void setEndIndex(Long endIndex)
    {
        this.end_index = endIndex;
    }

    public Object getGroupIds()
    {
        return group_ids;
    }

    public void setGroupIds(Object groupIds)
    {
        this.group_ids = groupIds;
    }

    public Object getLastListingsView()
    {
        return last_listings_view;
    }

    public void setLastListingsView(Object lastListingsView)
    {
        this.last_listings_view = lastListingsView;
    }

    public Long getNumPages()
    {
        return num_pages;
    }

    public void setNumPages(Long numPages)
    {
        this.num_pages = numPages;
    }

    public Long getNumPosts()
    {
        return num_posts;
    }

    public void setNumPosts(Long numPosts)
    {
        this.num_posts = numPosts;
    }

    public Long getPage()
    {
        return page;
    }

    public void setPage(Long page)
    {
        this.page = page;
    }

    public Long getPerPage()
    {
        return per_page;
    }

    public void setPerPage(Long perPage)
    {
        this.per_page = perPage;
    }

    public ArrayList<Post> getPosts()
    {
        return (ArrayList<Post>) posts;
    }

    public void setPosts(List<Post> posts)
    {
        this.posts = posts;
    }

    public Long getStartIndex()
    {
        return start_index;
    }

    public void setStartIndex(Long startIndex)
    {
        this.start_index = startIndex;
    }
}