package com.andy.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Table;

import java.util.Date;

@ApiModel
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

   @ApiModelProperty("主键")
   private Integer id;

   @ApiModelProperty("姓名")
   private String name;

   @ApiModelProperty("年龄")
   private Integer age;

   @ApiModelProperty("生日")
   private Date birth;

}
