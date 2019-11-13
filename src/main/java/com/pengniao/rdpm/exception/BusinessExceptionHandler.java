package com.pengniao.rdpm.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Map;

/**
 * @program: rdpm
 * @description: 全局异常处理类
 * @author: lj
 * @create: 2019-09-24 15:55
 **/
@RestControllerAdvice
public class BusinessExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SQLExceptionPrompt sqlExceptionPrompt;

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("请求有参数才进来");
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "嘟嘟MD");
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) throws NoSuchFieldException {
        AjaxObject r = new AjaxObject();
        //业务异常
        if(e instanceof BusinessException){
            r.put("code", ((BusinessException) e).getCode());
            r.put("msg", ((BusinessException) e).getMsg());
        }else{//系统异常
            r.put("code","500");
//            r.put("msg","未知异常，请联系管理员");
            if(e.getCause() != null){
                //获取异常名称
                String name = e.getCause().getClass().getName();

                //判断是否为SQL异常
                if("java.sql.SQLIntegrityConstraintViolationException".equals(name)){
                    Throwable cause = e.getCause();
                    SQLIntegrityConstraintViolationException ex = (SQLIntegrityConstraintViolationException) cause;
//                    System.out.println("getCause="+e.getCause());
                    //获取异常代码
//                    int errorCode = ex.getErrorCode();
//                    String sqlState = ex.getSQLState();
//                    System.out.println(sqlState);
                    if(ex.getSQLState().equals("23000") && e.getCause().toString().contains("delete or update")){
                        //删除或修改操作后 数据库外键引用异常，提示当前数据正在某个表中使用
                        Map<String,String> map = sqlExceptionPrompt.getMap();
                        for(Map.Entry<String, String> entry: map.entrySet()){
//                            System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
                            if(e.getCause().toString().contains(entry.getKey())){
                                r.put("msg","无法删除："+entry.getValue());
                                break;
                            }
                        }
                    }else{
                        r.put("msg",e.getCause().toString());
                    }
                    //判断异常代码,1451为发生主外键异常时候的错误代码
////                    if(errorCode == 1451){
////                       res.setResultDesc("系统异常：此数据已经使用,不能删除!");
////                    }
                }else{
                    r.put("msg",e.getCause().toString());
                }
            }

        }
        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
            System.out.println("ajax");
            return r;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", e.getMessage());
            modelAndView.addObject("url", req.getRequestURL());
            modelAndView.addObject("stackTrace", e.getStackTrace());
            modelAndView.setViewName("error");
            System.out.println("modelAndView");
            return modelAndView;
        }
    }
}
