package com.swp.dataweb.config;

import com.swp.dataweb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;

@AllArgsConstructor
@EnableAuthorizationServer
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserService userService;
    @Resource
    private TokenStore tokenStore;
    @Resource
    private TokenEnhancer tokenEnhancer;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 配置认证规则
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //super.configure(endpoints);
        endpoints

                //配置由谁完成认证?(认证管理器)
                .authenticationManager(authenticationManager)
                //配置由谁负责查询用户业务数据(可选)//可以让principal可以获取到用户完整信息
                .userDetailsService(userService)
                .accessTokenConverter(jwtAccessTokenConverter)
                //配置可以处理的认证请求方式.(可选,默认只能处理post请求)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
                //配置token生成及存储策略(默认是UUID~随机的字符串)
                .tokenServices(tokenServices());
    }
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        //1.创建授权令牌服务对象(TokenServices)
        DefaultTokenServices tokenServices=new DefaultTokenServices();
        //2.配置令牌的创建和存储对象(TokenStore)
        tokenServices.setTokenStore(tokenStore);
        //3.配置令牌增强(默认令牌的生成非常简单,使用的就是UUID)
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        //4.设置令牌有效时长?
        tokenServices.setAccessTokenValiditySeconds(3600);//1小时
        //5.设置是否支持刷新令牌刷(是否支持使用刷新令牌再生成新令牌)
        tokenServices.setSupportRefreshToken(true);
        //6.设置刷新令牌有效时长?
        tokenServices.setRefreshTokenValiditySeconds(3600*5);//5小时
        return tokenServices;
    }
    //思考:(后面预习完成)
    //1)你对谁颁发令牌?(对客户端有没有要求,假如有是不是需要进行配置)
    //2)你访问哪个路径时帮你颁发令牌,需要对外暴露认证路径?(定义颁发令牌的路径,解析令牌的路径,校验令牌的路径)

    /**
     * 说明:我们的认证服务不是对任意客户端都要颁发令牌,是有条件的.
     * 通过此方法配置对谁颁发令牌?客户端需要有什么特点?
     * @param clients 定义客户端的配置
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //super.configure(clients);
        clients.inMemory()
                //定义客户端要携带的id(客户端访问此服务时要携带的id,这个是自己定义的字符串)
                .withClient("dataweb")
                //定义客户端要携带的秘钥(这个秘钥也是官方定义的一个规则,客户端需要携带,字符串可以自己定义)
                .secret(passwordEncoder.encode("123456"))
                //定义作用范围(所有符合定义规则的客户端,例如client,secret,....)
                .scopes("all")
                //定义允许的认证方式(可以基于密码进行认证,也可以基于刷新令牌进行认证)
                .authorizedGrantTypes("authorization_code","password","refresh_token");
    }
    /**
     * 我们登录时要对哪个url发起请求,通过哪个url可以解析令牌等?
     * 配置要对外暴露的认证url,刷新令牌的url,检查令牌的url等
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //super.configure(security);
        security
                //公开认证的url(可选)
                .tokenKeyAccess("permitAll()")
                //公开检查token有效性的url
                .checkTokenAccess("permitAll()")
                //允许通过表单提交方式进行认证
                .allowFormAuthenticationForClients();
    }

}
