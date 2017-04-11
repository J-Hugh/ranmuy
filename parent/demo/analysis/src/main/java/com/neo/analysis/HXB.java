package com.neo.analysis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 华夏银行
 * Created by neoThe on 2017/4/11.
 */
public class HXB extends SmsBase implements ISms {

    public HXB(String content) {
        super(content);
    }

    @Override
    public Boolean isValid() {
        return null;
    }

    @Override
    public BigDecimal anaAmount() {
        return null;
    }

    @Override
    public void anaBase() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日HH:mm");
            Pattern p1 = Pattern.compile("您的华夏卡（\\*\\*(\\d+)），(.{11})(.{5})(\\d+\\.\\d+)元，(.+)，余额(\\d+\\.\\d+)元");
            Matcher m = p1.matcher(super.getContent());
            if(m.find()) {
                this.setAccount("华夏银行 "+m.group(1));
                this.setDate(format.parse(m.group(2)));
                this.setType(m.group(5));
                this.setAmount(new BigDecimal(m.group(4)));
                this.setBalance(new BigDecimal((null == m.group(6) || "".equals(m.group(6))) ? "0" : m.group(6)));
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BigDecimal anaBalance() {
        return null;
    }

}
