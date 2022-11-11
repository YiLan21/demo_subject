package com.example.demo_subject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final ApiInfo DEFULT_API_INFO = new ApiInfoBuilder().title("RESTful APIs")// .description("RESTful
																							// APIs")
//			.termsOfServiceUrl("urn:tos") 
//			//.termsOfServiceUrl("http://localhost:8080/")
//			.contact(new Contact("DEFAULT","",""))
//			.license("Apache 2.0")//
//			.version("1.0")
//			.licenseUrl("http://www.apache.org/license/LICENSE-2.0.TXT")
			.build();

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFULT_API_INFO)// ���api�򥻸�T�A�i�H���[
				.select()// �^�Ǥ@��ApiSelectorBuilder��ҡA�Ψӱ�����Ǥ����i�H��Swagger����{
				// �]�w�M�󱽴y���|
				// Swagger�|���y�M��U�Ҧ�controller �w�qAPI�åB���ͤ��
				// �Y���QAPI���ͬ������A�i�H�bAPI�W�[�W@ApiIgnore
				.apis(RequestHandlerSelectors.basePackage("com.example.demo_subject.controller"))
				.paths(PathSelectors.any()).build();

	}
}
