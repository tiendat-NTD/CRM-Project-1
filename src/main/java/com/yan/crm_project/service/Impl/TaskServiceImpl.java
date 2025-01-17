package com.yan.crm_project.service.Impl;

import java.util.*;

import javax.transaction.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.yan.crm_project.model.*;
import com.yan.crm_project.repository.*;
import com.yan.crm_project.service.*;
import com.yan.crm_project.util.*;

import lombok.extern.slf4j.*;

import static org.springframework.util.StringUtils.*;

@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StringUtil stringUtil;

    @Autowired
    private TextUtil textUtil;

    @Override
    public List<Task> getTasks() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByDoer(int doerId) {
        log.info("Fetching all tasks by doer id: {}", doerId);
        return taskRepository.findAllByDoerId(doerId);
    }

    @Override
    public Task getTask(int id) {
        log.info("Fetching task with id: {}", id);
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task saveTask(Task task) {
        var name = task.getName();
        task.setName(capitalize(stringUtil.replaceMultiBySingleWhitespace(stringUtil.removeSpCharsBeginAndEnd(name))));
        task.setDescription(textUtil.parseToLegalText(task.getDescription()));
        log.info("Saving task with name: {}", name);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) {
        log.info("Deleting task with id: {}", id);
        taskRepository.deleteById(id);
    }
}
