package com.andy.consumer;

import com.alibaba.fastjson.JSONObject;
import com.andy.po.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @KafkaListener(topics = {"andyTest","user_topic","test"})
    public void consumerTo(ConsumerRecord<String,String> record,Acknowledgment ack){
        User user = JSONObject.parseObject(record.value(), User.class);
        System.out.println("开始消费消息******************"+record);
        //手动提交offset
        ack.acknowledge();
    }
}
