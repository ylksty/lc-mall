package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 * 启动程序
 * </p>
 *
 * @author joe 2021/3/17 18:33
 */
@MapperScan("com.ruoyi.*.mapper")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MallApplication {
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MallApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  mall启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
