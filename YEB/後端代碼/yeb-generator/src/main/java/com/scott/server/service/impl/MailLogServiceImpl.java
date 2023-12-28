package com.scott.server.service.impl;

import com.scott.server.pojo.MailLog;
import com.scott.server.mapper.MailLogMapper;
import com.scott.server.service.IMailLogService;
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
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
