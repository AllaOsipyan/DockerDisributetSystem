package com.example.demo.controller;
import com.example.demo.models.Link;
import com.example.demo.service.LinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LinksSaveController {

    @Autowired
    public LinkService linkService;

    @ResponseBody
    @RequestMapping(path = "/links", method = RequestMethod.POST)
    public ResponseEntity<?> doSave(@RequestBody Link url) {
        try {
            return new ResponseEntity<Long>(linkService.saveURl(url).getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не найдено", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping()
    @RequestMapping(path = "/links")
    public ResponseEntity<?> getAllLinks() {
        try {
            return new ResponseEntity<List<Link>>(linkService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не найдено", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    @RequestMapping(path = "/links/{id}")
    public ResponseEntity<?> getLink(@PathVariable("id") Long id) {
        try {
            Link link = linkService.getURl(id);
            return new ResponseEntity<>(link, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Не найдено", HttpStatus.NOT_FOUND);
        }

    }
}
