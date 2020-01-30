package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.Todo;
import java.util.List;

public interface TodoService{
    List<Todo> getTodoListByUserId(Integer id);

    void modifyTodoById(int userId, Todo todo);

    void addTodoByUserId(int userId, Todo todo);

    void deleteTodoByArray(int userId, List<Integer> delList);
}
