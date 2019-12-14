package com.pmApplication.API.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmApplication.API.domain.Backlog;
import com.pmApplication.API.domain.ProjectTask;
import com.pmApplication.API.repositories.BacklogRepository;
import com.pmApplication.API.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask )
	{
		
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		projectTask.setBacklog(backlog);
		
		Integer BacklogSequence = backlog.getPTSequence();
		BacklogSequence++;
		
//		Add Sequence to Project task
		
		projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
//      INITIAL priority when priority null
		if(projectTask.getPriority()==0 || projectTask.getPriority()==null)
		{
			projectTask.setPriority(3);
		}

//		INITIAL status when status is null
		if(projectTask.getStatus()==""|| projectTask.getStatus()==null)
		{
			projectTask.setStatus("TO-DO");
		}
		
		
		return projectTaskRepository.save(projectTask);
		
	}
	
}
