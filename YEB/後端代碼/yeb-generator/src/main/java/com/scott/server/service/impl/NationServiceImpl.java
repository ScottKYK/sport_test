package com.scott.server.service.impl;

import com.scott.server.pojo.Nation;
import com.scott.server.mapper.NationMapper;
import com.scott.server.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scott
 * @since 2023-12-05
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
