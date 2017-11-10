package com.neo.espionage.controller.lbs;

import com.neo.espionage.controller.BaseController;
import com.neo.espionage.controller.BaseSearch;
import com.neo.utils.values.ActResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author luoyulin
 *
 */
@Controller
@RequestMapping(value = "lbs")
public class LbsController extends BaseController {

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    @ResponseBody
    public ActResult<?> Refresh(String mac,double lng,double lat) {
        ActResult<?> ret = new ActResult<>();
        System.out.println(mac + "_" + lng +"_" + lat);
        return ret;
    }
}
