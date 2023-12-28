package com.scott.server.service;

import com.scott.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scott.server.utils.RespBean;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author scott
 * @since 2023-11-30
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String userName, String password, HttpServletRequest request);

    Admin getAdminByUserName(String userName);
}
