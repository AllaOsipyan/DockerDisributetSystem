package com.example.demo.controller;
import com.example.demo.Send;
import com.example.demo.models.Link;
import com.example.demo.service.LinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class LinksSaveController {

    @Autowired
    public LinkService linkService;
    @Autowired
    public Send sendHandler;

    @ResponseBody
    @RequestMapping(path = "/links", method = RequestMethod.POST)
    public ResponseEntity<?> doSave(@RequestBody Link url) {
        try {
            Link savedLink = linkService.saveURl(url);
            Long savedLinkId = savedLink.getId();
            sendHandler.sendMessage(savedLinkId, url.getUrl());
            return new ResponseEntity<Long>(savedLinkId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("NOT_FOUND", HttpStatus.OK);
        }
    }


    @RequestMapping(path = "/links", method = RequestMethod.GET)
    public ResponseEntity<?> getAllLinks() {
        try {
            return new ResponseEntity<List<Link>>(linkService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("Не найдено", HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping(path = "/status/link", method = RequestMethod.PUT)
    public ResponseEntity<?> getAllLinksWithStatus(@RequestBody Link link) {
        try {
            System.out.println(link.getId()+" "+ link.getStatus()+ " "+link.getUrl());
            linkService.patchLink(link.getId(), link.getStatus());
            return new ResponseEntity<>(link.getId(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.OK);
        }

    }

    @RequestMapping(path = "/links/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLink(@PathVariable("id") Long id) {
        try {
            Link link = linkService.getURl(id);
            return new ResponseEntity<>(link, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.OK);
        }

    }
}
