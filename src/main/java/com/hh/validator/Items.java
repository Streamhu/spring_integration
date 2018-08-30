package com.hh.validator;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/29 14:27
 */
public class Items {

    private int id;

    @Size(min=1,max=30,message="name字符串必须在1到30之间")
    private String name;

    @NotNull(message="生产日期不能为空")
    private Date createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
