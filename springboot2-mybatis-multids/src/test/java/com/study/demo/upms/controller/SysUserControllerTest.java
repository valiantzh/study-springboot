package com.study.demo.upms.controller;

import com.study.demo.Springboot2MybatisMultidsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Springboot2MybatisMultidsApplication.class)
public class SysUserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllMaster() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/user/getAllMaster")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserMaster() throws Exception {


        String userId = "00001";
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .get("/user/getMaster")
                .param("userId", userId)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void insertMaster() throws Exception {
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .post("/user/insertMaster")
                .param("uid","00001")
                .param("uname", "uname01")
                .param("nick", "01")
                .param("pwd", "pwd")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        String userId = action.andReturn().getResponse().getContentAsString();

        System.out.println("userId:" + userId);
    }

    @Test
    public void updateMaster() throws Exception {
        // 先保存，确保有数据
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .post("/user/insertMaster")
                .param("uid","00002")
                .param("uname", "uname02")
                .param("nick", "02")
                .param("pwd", "pwd")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String userId = action.andReturn().getResponse().getContentAsString();
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .post("/user/updateMaster")
                .param("uname", "uname02")
                .param("nick", "02-修改")
                .param("pwd", "pwd")
                .param("uid", userId)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMaster() throws Exception {
        // 先保存，确保有数据
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .post("/user/insertMaster")
                .param("uid","00003")
                .param("uname", "uname03")
                .param("nick", "03")
                .param("pwd", "pwd")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String userId = action.andReturn().getResponse().getContentAsString();
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .delete("/user/deleteMaster")
                .param("userId", userId)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /************************主库控制层接口测试-end******************************/

    /************************从库控制层接口测试-start******************************/

    @Test
    public void getAllCluster() throws Exception {
        // 先保存，确保有数据
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .post("/user/insertCluster")
                .param("uid","00001")
                .param("uname", "uname01")
                .param("nick", "01-S1")
                .param("pwd", "pwd")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String userId = action.andReturn().getResponse().getContentAsString();
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .get("/user/getAllCluster")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserCluster() throws Exception {
        // 先保存，确保有数据
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .post("/user/insertCluster")
                .param("uid","00002")
                .param("uname", "uname02")
                .param("nick", "S1-02")
                .param("pwd", "pwd")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String userId = action.andReturn().getResponse().getContentAsString();
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .get("/user/getCluster")
                .param("userId", userId)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void insertCluster() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/user/insertCluster")
                .param("uid","00001")
                .param("uname", "uname01")
                .param("nick", "S1-01")
                .param("pwd", "pwd")
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateCluster() throws Exception {


        String userId = "00002";
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .post("/user/updateCluster")
                .param("uname", "uname01")
                .param("nick", "S1-01")
                .param("pwd", "pwd")
                .param("uid", userId)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteCluster() throws Exception {

        String userId = "00001";
        System.out.println("userId:" + userId);

        mvc.perform(MockMvcRequestBuilders
                .delete("/user/deleteCluster")
                .param("userId", userId)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}