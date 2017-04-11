package com.neo.analysis;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 招商银行
 * Created by neoThe on 2017/4/11.
 */
public class CMBC extends SmsBase implements ISms {

    public CMBC(String content) {
        super(content);
    }

    @Override
    public Boolean isValid() {
        return null;
    }

    @Override
    public BigDecimal anaBalance() {
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
            Pattern p1 = Pattern.compile("^您账户(.*)于(.{11})(.*)人民币(\\d+\\.\\d+)(，余额(\\d+\\.\\d+))?");
            Matcher m = p1.matcher(super.getContent());
            if(m.find()) {
                this.setAccount("招商银行 "+m.group(1));
                this.setDate(format.parse(m.group(2)));
                this.setType(m.group(3));
                this.setAmount(new BigDecimal(m.group(4)));
                this.setBalance(new BigDecimal((null == m.group(6) || "".equals(m.group(6))) ? "0" : m.group(6)));
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
