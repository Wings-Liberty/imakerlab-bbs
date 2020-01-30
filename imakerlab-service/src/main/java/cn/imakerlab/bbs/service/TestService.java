package cn.imakerlab.bbs.service;

import cn.imakerlab.bbs.model.po.User;

import java.util.List;

public interface TestService {

    User getUser();

    List<User> getUsersByList(List<Integer> list);
}
