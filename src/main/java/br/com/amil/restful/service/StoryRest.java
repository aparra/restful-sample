package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.model.Story;
import br.com.amil.model.Story.Task;
import br.com.amil.repository.StoryRepository;
import br.com.amil.restful.mediatype.usuario.StoryMediaType;
import br.com.amil.restful.mediatype.usuario.TaskMediaType;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Status;

@Resource
@Path("/servicos/story")
public class StoryRest {

	private StoryRepository storyRepository;
	
	private Result result;
	
	private Status status;
	
	public StoryRest(StoryRepository storyRepository, Result result, Status status) {
		super();
		this.storyRepository = storyRepository;
		this.result = result;
		this.status = status;
	}
	
	@Get("")
	public void list() {
		List<StoryMediaType> stories = new ArrayList<StoryMediaType>();
		for (Story task : storyRepository.findAll()) {
			stories.add(new StoryMediaType(task));
		}
		result.use(representation()).from(stories, "stories").recursive().serialize();
	}
	
	@Post("")
	public void create(Story story) {
		Story myStory = storyRepository.create(story);
		
		result.use(representation()).from(new StoryMediaType(myStory)).recursive().serialize();
		status.created();
	}
	
	@Get("/{story.id}")
	public void retrieve(Story story) {
		Story existingStory = storyRepository.find(story.getId());
		if (existingStory == null) {
			status.notFound();
		} else {
			result.use(representation()).from(new StoryMediaType(existingStory)).recursive().serialize();
			status.ok();
		}
	}
	
	@Post("/{story.id}/task")
	public void addTask(Story story, Task task) {
		Task myTask = storyRepository.using(story).add(task);
		result.use(representation()).from(new TaskMediaType(myTask)).recursive().serialize();
		status.created();
	}
	
	@Get("/{story.id}/task")
	public void showTasks(Story story) {
		List<TaskMediaType> tasks = new ArrayList<TaskMediaType>();
		for (Task task : storyRepository.find(story.getId()).getTasks()) {
			tasks.add(new TaskMediaType(task));
		}
		result.use(representation()).from(tasks, "tasks").recursive().serialize();
	}
	
	@Put("/{story.id}/task/{task.id}")
	public void update(Story story, Task task) {
		Task existingTask = storyRepository.using(story).find(task);
		if (existingTask == null) {
			status.notFound();
		} else {
			existingTask.setDescription(task.getDescription());
			existingTask.setStatus(task.getStatus());
			result.use(representation()).from(new TaskMediaType(storyRepository.using(story).find(task))).recursive().serialize();
			status.ok();
		}
	}
	
	@Get("/{story.id}/task/{task.id}")
	public void show(Story story, Task task) {
		Task existingTask = storyRepository.using(story).find(task);
		if (task == null) {
			status.notFound();
		} else {
			result.use(representation()).from(new TaskMediaType(existingTask)).recursive().serialize();
			status.ok();
		}
	}
}
