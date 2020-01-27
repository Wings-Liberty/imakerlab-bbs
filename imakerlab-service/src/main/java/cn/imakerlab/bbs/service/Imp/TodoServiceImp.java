package cn.imakerlab.bbs.service.Imp;

import cn.imakerlab.bbs.mapper.TodoDao;
import cn.imakerlab.bbs.model.po.Todo;
import cn.imakerlab.bbs.model.po.TodoExample;
import cn.imakerlab.bbs.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImp implements TodoService {

    @Autowired
    TodoDao todoDao;

    @Override
    public List<Todo> getTodoListByUserId(Integer id) {

        TodoExample example = new TodoExample();
        example.createCriteria().andUserIdEqualTo(id);

        List<Todo> list = todoDao.selectByExample(example);

        return list;
    }

    @Override
    public void modifyTodoById(Todo todo) {

        TodoExample example = new TodoExample();
        example.createCriteria().andIdEqualTo(todo.getId());

        todoDao.updateByExampleSelective(todo, example);

    }

    @Override
    public void addTodoByUserId(int userId, Todo todo) {

        todoDao.insertSelective(todo);

    }

    @Override
    public void deleteTodoByArray(List<Integer> delList) {

        TodoExample example = new TodoExample();
        example.createCriteria().andIdIn(delList);

        todoDao.deleteByExample(example);
    }
}