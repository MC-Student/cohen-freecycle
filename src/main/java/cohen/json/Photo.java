package cohen.json;

import java.util.List;
public class Photo
{
    private String blurhash;
    private List<Image> images;
    private String photo_id;
    private String thumbnail;
    private String url;
    public String getBlurhash()
    {
        return blurhash;
    }
    public void setBlurhash(String blurhash)
    {
        this.blurhash = blurhash;
    }
    public List<Image> getImages()
    {
        return images;
    }
    public void setImages(List<Image> images)
    {
        this.images = images;
    }
    public String getPhotoId()
    {
        return photo_id;
    }
    public void setPhotoId(String photoId)
    {
        this.photo_id = photoId;
    }
    public String getThumbnail()
    {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
}