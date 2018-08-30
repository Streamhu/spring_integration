package com.hh.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/29 14:27
 */
@Controller
@RequestMapping(value="/validator")
public class ValidatorController {

    @RequestMapping(value="/test")
    public String test(@Validated Items items, BindingResult bindingResult){
        System.out.println("validator配置成功");
        // 获取检验错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for(ObjectError objectError : allErrors){
                System.out.println(objectError.getDefaultMessage());
            }
            return "validator_error";
        }
        return "validator_success";
    }

}
