package cn.chenhaonee.movie.nuomi.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ChenhaoNee on 2016/11/26.
 */
@Configuration
@EnableSwagger2
@ComponentScan("cn.chenhaonee.movie.nuomi.controller")
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("privite")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.chenhaonee.movie.nuomi.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MoviesInfo  RESTful APIs")
                .description("MoviesInfo平台内部开发接口")
                .termsOfServiceUrl("termsOfService.html")
                .contact("ChenhaoNee@outlook.com")
                .version("1.0")
                .build();
    }
}