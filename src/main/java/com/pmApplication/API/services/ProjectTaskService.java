package com.pmApplication.API.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmApplication.API.domain.Backlog;
import com.pmApplication.API.domain.Project;
import com.pmApplication.API.domain.ProjectTask;
import com.pmApplication.API.exceptions.ProjectNotFoundException;
import com.pmApplication.API.repositories.BacklogRepository;
import com.pmApplication.API.repositories.ProjectRepository;
import com.pmApplication.API.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask )
	{
		
		try
		{
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
			
			projectTask.setBacklog(backlog);
			
			Integer BacklogSequence = backlog.getPTSequence();
			BacklogSequence++;
			
			backlog.setPTSequence(BacklogSequence);
			
//			Add Sequence to Project task
			
			projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
			projectTask.setProjectIdentifier(projectIdentifier.toUpperCase());
			
//	      INITIAL priority when priority null
			if(projectTask.getPriority()==null)
			{
				projectTask.setPriority(3);
			}

//			INITIAL status when status is null
			if(projectTask.getStatus()==""|| projectTask.getStatus()==null)
			{
				projectTask.setStatus("TO-DO");
			}
			
			
			return projectTaskRepository.save(projectTask);
		}
		catch(Exception e)
		{
			throw new ProjectNotFoundException("Project not found");
		}
		
	}
	
	
	public Iterable<ProjectTask> findBacklogByProjectId(String projectIdentifier)
	{
		
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
		 
		if(project==null)
		{
			throw new ProjectNotFoundException("Project Id:' "+projectIdentifier+"' doesnot not exist");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier.toUpperCase());
	}
	
	
	public ProjectTask findPTByProjectSequence(String backlog_id,String projectSequence)
	{
		
		
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog==null)
		{
			throw new ProjectNotFoundException("Project with ID: '"+backlog_id+"' does not exist");
		}
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
		
		if(projectTask==null)
		{
			throw new ProjectNotFoundException("Project task '"+projectSequence+"' not found");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlog_id.toUpperCase()))
		{
			throw new ProjectNotFoundException("Project task '"+projectSequence+" does not exist in project "+backlog_id);
		}
		
	
		return projectTask;
		
		
		
		
	}
	
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask,String backlog_pid,String pt_id) 
	{
		
		ProjectTask projectTask = findPTByProjectSequence(backlog_pid,pt_id);
		
		projectTask=updatedTask;
		
		return projectTaskRepository.save(projectTask);
	}
	
	
	public void deletePTByProjectSequence(String backlog_pid, String pt_id)
	{
		ProjectTask projectTask = findPTByProjectSequence(backlog_pid,pt_id);
		
		projectTaskRepository.delete(projectTask);
		
	}
	
	
	
	
	
	
	
	
	
	
}
