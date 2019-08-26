package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author: 杨强
 * @Date: 2019/8/26 22:44
 * @Version 1.0
 * @Discription
 */
@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    /**
     * 拼装扫一扫登录的url
     * @return
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page" ,required = true) String accessPage) throws UnsupportedEncodingException {
        //获取开放平台重定向的地址
        String redirecturl=weChatConfig.getOpenRedirectUrl();
        //url编码
        String callbackUrl= URLEncoder.encode(redirecturl, "GBK");
        String qrcodeUrl=String.format(WeChatConfig.QR_CODE_URL,
                                        weChatConfig.getOpenAppId(), //微信开放平台的appid
                                        callbackUrl,                //微信回调我们的url
                                        accessPage);                //要返回的url
        return JsonData.buildSuccess(qrcodeUrl);
    }
}
