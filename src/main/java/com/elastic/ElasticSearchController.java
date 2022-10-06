package com.elastic;

import Model.Xml;
import com.apptasticsoftware.rssreader.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @Autowired
    @Qualifier("rssFileReader")
    private RssFileReader rssFileReader;


    //indexing Data in index
    @PostMapping("/createDocument")
    public ResponseEntity<Object> createOrUpdateDocument() throws IOException {
        List<Xml> xmlList= rssFileReader.read();
        for (Xml xml:xmlList) {
            elasticSearchQuery.createDocument(xml);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    //get all document by id
    @GetMapping("/getAllDocuments")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Item> xml = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }



    //get the document by the _id field
    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById(@RequestParam String documentId) throws IOException {
        Xml xml = elasticSearchQuery.getDocument(documentId);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    //delete the index by _id
    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteByStudentId(@RequestParam String documentId) throws IOException {
        String response = elasticSearchQuery.deleteDocumentById(documentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //searching in title -- keyword field
    @GetMapping("/searchQueryKeywordTitle")
    public void searchQueryKeyword() throws IOException {
        elasticSearchQuery.searchByKeywordTitle();
    }

    //searching in title using Text-field
    @GetMapping("/searchQueryTextTitle")
    public void searchQueryText(){
        elasticSearchQuery.searchByTextTitle();
    }

    //searching in Guid
    @GetMapping("/searchQueryKeywordGuid")
    private void searchQueryKeywordField(@RequestParam String textToSearch) {
        elasticSearchQuery.searchByQueryKeywordGuid(textToSearch);
    }
}
