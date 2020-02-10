package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.Todo;
import java.util.List;

public interface TodoService{
    List<Todo> listNotDeletedTodoListByUserId(Integer id);

    void updateTodoById(int userId, Todo todo);

    void insertTodoByUserId(int userId, Todo todo);

    void deleteTodoByArray(int userId, List<Integer> delList);

    /**
     * 当前用户是否还能够添加todo
     * @param userId
     * @return
     */
    boolean isEnableAddTodoCurrentUser(int userId);
}