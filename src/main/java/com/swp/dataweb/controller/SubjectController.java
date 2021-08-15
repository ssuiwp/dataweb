package com.swp.dataweb.controller;

import com.swp.dataweb.entity.Response.Response;

import com.swp.dataweb.entity.Subject;

import com.swp.dataweb.entity.SubjectQuery;
import com.swp.dataweb.service.SubjectService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.swp.dataweb.entity.Response.Status.*;

@RestController
@RequestMapping("/dataweb/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 添加课题
     * @param subject 课题
     * @return 课题类
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Response createSubject(@RequestBody Subject subject){
        if(subject == null){
            return Response.error(SUBJECT_EMPTY);
        }

        return subjectService.createSubject(subject);
    }

    /**
     * 查询课题
     * @param query 课题查询类
     * @return 查询类
     */
    @PostMapping(value = "/query", consumes = "application/json", produces = "application/json")
    public Response obtainSubject(@RequestBody SubjectQuery query){
        if (query == null){
            return Response.error(SUBJECT_QUERY_EMPTY);
        }
        return subjectService.obtainSubject(query);
    }

    /**
     * 更新课程
     * @return 更新后的课题
     */
    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public Response updateSubject(@RequestBody Subject subject){
        if(subject == null){
            return Response.error(SUBJECT_EMPTY);
        }
        return subjectService.updateSubject(subject);
    }

    /**
     * 删除课题
     * @return 删除结果
     */
    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public Response deleteSubject(@RequestBody Subject subject){
        if (subject == null){
            return Response.error(FAILURE);
        }
        //todo 删除课题包含对应的表单
        return subjectService.deleteSubject(subject);
    }

    @GetMapping(value = "/test", produces = "application/json")
    public String test(){

        return "hello world!";
    }
}
