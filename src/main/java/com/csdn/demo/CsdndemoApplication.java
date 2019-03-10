package com.csdn.demo;

import com.csdn.demo.common.util.user.CommonUserUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableCaching
public class CsdndemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsdndemoApplication.class, args);
	}
}
