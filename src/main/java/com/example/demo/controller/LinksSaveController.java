package com.example.demo.controller;
import com.example.demo.service.LinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LinksSaveController {

    @Autowired
    public LinkService linkService;

    @ResponseBody
    @RequestMapping(path = "/links", method = RequestMethod.POST)
    public String doSave(@ModelAttribute("url") String url) {
        linkService.saveURl(url);
        return "redirect:/";
    }

    /*@ResponseBody
    @RequestMapping(path = "/links/{id}", method = RequestMethod.GET)
    public String GetLink(@PathVariable("id") int id) {
        return linkRep.getURl(id);*/
    //}
}
