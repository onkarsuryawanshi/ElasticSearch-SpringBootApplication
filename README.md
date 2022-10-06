# ElasticSearch-SpringBootApplication
Using Rss reader 





1.Using Rss reader class reading input from url 

            RssReader reader = new RssReader();
            Stream<Item> rssFeed = reader.read(url);
            items = rssFeed.collect(Collectors.toList());
            
            
2.configure Elatic search clien in clientElasticSearchConfiguration


3.write the ElasticSearchController to add end points


4.write the method of end point in ElasticSearchQuery
  for retriving the data from ES-rest application make sure you have no args constructor in POJO class 


