package com.zy.cn.service.impl;
import com.zy.cn.annonations.Cache;
import com.zy.cn.dao.MenuDAO;
import com.zy.cn.entity.Menu;
import com.zy.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.REPEATABLE_READ,
        readOnly = false,
        timeout = -1,
        rollbackFor = RuntimeException.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Cache
    @Override
    public List<Menu> queryAll() {

       return menuDAO.queryAll();

    }
}
