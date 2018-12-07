package com.wangtao.mybatisplusdemo.service.impl;

import com.wangtao.mybatisplusdemo.entity.Person;
import com.wangtao.mybatisplusdemo.mapper.PersonMapper;
import com.wangtao.mybatisplusdemo.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2018-12-07
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
