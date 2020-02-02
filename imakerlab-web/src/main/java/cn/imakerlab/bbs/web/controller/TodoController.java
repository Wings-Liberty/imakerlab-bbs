package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Todo;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.TodoServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TodoController {

    @Autowired
    TodoServiceImp todoService;


    @GetMapping("todo/{userIdStr}")
    @ResponseBody
    public ResultUtils getTodoListByUserId(@PathVariable String userIdStr) {

        Integer id = Integer.parseInt(userIdStr);

        List<Todo> todoList = todoService.getTodoListByUserId(id);

        Map map = new HashMap();

        map.put("set", todoList);

        return ResultUtils.success().setData(map);
    }

    @PostMapping("/todo")
    @ResponseBody
    public ResultUtils addTodo(
            @RequestParam(required = true) Todo todo,
            HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromAuthenticationByRequest(request);

        todoService.addTodoByUserId(userId, todo);

        return ResultUtils.success();
    }

    @PutMapping("/todo")
    @ResponseBody
    public ResultUtils modifyTodoTodoById(
            @RequestParam(required = true) Todo todo,
            HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromAuthenticationByRequest(request);

        todoService.modifyTodoById(userId, todo);

        return ResultUtils.success();
    }

    @DeleteMapping("/todo")
    @ResponseBody
    public ResultUtils deleteTodoByArray(@RequestParam(required = true) List<Integer> delList,
                                         HttpServletRequest request){

        if(CollectionUtils.isEmpty(delList)){
            throw new MyException("delList是空的");
        }

        int userId = SecurityUtils.getUserIdFromAuthenticationByRequest(request);

        todoService.deleteTodoByArray(userId, delList);

        return  ResultUtils.success();
    }

}