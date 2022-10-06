package Model;

import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.Enclosure;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;


@Jacksonized

public class Xml {
    private String title;
    private String description;
    private String link;
    private String author;
    private String category;
    private String guid;
    private Boolean isPermaLink;
    private String pubDate;
    private Channel channel;
    private Enclosure enclosure;


    public Xml() {}

    //    title,link,pubDate,category,guid,description
    public Xml(String title, String link, String pubDate, String category, String guid, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.category = category;
        this.guid = guid;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean getPermaLink() {
        return isPermaLink;
    }

    public void setPermaLink(Boolean permaLink) {
        isPermaLink = permaLink;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    @Override
    public String toString() {
        return "XmlPojo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", guid='" + guid + '\'' +
                ", isPermaLink=" + isPermaLink +
                ", pubDate='" + pubDate + '\'' +
                ", channel=" + channel +
                ", enclosure=" + enclosure +
                '}';
    }
}
