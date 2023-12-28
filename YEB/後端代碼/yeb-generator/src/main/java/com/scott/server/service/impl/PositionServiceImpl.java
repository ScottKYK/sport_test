package com.scott.server.service.impl;

import com.scott.server.pojo.Position;
import com.scott.server.mapper.PositionMapper;
import com.scott.server.service.IPositionService;
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
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
