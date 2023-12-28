package com.scott.server.service.impl;

import com.scott.server.pojo.Admin;
import com.scott.server.mapper.AdminMapper;
import com.scott.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scott
 * @since 2023-11-30
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
