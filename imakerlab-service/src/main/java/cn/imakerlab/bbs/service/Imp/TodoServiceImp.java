package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.constant.DefaultConstant;
import cn.imakerlab.bbs.mapper.TodoDao;
import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.model.po.Todo;
import cn.imakerlab.bbs.model.po.TodoExample;
import cn.imakerlab.bbs.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class TodoServiceImp implements TodoService {

    @Autowired
    TodoDao todoDao;

    @Override
    public List<Todo> listNotDeletedTodoListByUserId(Integer id) {

        TodoExample example = new TodoExample();
        example.createCriteria()
                .andUserIdEqualTo(id)
                .andIsDeletedEqualTo((byte) 0);

        List<Todo> list = todoDao.selectByExample(example);

        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }

    @Override
    public void updateTodoById(int userId, Todo todo) {

        TodoExample example = new TodoExample();
        example.createCriteria().andIdEqualTo(todo.getId()).andUserIdEqualTo(userId);

        todoDao.updateByExampleSelective(todo, example);

    }

    @Override
    public void insertTodoByUserId(int userId, Todo todo) {

        todo.setIsDeleted((byte) 0);
        todo.setStatus((byte) 0);
        todo.setUserId(userId);

        todoDao.insertSelective(todo);

    }

    @Override
    public void deleteTodoByArray(int userId, List<Integer> delList) {

        TodoExample example = new TodoExample();
        example.createCriteria().andIdIn(delList).andUserIdEqualTo(userId);

        Todo todo = new Todo();
        todo.setIsDeleted((byte) 1);

        todoDao.updateByExampleSelective(todo, example);
    }

    @Override
    public boolean isEnableAddTodoCurrentUser(int userId) {
        boolean isEnableAddTodo = false;

        TodoExample example = new TodoExample();
        example.createCriteria().andUserIdEqualTo(userId);

        if (DefaultConstant.User.USER_TODO_MAX_COUNT < todoDao.countByExample(example)) {
            log.info("该用户的todo持有数量达到上限，不能再添加todo");
        } else {
            isEnableAddTodo = true;
        }

        return isEnableAddTodo;

    }
}