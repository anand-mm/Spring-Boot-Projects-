package com.codeforyou.spring_gitlab;

import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class SpringGitlabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGitlabApplication.class, args);
	}

	@GetMapping("/git")
	public String getGitlabConnection() throws GitLabApiException{

		System.out.println("Going to Execute GitLab API...");
		GitLabApi gitLabApi2 = new GitLabApi("http://gepnic-devops.nic.in/","glpat-s4ySKJTcj5sC7YB_Rqo-");
		List<Project> projects = gitLabApi2.getProjectApi().getProjects();
System.out.println(projects.toString());
		gitLabApi2.close();
        return "returning from git Controller";
	}
	

}
