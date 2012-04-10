package br.com.amil.repository;

import java.util.List;

import br.com.amil.model.Story;
import br.com.amil.model.Story.Task;

public interface StoryRepository {

	public Story create(Story story);
	
	public Story find(Long id);
	
	public List<Story> findAll();
	
	public TaskHandler using(Story story);
	
	interface TaskHandler {
		Task add(Task task);
		
		Task find(Task task);
	}
}
