package com.accountingManage.demo;

import com.accountingManage.demo.common.util.user.CommonUserUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableCaching
public class AccountingManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingManageApplication.class, args);
	}
}
