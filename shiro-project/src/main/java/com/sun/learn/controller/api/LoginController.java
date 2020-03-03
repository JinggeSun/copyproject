package com.sun.learn.controller.api;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.learn.entity.from.LoginForm;
import com.sun.learn.enums.ExceptionEnums;
import com.sun.learn.exception.CustomException;
import com.sun.learn.vo.ResultVo;
import com.sun.learn.util.ResultVoUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 接口
 * @author zcm
 */
@RestController
public class LoginController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    public static final String CAPTCHA_SESSION = "captcha_session";

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {

        byte[] verByte = null;

        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            //生成验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();

            request.getSession().setAttribute(CAPTCHA_SESSION,createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();

    }

    /**
     * 登陆,使用shiro进行认证
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(String username, String password, String captcha) {

        LoginForm loginForm = new LoginForm();
        loginForm.setCaptcha(captcha);
        loginForm.setUsername(username);
        loginForm.setPassword(password);

        //1. 获取subject主体
        Subject subject = SecurityUtils.getSubject();
        //2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(),loginForm.getPassword());
        //3. 验证验证码
        String verCode = (String) subject.getSession().getAttribute(CAPTCHA_SESSION);
        System.out.println(verCode);
        // 临时注销
        //if (!loginForm.getCaptcha().equals(verCode)){
        //    throw new CustomException(ExceptionEnums.LOGIN_CAPTCHA);
        //}
        //4. 执行登陆
        try {
            subject.login(token);
            //登陆成功
            return ResultVoUtil.successResult();
        }catch (UnknownAccountException e){
            throw new CustomException(ExceptionEnums.LOGIN_NO_ACCOUNT);
        } catch (LockedAccountException e){
            throw new CustomException(ExceptionEnums.LOGIN_ACCOUNT_LOCK);
        } catch (IncorrectCredentialsException e){
            throw new CustomException(ExceptionEnums.LOGIN_PASSWORD_ERROR);
        }
    }
}
