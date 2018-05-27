package br.com.mobibike.coletor.domain.main.services;

import javax.inject.Inject;

import com.github.joaquimsn.querysearch.data.SearchRepository;
import com.github.joaquimsn.querysearch.services.SearchService;

public class SearchServiceImpl implements SearchService {
	private static final long serialVersionUID = 6162790322339733942L;

	@Inject
	private transient SearchRepository searchRepository;

	@Override
	public SearchRepository getRepository() {
		return searchRepository;
	}
}
