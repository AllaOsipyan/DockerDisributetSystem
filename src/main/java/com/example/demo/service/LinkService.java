package com.example.demo.service;

import com.example.demo.repository.LinkRepository;
import com.example.demo.models.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public void saveURl(String url) {
        Link link = new Link(null, url);
        linkRepository.save(link);
        //String sql = String.format("INSERT into link VALUES (DEFAULT, '%s')",url)
    }

    /*public LinkService(LinkRepos linkRepository){
        this.linkRepository = linkRepository;
    }
    /*@Override
    public String getURl(int id) {
        try {
            String sql = "SELECT url FROM link WHERE id="+id;
            Query query = entityManager.createQuery(sql);
            return (String) query.getSingleResult();
        } catch (NoResultException e) {
            return "";
        }
    }*/

    /*public void setURl(String url) {
        Link link = new Link(url);
        this.linkRepository.save(link);
        /*String sql = String.format("INSERT into link VALUES (id.last+1, '%s')",url);
        Query query = entityManager.createQuery(sql);
    }*/
}
