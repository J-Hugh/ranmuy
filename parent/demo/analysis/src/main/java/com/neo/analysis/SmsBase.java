package com.neo.analysis;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * Created by neoThe on 2017/4/11.
 */
public class SmsBase {

    /**
     * 短信内容
     */
    private String content;
    /**
     * 账户
     */
    private String account;
    /**
     * 交易日期
     */
    private Date date;

    /**
     * 交易类型

     */
    private String type;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 余额
     */
    private BigDecimal balance;

    public SmsBase(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
