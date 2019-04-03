package com.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.manager.model.Link;
import com.application.repo.LinkRepository;

@Service
public class LinkService {

		@Autowired
		private LinkRepository linkRepository;
		
		
		public List<Link> getActiveLinks(){
			List<Link> links =  linkRepository.findByActiveTrue();
			for(Link l :links) {
				System.out.println(l.getContext());
			}
			return links;
		}
		
		
		@Transactional
		public void update(List<Link> links) {
				int size = links.size();
				int counter = 0;
				List<Link> temp = new ArrayList<Link>();
				for(Link l:links) {
					temp.add(l);
					if ((counter + 1) % 500 == 0 || (counter + 1) == size) {
						linkRepository.saveAll(temp);
						temp.clear();
					}
					counter++;
				}
		}
}
