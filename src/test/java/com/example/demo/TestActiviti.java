package com.example.demo;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
@SpringBootTest(classes = XhAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestActiviti {
          
        @Autowired
        RuntimeService runtimeService;
        @Autowired  
        private TaskService taskService;  
        @Autowired  
        private RepositoryService repositoryService; 
        
        @Test
        public void TestStartProcess() {
            System.out.println("Start.........");
            ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
            List<ProcessDefinition> list = query.list();
            for (ProcessDefinition processDefinition : list) {
                System.out.println("流程启动成功，流程id:"+processDefinition);
            }
            
        }
        
        @Test
        public void findTasksByUserId() {
            String userId ="dulingjiang";
            List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("MyProcess").taskCandidateOrAssigned(userId).list();
            System.out.println("任务列表："+resultTask);
        }

    
}
