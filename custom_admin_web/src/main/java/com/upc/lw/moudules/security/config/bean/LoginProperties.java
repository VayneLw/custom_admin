package com.upc.lw.moudules.security.config.bean;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

/**
 * @Description 登录相关配置文件：
 * 1.接入验证码  https://gitee.com/whvse/EasyCaptcha
 * @author: liwei
 * @date: 2020/8/5 11:26
 */
@Slf4j
@Data
public class LoginProperties {

    private LoginCode loginCode;

    public Captcha getCaptcha() {
        if (loginCode == null) {
            loginCode = new LoginCode();
            if (loginCode.getCodeType() == null) {
                loginCode.setCodeType(LoginCodeEnum.arithmetic);
            }
        }
        return handleCaptcha(loginCode);
    }

    /**
     * 根据类型获取对应验证码信息
     *
     * @param loginCode
     * @return com.wf.captcha.base.Captcha
     * @throws
     */
    private Captcha handleCaptcha(LoginCode loginCode) {
        Captcha captcha;
        switch (loginCode.getCodeType()) {
            case arithmetic:
                captcha = new ArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                break;
            case chinese:
                captcha = new ChineseCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                break;
            case chinese_gif:
                captcha = new ChineseGifCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                break;
            case gif:
                captcha = new GifCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                break;
            case spec:
                captcha = new SpecCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                break;
            default:
                throw new RuntimeException("获取验证码失败");
        }

        if (StringUtils.isNotEmpty(loginCode.getFrontName())) {
            captcha.setFont(new Font(loginCode.getFrontName(), Font.PLAIN, loginCode.getFrontSize()));
        }
        return captcha;
    }
}
