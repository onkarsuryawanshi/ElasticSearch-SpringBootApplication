package com.elastic;

import Model.Xml;
import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RssFileReader {

    private String url="https://rss.com/blog/feed/";
    public List<Xml> read() {
        List<Xml> xmlList = new ArrayList<>();
        List<Item> items = null;
        try {
            RssReader reader = new RssReader();
            Stream<Item> rssFeed = reader.read(url);
            items = rssFeed.collect(Collectors.toList());
            for (Item item: items) {
                /*
                item.getTitle is in optional field like --> optional["this is title"]
                used orElseThrow method of optional to convert it into string field
                */
                String title = item.getTitle().orElseThrow(NoSuchElementException::new);
                String link = item.getLink().orElseThrow(NoSuchElementException::new);
                String pubDate = item.getPubDate().orElseThrow(NoSuchElementException::new);
                String category = item.getCategory().orElseThrow(NoSuchElementException::new);
                String guid = item.getGuid().orElseThrow(NoSuchElementException::new);
                String description = item.getDescription().orElseThrow(NoSuchElementException::new);

                //creating objects
                Xml object = new Xml(title,link,pubDate,category,guid,description);
                xmlList.add(object);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            return xmlList;
    }

}
