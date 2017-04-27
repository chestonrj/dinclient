package com.ding.controller;

import com.ding.DGlobal;
import com.ding.data.DingDepartment;
import com.ding.data.DingDepartmentList;
import com.ding.data.DingDepartmentUsers;
import com.ding.data.DingUser;
import com.ding.main.InitToken;
import com.ding.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gurongjing on 2017/4/27.
 */

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    InitToken initToken;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getInitToken", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public Map getInitToken(){
        Map map = new HashMap();
        initToken.getAccessToken();
        map.put("access_token", DGlobal.accessToken);
        map.put("ticket", DGlobal.jsTicket);
        List<Map<String,String>> xx = new ArrayList<Map<String,String>>();
        Map map1 = new HashMap();
        map1.put("12","24");
        Map map2 = new HashMap();
        map2.put("13","26");
        xx.add(map1);
        xx.add(map2);
        map.put("orderInDepts",xx);
        return map;
    }

    /**
     * 得到该公司部门列表，id=1
     *
     * @param parentId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDingDepartmentList", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public DingDepartmentList getDingDepartmentList(@RequestParam(value = "parentId") Integer parentId) throws Exception{
        return userService.getDepartmentList(parentId);
    }

    /**
     *根据部门id得到部门详细信息
     *
     * @param depId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDingDepartmentDetail", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public DingDepartment getDingDepartmentDetail(@RequestParam(value = "depId") Integer depId) throws Exception{
        return userService.getDepartmentDetail(depId);
    }

    /**
     *根据部门id得到部门成员
     *
     * @param depId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDingDepartmentUser", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public DingDepartmentUsers getDingDepartmentUser(@RequestParam(value = "depId") Integer depId) throws Exception{
        return userService.getDingDepartmentUser(depId);
    }

    /**
     *根据部门id得到部门成员
     *
     * @param depId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDingDepartmentUserDetail", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public DingDepartmentUsers getDingDepartmentUserDetail(@RequestParam(value = "depId") Integer depId) throws Exception{
        return userService.getDingDepartmentUser(depId);
    }

    /**
     *根据unionid得到用户id
     *
     * @param unionId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserId", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public String getUserId(@RequestParam(value = "unionId") String unionId) throws Exception{
        return userService.getUserId(unionId);
    }

    /**
     *根据unionid得到用户id
     *
     * @param userid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserDetailByUserId", method = {
            RequestMethod.GET,RequestMethod.POST},headers="Accept=application/json")
    @ResponseBody
    public DingUser getUserDetailByUserId(@RequestParam(value = "userid") String userid) throws Exception{
        return userService.getUserDetailByUserId(userid);
    }

}
