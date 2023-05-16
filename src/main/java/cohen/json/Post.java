package cohen.json;

import java.util.List;
public class Post
{
    private String content;
    private String date;
    private String expiration;
    private Object footer;
    private Long group_id;
    private Double latitude;
    private Double longitude;
    private Object outcome;
    private List<Photo> photos;
    private Long post_id;
    private Long repost_count;
    private Object reselling;
    private String source;
    private String title;
    private String type;
    private String url;
    private Long user_id;

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getExpiration()
    {
        return expiration;
    }

    public void setExpiration(String expiration)
    {
        this.expiration = expiration;
    }

    public Object getFooter()
    {
        return footer;
    }

    public void setFooter(Object footer)
    {
        this.footer = footer;
    }

    public Long getGroupId()
    {
        return group_id;
    }

    public void setGroupId(Long groupId)
    {
        this.group_id = groupId;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public Object getOutcome()
    {
        return outcome;
    }

    public void setOutcome(Object outcome)
    {
        this.outcome = outcome;
    }

    public List<Photo> getPhotos()
    {
        return photos;
    }

    public void setPhotos(List<Photo> photos)
    {
        this.photos = photos;
    }

    public Long getPostId()
    {
        return post_id;
    }

    public void setPostId(Long post_id)
    {
        this.post_id = post_id;
    }

    public Long getRepostCount()
    {
        return repost_count;
    }

    public void setRepostCount(Long repost_count)
    {
        this.repost_count = repost_count;
    }

    public Object getReselling()
    {
        return reselling;
    }

    public void setReselling(Object reselling)
    {
        this.reselling = reselling;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Long getUserId()
    {
        return user_id;
    }

    public void setUserId(Long userId)
    {
        this.user_id = userId;
    }
}