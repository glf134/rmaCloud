package com.rma.auth.endpoint;

import com.ram.common.LogAnnotation;
import com.ram.common.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author glf
 */
@RestController
@Api(tags = "OAuth API")
@RequestMapping("/oauth")
public class RmaTokenEndpoint {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @GetMapping("/user/token")
    @ApiOperation(value = "根据用户信息获取token")
    @LogAnnotation(module = "rma-auth", recordRequestParam = false)
    public ResponseEntity<OAuth2AccessToken> getAccessToken(@RequestHeader("client_id")String client_id,
                                                            @RequestHeader("client_secret")String client_secret,
                                                            @RequestHeader("username")String username,
                                                            @RequestHeader("password")String password) throws BaseException, HttpRequestMethodNotSupportedException {
        UsernamePasswordAuthenticationToken p = new UsernamePasswordAuthenticationToken(client_id,client_secret,null);
        Map params = new HashMap();
        params.put("client_id",client_id);
        params.put("client_secret",client_secret);
        params.put("username",username);
        params.put("password",password);
        params.put("grant_type","password");
        return  tokenEndpoint.postAccessToken(p,params);
    }


}
