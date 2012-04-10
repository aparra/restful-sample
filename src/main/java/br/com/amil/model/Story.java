package br.com.amil.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Story implements Serializable {

	private static final long serialVersionUID = -3588002161610033577L;
	
	private Long id;
	private Long points;
	private String title;
	
	private List<Task> tasks = new ArrayList<Task>();

	private ProductOwner productOwner;

	public static class Task {
		
		private Long id;
		private String description;
		private String status;
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String desc) {
			this.description = desc;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductOwner getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(ProductOwner productOwner) {
		this.productOwner = productOwner;
	}
}
