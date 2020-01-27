package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Todo;
import cn.imakerlab.bbs.service.Imp.TodoServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TodoController {

    @Autowired
    TodoServiceImp todoService;


    @GetMapping("todo/{idStr}")
    @ResponseBody
    public ResultUtils getTodoListByUserId(@PathVariable String idStr) {

        Integer id = Integer.parseInt(idStr);

        List<Todo> todoList = todoService.getTodoListByUserId(id);

        if (CollectionUtils.isEmpty(todoList)) {
            throw new MyException("获取到的todo为空，原因是该用户id不存在，或该用户todo列表本来就是空");
        }

        Map map = new HashMap();

        map.put("set", todoList);

        return ResultUtils.success().setData(map);
    }

    @PostMapping("/todo")
    @ResponseBody
    public ResultUtils addTodo(
            @RequestParam(required = true) int userId,
            @RequestParam(required = true) Todo todo) {

        todoService.addTodoByUserId(userId, todo);

        return ResultUtils.success();
    }

    @PutMapping("/todo")
    @ResponseBody
    public ResultUtils modifyTodoTodoById(
            @RequestParam(required = true) int userId,
            @RequestParam(required = true) Todo todo) {

        todoService.modifyTodoById(todo);

        return ResultUtils.success();
    }

    @DeleteMapping("/todo")
    @ResponseBody
    public ResultUtils deleteTodoByArray(@RequestParam(required = true) List<Integer> delList){

        if(CollectionUtils.isEmpty(delList)){
            throw new MyException("delList是空的");
        }

        todoService.deleteTodoByArray(delList);

        return  ResultUtils.success();
    }

}