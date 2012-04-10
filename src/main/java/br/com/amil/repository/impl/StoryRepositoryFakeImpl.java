package br.com.amil.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.amil.model.Story;
import br.com.amil.model.Story.Task;
import br.com.amil.repository.Sequence;
import br.com.amil.repository.StoryRepository;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class StoryRepositoryFakeImpl implements StoryRepository {

	private Sequence sequence;
	
	public StoryRepositoryFakeImpl(Sequence sequence) {
		this.sequence = sequence;
	}

	private Map<Long, Story> db = new HashMap<Long, Story>();
	
	@Override
	public Story create(Story story) {
		story.setId(sequence.nextval());
		db.put(story.getId(), story);
		return story;
	}

	@Override
	public Story find(Long id) {
		return db.get(id);
	}

	@Override
	public List<Story> findAll() {
		return new ArrayList<Story>(db.values());
	}
	
	@Override
	public TaskHandler using(Story story) {
		return new TaskHandlerImpl(story);
	}
	
	public class TaskHandlerImpl implements TaskHandler {
		private Story story;
		
		public TaskHandlerImpl(Story story) {
			this.story = db.get(story.getId());
		}

		@Override
		public Task add(Task task) {
			task.setId(sequence.nextval());
			story.addTask(task);
			return task;
		}
		
		@Override
		public Task find(Task taskRef) {
			for (Task task : story.getTasks()) {
				if (task.getId().equals(taskRef.getId())) {
					return task;
				}
			}
			return null;
		}
	}
}
