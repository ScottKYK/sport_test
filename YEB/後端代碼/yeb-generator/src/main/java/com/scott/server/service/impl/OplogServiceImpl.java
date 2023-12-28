package com.scott.server.service.impl;

import com.scott.server.pojo.Oplog;
import com.scott.server.mapper.OplogMapper;
import com.scott.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
