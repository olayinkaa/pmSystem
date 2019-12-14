package com.pmApplication.API.controllers;

//import java.util.HashMap;
//import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmApplication.API.domain.Project;
import com.pmApplication.API.services.MapValidationErrorService;
import com.pmApplication.API.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	
// createNewProject
	@PostMapping
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result)
	{

/*
		if(result.hasErrors())
		{
			Map<String,String> errorMap = new HashMap<>();
			
			for(FieldError error: result.getFieldErrors())
			{
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
			
		}

*/
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Project newProject = projectService.saveOrUpdate(project);
		
		return new ResponseEntity<Project>(newProject,HttpStatus.CREATED);
	}
	
	
//	getProjectById
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId)
	{
		Project project = projectService.findProjectByIdentifier(projectId);
		
		return new ResponseEntity<Project>(project,HttpStatus.OK);
		
	}
	
//	getAllProjects
	@GetMapping
	public ResponseEntity<?> getAllProjects()
	{
		
		Iterable<Project> projects = projectService.findAllProjects();
		
		return new ResponseEntity<Iterable<Project>>(projects,HttpStatus.OK);
	}
	
	
//	deleteProject
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId)
	{
		projectService.deleteProjectByIdentifier(projectId);
		
		return new ResponseEntity<String>("Project with ID '"+projectId+"' was deleted",HttpStatus.OK);
	}
	
	
	
	
}
