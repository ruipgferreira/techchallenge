package com.ruipgferreira.bliptechchallenge;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import com.ruipgferreira.bliptechchallenge.models.Purchase;
import com.ruipgferreira.bliptechchallenge.repositories.DetailsRepository;
import com.ruipgferreira.bliptechchallenge.repositories.PurchaseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlipTechChallengeApplication.class)
@WebAppConfiguration
public class BlipTechChallengeApplicationTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private DetailsRepository detailsRepository;
    
    private Purchase purchase;
    
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    
    @Test
    public void getPurchases_SizeEquals1And2xx_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/")
        		.accept(contentType))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    } 
    
    @Test
    public void getPurchasesId3_PurchaseId3Returned_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/{id}", 3l)
        		.accept(MediaType.APPLICATION_JSON))                
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }  
    
    @Test
    public void getPurchasesId4_Return404_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/{id}", 4l)
        		.accept(MediaType.APPLICATION_JSON))
                //.andDo(MockMvcResultHandlers.print())
        		.andExpect(status().isNotFound());
    } 
  
    @Test
    public void createPurchase_Return201_Success() throws Exception {
    	String body = "{";
    	body += "\"productType\": \"type4\",";
    	body += "\"expiresUTC\": \"2017-03-03T10:17:19\",";
    	
    	body += "\"purchaseDetails\":";
    	body += "{";
    	body += "\"description\": \"desc4\",";
    	body += "\"quantity\": 44,";
    	body += "\"value\": 4.4";
    	body += "}";
    	
    	body += "}";
    	
        mockMvc.perform(post("/purchases/")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }
    
    
    @Test
    public void updatePurchase_Return201_Success() throws Exception {
        mockMvc.perform(put("/purchases/{id}", 2l)
                .content("{\"productType\": \"type22\",\"expiresUTC\": \"2028-03-03T10:17:19\"}")
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(MockMvcResultHandlers.print());
                //.andExpect(status().isNotFound());
    } 
    
    @Test
    public void updatePurchaseExpired_Return404_Success() throws Exception {
        mockMvc.perform(put("/purchases/{id}", 1l)
                .content("{\"productType\": \"type11\",\"expiresUTC\": \"2028-03-03T10:17:19\"}")
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }     
        
    
    /*
    @Test
    public void updatePurchaseNotFoundError() throws Exception {
        mockMvc.perform(put("/purchases/2")
                .content("{\"productType\": \"type44\",\"expiresUTC\": \"2018-03-03T10:17:19\"}")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
   */
}
