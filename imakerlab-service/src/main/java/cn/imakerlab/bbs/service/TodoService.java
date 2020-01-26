package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.Todo;
import java.util.List;

public interface TodoService{
    List<Todo> getTodoListByUserId(Integer id);

    void modifyTodoById(Todo todo);

    void addTodoByUserId(int userId, Todo todo);

    void deleteTodoByArray(List<Integer> delList);
}
