package com.example.demo.service;

import com.example.demo.repository.LinkRepository;
import com.example.demo.models.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
            System.out.println("err:"+ Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    @Transactional
    public void patchLink(Long linkId, int newStatus) throws Exception {
        try {
            linkRepository.updateStatus(linkId, newStatus);
        }catch (Exception e){
            throw new Exception("Not Modified");
        }
    }
}
