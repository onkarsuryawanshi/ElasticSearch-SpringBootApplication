package com.elastic;

import Model.Xml;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import org.elasticsearch.action.index.IndexRequest;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;

import com.apptasticsoftware.rssreader.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Repository
public class ElasticSearchQuery {

    private final String indexName = "xml";


    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public String createDocument(Xml xml) {
        IndexResponse response = null;
        try {
            response = elasticsearchClient
                    .index(i -> i
                            .index(indexName)
                            .id(String.valueOf(new Date().getTime()))
                            .document(xml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(response.version());
    }

    public List<Item> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse<Item> searchResponse = elasticsearchClient.search(searchRequest, Item.class);
        List<Hit<Item>> hits = searchResponse.hits().hits();
        List<Item> list = new ArrayList<>();
        for (Hit<Item> object : hits) {

            System.out.print((object.source()));
            list.add(object.source());

        }
        return list;
    }

    public Xml getDocument(String documentId) {

        GetResponse<Xml> response = null;
        try {
            response = elasticsearchClient.get(g -> g
                            .index(indexName)
                            .id(documentId),
                    Xml.class
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Xml xml = null;
        if (response.found()) {
            xml = response.source();
            System.out.println("document id " + xml.toString());
        } else {
            System.out.println("document not found");
        }
        return xml;

    }

    public String deleteDocumentById(String documentId) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(documentId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("document with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("document not found");
        return new StringBuilder("document with id " + deleteResponse.id() + " does not exist.").toString();

    }

    public void searchByKeywordTitle() throws IOException {
        String field = "title.keyword";
        String textToBeSearched = "RSS.com Product Updates";
        IndexRequest indexRequest = new IndexRequest(indexName);
        indexRequest.id(indexName);
        SearchRequest request = SearchRequest.of(s -> s.index(indexName).query(q -> q
                .match(t -> t
                        .field(field)
                        .query(textToBeSearched))));
        SearchResponse response = elasticsearchClient.search(request, Xml.class);
        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            System.out.println("There are " + total.value() + " results");
        } else {
            System.out.println("There are more than " + total.value() + " results");
        }

        List<Hit<Xml>> hits = response.hits().hits();
        for (Hit<Xml> hit : hits) {
            Xml xml = hit.source();
            System.out.println("Found found  " + xml + ", score " + hit.score());
        }
    }

    public void searchByTextTitle() {
        String field = "title";
        String textToBeSearched = "RSS.com Product Updates";
        IndexRequest indexRequest = new IndexRequest(indexName);
        indexRequest.id(indexName);
        SearchRequest request = SearchRequest.of(s -> s.index(indexName).query(q -> q
                .match(t -> t
                        .field(field)
                        .query(textToBeSearched))));
        SearchResponse response = null;
        try {
            response = elasticsearchClient.search(request, Xml.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            System.out.println("There are " + total.value() + " results");
        } else {
            System.out.println("There are more than " + total.value() + " results");
        }

        List<Hit<Xml>> hits = response.hits().hits();
        for (Hit<Xml> hit : hits) {
            Xml xml = hit.source();
            System.out.println("Found found  " + xml + ", score " + hit.score());
        }
    }


    public void searchByQueryKeywordGuid(String textToSearch) {
        String searchText = "guid" + ".keyword";
        String textToBeSearched = textToSearch;

        IndexRequest indexRequest = new IndexRequest(indexName);
        indexRequest.id(indexName);
        SearchRequest request = SearchRequest.of(s -> s.index(indexName).query(q -> q
                .match(t -> t
                        .field(searchText)
                        .query(textToBeSearched))));
        SearchResponse response = null;
        try {
            response = elasticsearchClient.search(request, Xml.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            System.out.println("There are " + total.value() + " results");
        } else {
            System.out.println("There are more than " + total.value() + " results");
        }

        List<Hit<Xml>> hits = response.hits().hits();
        for (Hit<Xml> hit : hits) {
            Xml xml = hit.source();
            System.out.println("Found found  " + xml + ", score " + hit.score());
        }
    }
}
