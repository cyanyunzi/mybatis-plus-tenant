package linwu.tenant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：林雾
 * @date ：2020/06/19
 * @description :
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket commonApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("common")
        .apiInfo(new ApiInfoBuilder().title("API").description("提供API调用").version("1.0").build())
        .select()
        .apis(RequestHandlerSelectors.basePackage("linwu.tenant.controller.common"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }


  @Bean
  public Docket tanantApi() {
    List<Parameter> pars = new ArrayList<Parameter>();
    Parameter parameter =
            new ParameterBuilder()
                    .name("tanent_id")
                    .description("代理商ID")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(true)
                    .build();

    pars.add(parameter);

    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("tenant")
            .apiInfo(new ApiInfoBuilder().title("API").description("提供API调用").version("1.0").build())
            .select()
            .apis(RequestHandlerSelectors.basePackage("linwu.tenant.controller.tenant"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(pars)
            .useDefaultResponseMessages(false);
  }
}
