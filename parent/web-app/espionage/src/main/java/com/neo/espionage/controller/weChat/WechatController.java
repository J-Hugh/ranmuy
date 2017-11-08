package com.neo.espionage.controller.weChat;

import com.neo.espionage.controller.BaseController;
import com.neo.espionage.controller.BaseSearch;
import com.neo.espionage.modular.wechatRecord.domain.WechatRecord;
import com.neo.espionage.modular.wechatRecord.service.WechatRecordService;
import com.neo.utils.values.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author luoyulin
 *
 */
@Controller
@RequestMapping(value = "wechat")
public class WechatController extends BaseController {

    @Autowired
    private WechatRecordService wechatRecordService;

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    @ResponseBody
    public ActResult<WechatRecord> Refresh(WechatRecord record) {
        ActResult<WechatRecord> ret = new ActResult<>();
        try {
            wechatRecordService.save(record);
            ret.setDataSet(record);
        } catch (Exception e) {
            ret.setFail("异常："+e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }
}
