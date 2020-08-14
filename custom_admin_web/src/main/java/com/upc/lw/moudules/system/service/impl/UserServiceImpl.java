package com.upc.lw.moudules.system.service.impl;

import com.upc.lw.exception.EntityNotFoundException;
import com.upc.lw.moudules.system.service.UserService;
import com.upc.lw.request.user.UserRequest;
import com.upc.lw.system.User;
import com.upc.lw.system.dto.UserDto;
import com.upc.lw.system.mapstruct.UserMapper;
import com.upc.lw.system.repository.UserRepository;
import com.upc.lw.utills.PageUtils;
import com.upc.lw.utills.QueryHelp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:07
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private static final Pattern CHINA_PATTERN = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");

    @Override
    public UserDto findByPhoneOrPin(String info) {
        User user = null;
        boolean isPhone = isPhone(info);
        if (isPhone) {
            user = userRepository.findByPhone(info);
        } else {
            user = userRepository.findByPin(info);
        }
        log.info("findByPhoneOrPin info:{},isPhone:{},user:{}", info, isPhone, user);

        if (user == null) {
            throw new EntityNotFoundException(User.class);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    public Map<String,Object> findUsers(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);
        Map<String, Object> page = PageUtils.toPage(users.map(userMapper::toDto));
        return page;
    }

    @Override
    public Map<String, Object> findUserListByArg(UserRequest.QueryArg arg, Pageable pageable) {
        Page<User> users = userRepository.findAll((root, query, criteriaBuilder) ->
                QueryHelp.toPredicate(root, arg, criteriaBuilder), pageable);

        List<UserDto> userDtoList = userMapper.toDto(users.getContent());
        Map<String, Object> page = PageUtils.toPage(userDtoList, userDtoList.size());
        return page;
    }

    private static boolean isPhone(String value) {
        boolean matches = CHINA_PATTERN.matcher(value).matches();
        return matches;
    }


    public static void main(String[] args) {
        String val = "13808954726";
        System.out.println(isPhone(val));
    }
}
