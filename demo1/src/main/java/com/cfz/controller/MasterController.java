package com.cfz.controller;

import com.alibaba.fastjson.JSONArray;
import com.cfz.annotation.SystemLog;
import com.cfz.entity.Master;
import com.cfz.entity.MasterAddress;
import com.cfz.entity.vo.MasterVo;
import com.cfz.entity.vo.PageVo;
import com.cfz.service.MasterAddressService;
import com.cfz.service.MasterService;
import com.cfz.utils.BeanCopyUtils;
import com.cfz.utils.LngAndLatUtil;
import com.cfz.utils.PageUtil;
import com.cfz.utils.ResponseResult;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/master")
public class MasterController {

    @Autowired
    MasterService masterService;

    /**
     * 跳转工程师页面
     * @return
     */
    @SystemLog(methods = "跳转工程师页面",module = "工程师模块")
    @RequestMapping("/selectShow")
    public String goLogin() {
        return "/html/master/table.html";
    }

    /**
     * 查询工程师列表
     * @param master
     * @param page
     * @param limit
     * @return
     */
    @SystemLog(methods = "查询工程师列表",module = "工程师模块")
    @RequestMapping("/selectList")
    @ResponseBody
    public String selectList(Master master, Integer page, Integer limit) {
        PageVo pageVo = masterService.findAll(master, page, limit);
        //转换vo
        List<MasterVo> masterList = BeanCopyUtils.copyBeanList(pageVo.getRows(), MasterVo.class);
        //将经纬度转为地址
        List<MasterVo> collect = masterList.stream()
                .map(masterVo -> {
                    MasterAddress masterAddress = masterVo.getMasterAddress();
                    MasterVo masterVo1 = null;
                    try {
                        masterVo1 = masterVo.setAddress(LngAndLatUtil
                                .getLngAndLat(masterAddress.getLng(),masterAddress.getLat()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return masterVo1;
                }).collect(Collectors.toList());

        PageUtil pageUtil = new PageUtil();
        pageUtil.setMsg("");
        pageUtil.setCount(pageVo.getTotal() + "");
        pageUtil.setCode("0");
        pageUtil.setData(JSONArray.toJSONString(collect));
        return pageUtil.toString();
    }

    /**
     * 跳转添加页面
     * @return
     */
    @SystemLog(methods = "跳转添加页面",module = "工程师模块")
    @RequestMapping("/goAdd")
    public String goAdd() {
        return "/html/master/addMaster.html";
    }

    /**
     * 添加工程师
     * @param master
     * @return
     */
    @SystemLog(methods = "添加工程师",module = "工程师模块")
    @RequestMapping("/add")
    @ResponseBody
    public String add(Master master) {
        return JSONArray.toJSONString(masterService.insertMaster(master));
    }

    /**
     * 跳转修改页面
     * @return
     */
    @SystemLog(methods = "跳转修改页面",module = "工程师模块")
    @RequestMapping("/goUpdate")
    public String goUpdate() {
        return "/html/master/updateMaster.html";
    }

    /**
     * 修改信息
     * @param master
     * @return
     */
    @SystemLog(methods = "修改信息",module = "工程师模块")
    @RequestMapping("/update")
    @ResponseBody
    public String update(Master master) {
        return JSONArray.toJSONString(masterService.update(master));
    }

}
