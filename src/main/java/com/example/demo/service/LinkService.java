package com.example.demo.service;

import com.example.demo.repository.LinkRepository;
import com.example.demo.models.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public Link saveURl(Link url) {
        return  linkRepository.save(url);
    }

    public List<Link> getAll() {
        List allLinks = new ArrayList();
        linkRepository.findAll().forEach(el -> allLinks.add(el));
        return allLinks;
    }
    /*public LinkService(LinkRepos linkRepository){
        this.linkRepository = linkRepository;
    }*/

    public Link getURl(Long id) {
        try {
            return linkRepository.findLinkById(id);
        } catch (Exception e) {
        }
        return null;
    }
}
