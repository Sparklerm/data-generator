package com.generator.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Sparkler
 * @createDate 2022/11/14
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean enable;

    /**
     * application中还配置了mvc，因为springboot2.6.1与swagger3不兼容
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // API 页面上半部分展示信息
                .apiInfo(apiInfo())
                // ture 启用Swagger3.0， false 禁用（生产环境要禁用）
                .enable(enable).select()
                // 扫描的路径
                .apis(RequestHandlerSelectors.basePackage("com.generator.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build();
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Data Generator API Document").description("Data Generator接口文档").contact(new Contact("Sparkler", "https://github.com/Sparklerm/data-generator", "mengjiao.cn@outlook.com")).version("1.0").build();
    }
}
