package com.wangtao.mybatisplusdemo.service.impl;

import com.wangtao.mybatisplusdemo.entity.TestTime;
import com.wangtao.mybatisplusdemo.mapper.TestTimeMapper;
import com.wangtao.mybatisplusdemo.service.ITestTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2018-12-17
 */
@Service
public class TestTimeServiceImpl extends ServiceImpl<TestTimeMapper, TestTime> implements ITestTimeService {

}
