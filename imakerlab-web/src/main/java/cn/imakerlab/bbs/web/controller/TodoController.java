package cn.imakerlab.bbs.web.controller;

import cn.imakerlab.bbs.constant.ErrorConstant;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Todo;
import cn.imakerlab.bbs.security.utils.SecurityUtils;
import cn.imakerlab.bbs.service.Imp.TodoServiceImp;
import cn.imakerlab.bbs.service.Imp.UserServiceImp;
import cn.imakerlab.bbs.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TodoController {

    @Autowired
    TodoServiceImp todoService;

    @Autowired
    UserServiceImp userService;

    @GetMapping({"todo/{userIdStr}", "todo"})
    @ResponseBody
    public ResultUtils listTodosByUserId(@PathVariable(required = false) String userIdStr,
                                         HttpServletRequest request) {

        Integer userId;
        if (StringUtils.isEmpty(userIdStr)) {
            userId = SecurityUtils.getUserIdFromRequest(request);
        } else {
            userId = Integer.parseInt(userIdStr);
        }

        if (userService.getUserByUserId(userId) == null) {
            throw new MyException(ErrorConstant.User.USER_NOT_FOUND);
        }

        List<Todo> todoList = todoService.listNotDeletedTodoListByUserId(userId);

        Map<String, List<Todo>> map = new HashMap<>();

        map.put("set", todoList);

        return ResultUtils.success(map);
    }

    @PostMapping("/todo")
    @ResponseBody
    public ResultUtils addTodo(@Valid Todo todo,
                               HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        boolean isEnableAddTodo = todoService.isEnableAddTodoCurrentUser(userId);

        if (isEnableAddTodo) {
            todoService.insertTodoByUserId(userId, todo);
        } else {
            return ResultUtils.failure(ErrorConstant.Todo.TODO_COUNT_IS_EXECEEDS);
        }

        return ResultUtils.success();
    }

    @PutMapping("/todo")
    @ResponseBody
    public ResultUtils updateTodoById(
            @RequestParam Todo todo,
            HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        todoService.updateTodoById(userId, todo);

        return ResultUtils.success();
    }

    @DeleteMapping("/todo")
    @ResponseBody
    public ResultUtils deleteTodoByArray(@RequestParam List<Integer> delList,
                                         HttpServletRequest request) {

        int userId = SecurityUtils.getUserIdFromRequest(request);

        todoService.deleteTodoByArray(userId, delList);

        return ResultUtils.success();
    }

}