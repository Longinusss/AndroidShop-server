package com.lon.qingshe;

/*
2020.03.02
编写接口测试用例
测试失败》》
 */

import com.alibaba.fastjson.JSON;
import com.lon.qingshe.pojo.User;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.MOCK,classes = QingsheApplication.class)
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("测试controller方法")
    public void test() throws Exception {
        HashMap map=new HashMap();
        map.put("id","123111");
        map.put("password","123");
//        MvcResult mvcResult = mockMvc.perform(
//                post("/account/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JSON.toJSONString(param)))
//                .andReturn();
//
//        System.out.println(mvcResult.getResponse().getContentAsString());
        String mvcResult1 =mockMvc.perform(MockMvcRequestBuilders.post("/account/login").
                contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(map))).andDo(print()).andExpect(status()
                .isOk()).andReturn().getResponse().getContentAsString();

    }

}
