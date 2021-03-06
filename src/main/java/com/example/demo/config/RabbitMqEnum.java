package com.example.demo.config;

/**
 * 定义rabbitMq需要的常量
 *
 * @author chenhf
 * @create 2017-10-23 下午4:07
 **/
public class RabbitMqEnum {

    /**
     * @param
     * @Author:chenhf
     * @Description:定义数据交换方式
     * @Date:下午4:08 2017/10/23
     * @return
     */
    public enum Exchange {
        CONTRACT_FANOUT("CONTRACT_FANOUT", "消息分发"),
        CONTRACT_TOPIC("CONTRACT_TOPIC", "消息订阅"),
        CONTRACT_DIRECT("CONTRACT_DIRECT", "点对点");
       // CONTRACT_KILL("KILL_EXCHANG","秒杀");
        private String code;
        private String name;

        Exchange(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * describe: 定义队列名称
     * creat_user: chenhf
     * creat_date: 2017/10/31
     **/
    public enum QueueName {
        FANOUT("FANOUT","fanout隊列"),
        TESTQUEUE("TESTQUEUE", "测试队列"),
        TOPICTEST1("TOPICTEST1", "topic测试队列"),
        TOPICTEST2("TOPICTEST2", "topic测试队列"),
        KILLQUEUE("KILLQUEUE","秒杀队列");
        private String code;
        private String name;

        QueueName(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * describe: 定义routing_key
     * creat_user: chenhf
     * creat_date: 2017/10/31
     **/
    public enum QueueEnum {
        FANOUT("FANOUTQUEUE1","測試fanout隊列key"),
        TESTQUEUE("TESTQUEUE1", "测试队列key"),
        TESTTOPICQUEUE1("*.TEST.*", "topic测试队列key"),
        TESTTOPICQUEUE2("lazy.#", "topic测试队列key"),
        KILL_ROUTING_KEY("KILL_ROUTING_KEY","秒杀routing-key");

        private String code;
        private String name;

        QueueEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}