package com.pmApplication.API.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmApplication.API.domain.GenericResponse;
import com.pmApplication.API.domain.ProjectTask;
import com.pmApplication.API.services.MapValidationErrorService;
import com.pmApplication.API.services.ProjectTaskService;



@RestController
@RequestMapping("/api/backlog")
public class BacklogController {

	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	
	@PostMapping("/{backlog_projectidentifier}")
	public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
			BindingResult result,
			@PathVariable String backlog_projectidentifier)
	{
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		ProjectTask projectTaskk = projectTaskService.addProjectTask(backlog_projectidentifier, projectTask);
		
		return new ResponseEntity<>(projectTaskk,HttpStatus.OK);
	}
	
	@GetMapping("/{backlog_projectId}")
	public ResponseEntity<?> getProjectBacklog(@PathVariable String backlog_projectId)
	{
		
		Iterable<ProjectTask> projectTasks = projectTaskService.findBacklogByProjectId(backlog_projectId);
		
		return new ResponseEntity<Iterable<ProjectTask>>(projectTasks,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/{backlog_projectId}/{pt_id}")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_projectId,@PathVariable String pt_id)
	{
		
		ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_projectId,pt_id);
		
		return new ResponseEntity<GenericResponse>(new GenericResponse(200,"successfully",projectTask),HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{backlog_pid}/{pt_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,
			BindingResult result,
			@PathVariable String backlog_pid,
			@PathVariable String pt_id)
	{

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlog_pid, pt_id);
		
		return new ResponseEntity<ProjectTask>(updatedTask,HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/{backlog_pid}/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_pid,
						@PathVariable String pt_id)
	{
		
		projectTaskService.deletePTByProjectSequence(backlog_pid, pt_id);
		
		return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully", HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
}
