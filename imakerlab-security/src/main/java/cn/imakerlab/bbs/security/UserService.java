package cn.imakerlab.bbs.security;

import cn.imakerlab.bbs.mapper.UserDao;
import cn.imakerlab.bbs.model.dto.UserExample;
import cn.imakerlab.bbs.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("输入的用户名是：" + username);

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        cn.imakerlab.bbs.model.dto.User user = MyUtils.ListToOne(
                userDao.selectByExample(example)
        );

//        if (user == null){
//            throw new UsernameNotFoundException(ErrorConstant.User.USER_NAME_NOTFOUND);
//        }

        String password = user.getPassword();

        return new User(
                username,
                passwordEncoder.encode(password),
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}