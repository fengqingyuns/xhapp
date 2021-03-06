package com.example.demo.modules.activiti;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;
    
    @RequestMapping("/act")
    public void TestStartProcess() {
        System.out.println("Start.........");
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程启动成功，流程id:"+processDefinition);
        }
        
    }
}
